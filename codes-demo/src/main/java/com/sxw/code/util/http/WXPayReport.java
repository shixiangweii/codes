package com.sxw.code.util.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * 交易保障 微信SDK中的http操作
 */
public class WXPayReport {
	
	private static final String REPORT_URL = "http://report.mch.weixin.qq.com/wxpay/report/default";

	/**
	 * http 请求
	 * 
	 * @param data
	 * @param connectTimeoutMs
	 * @param readTimeoutMs
	 * @return
	 * @throws Exception
	 */
	public static String httpRequest(String data, int connectTimeoutMs, int readTimeoutMs) throws Exception {
		// httpclient配置
		BasicHttpClientConnectionManager connManager;
		
		connManager = new BasicHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", SSLConnectionSocketFactory.getSocketFactory()).build(), null, null, null);
		
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();

		// post请求配置
		HttpPost httpPost = new HttpPost(REPORT_URL);

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeoutMs)
				.setConnectTimeout(connectTimeoutMs).build();
		
		httpPost.setConfig(requestConfig);

		// 请求体
		StringEntity postEntity = new StringEntity(data, "UTF-8");
		httpPost.setEntity(postEntity);
		
		// 请求头
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 "); 
		
		// 执行
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		// 返回
		HttpEntity httpEntity = httpResponse.getEntity();
		
		return EntityUtils.toString(httpEntity, "UTF-8");
	}

}
