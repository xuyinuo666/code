package com.goovy.feign;

import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xgw
 * @date 2020/11/27
 */
//@Component
//@ConditionalOnClass({RequestInterceptor.class})
//@Slf4j
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {


    @Autowired
    private FeignInsideAuthConfig feignInsideAuthConfig;

    @Override
    public void apply(RequestTemplate template) {
        // feign的内部请求，往请求头放入key 和 secret进行校验
        template.header(feignInsideAuthConfig.getKey(), feignInsideAuthConfig.getSecret());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String authorization = request.getHeader("Authorization");


        if (StrUtil.isNotBlank(authorization)) {
            template.header("Authorization", authorization);
        }
    }
}