package com.sxw.design.singleton;

import com.sxw.code.util.ExceptionUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Https工具类单例模式(饿汉)
 *
 * @author shixiangweii
 * @date 2019/8/10 14:51
 */
public class HttpsManager {

    private static final Logger logger = LoggerFactory.getLogger(HttpsManager.class);

    private static HttpsManager instance = new HttpsManager();

    private CloseableHttpClient httpClient;

    private RequestConfig requestConfig;

    static class IdleConnectionEvictor extends Thread {
        private Logger logger = LoggerFactory.getLogger(IdleConnectionEvictor.class);
        private final HttpClientConnectionManager connMgr;
        private static volatile boolean shutdown = false;

        IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
            this.connMgr = connMgr;
            this.start();
        }

        @Override
        public void run() {
            logger.info("开始启动清理线程");
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        if (logger.isDebugEnabled()) {
                            logger.info("do closeExpiredConnections");
                        }
                        connMgr.closeExpiredConnections();
                    }
                }
            } catch (InterruptedException ex) {
                logger.error(ExceptionUtils.getStackTrace(ex));
            }
            logger.info("停止清理线程");
        }

        static void shutdown() {
            shutdown = true;
        }
    }

    private HttpsManager() {
        try {
            init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void init() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, new java.security.SecureRandom());
        SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(sslContext);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslFactory).build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(10000).setTcpNoDelay(true).build();
        connManager.setDefaultSocketConfig(socketConfig);
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8).build();
        connManager.setDefaultConnectionConfig(connectionConfig);
        connManager.setMaxTotal(300);
        connManager.setDefaultMaxPerRoute(200);
        HttpRequestRetryHandler retryHandler = (exception, executionCount, context) -> {
            int count = 2;
            if (executionCount > count) {
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                return true;
            }
            if (exception instanceof UnknownHostException) {
                return true;
            }
            if (exception instanceof SSLException) {
                return true;
            }
            HttpRequest request = HttpClientContext.adapt(context).getRequest();
            return !(request instanceof HttpEntityEnclosingRequest);
        };
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(connManager)
                .setRetryHandler(retryHandler);
        httpClient = httpClientBuilder.build();
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();

        // 启动清理线程
        // 其实这里的JVM，涉及了垃圾回收，强引用的东西
        IdleConnectionEvictor idleConnectionEvictor = new IdleConnectionEvictor(connManager);
    }

    public static HttpsManager getInstance() {
        return instance;
    }

    public void close() {
        IdleConnectionEvictor.shutdown();
    }

    public String post(String postUrl, Map<String, String> params) {
        HttpPost post = new HttpPost(postUrl);
        List<NameValuePair> formParams = new ArrayList<>(params.size() + 1);
        for (String key : params.keySet()) {
            formParams.add(new BasicNameValuePair(key, params.get(key)));
        }
        UrlEncodedFormEntity entity;
        try {
            entity = new UrlEncodedFormEntity(formParams, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        post.setEntity(entity);
        post.setConfig(requestConfig);
        HttpClientContext httpClientContext = HttpClientContext.create();
        try {
            CloseableHttpResponse response = httpClient.execute(post, httpClientContext);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            // java.net.SocketException: Connection reset
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            post.releaseConnection();
        }
        return "";
    }
}
