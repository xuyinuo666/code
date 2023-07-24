package com.goovy.config;

import com.goovy.interceptor.RequestUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        添加拦截器
        registry.addInterceptor(new RequestUserInterceptor())
//                定义拦截路径
                .addPathPatterns("/**")
//                定义放行路径
                .excludePathPatterns("/index/**");
    }
}
