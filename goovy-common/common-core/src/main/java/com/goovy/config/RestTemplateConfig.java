package com.goovy.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author xgw
 * @date 2020/7/11
 */
@Configuration
public class RestTemplateConfig {

	@Bean
	@ConditionalOnMissingBean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
