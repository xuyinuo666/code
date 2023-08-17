package com.goovy.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestHighLevelClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {
    @Value("${es.host}")
    private String host;

    @Value("${es.port}")
    private int port;
    // Create the low-level client
    @Bean
    public RestClient getHttpClient() {
        return RestClient.builder(
                new HttpHost(host, port)
        ).build();
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClientBuilder(getHttpClient())
                .setApiCompatibilityMode(true)
                .build();
    }

}
