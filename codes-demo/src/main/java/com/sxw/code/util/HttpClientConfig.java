package com.sxw.code.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Description: httpclent操作对象配置
 * User: shixiangweii
 * Date: 2018-05-11
 * Time: 15:10
 *
 * @author shixiangweii
 */
@Configuration
public class HttpClientConfig {
    /**
     * 总共的连接数
     */
    private static final int MAX_TOTAL = 100;
    /**
     * 每个路由连接数
     */
    private static final int MAX_PER_ROUTE = 10;

    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =
                new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(MAX_TOTAL);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);
        poolingHttpClientConnectionManager.setValidateAfterInactivity(1000);
        return poolingHttpClientConnectionManager;
    }

    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder httpClientBuilder(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setConnectionManager(poolingHttpClientConnectionManager);
        return builder;
    }

    @Bean
    @Scope(value = "prototype")
    public CloseableHttpClient closeableHttpClient(HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    @Bean
    public RequestConfig requestConfigBuilder() {
        return RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(10000)
                .build();
    }

    @Bean
    public IdleConnectionEvictor idleConnectionEvictor(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
        return new IdleConnectionEvictor(poolingHttpClientConnectionManager);
    }
}
