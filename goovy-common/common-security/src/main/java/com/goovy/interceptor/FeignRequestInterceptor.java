package com.goovy.interceptor;

import com.goovy.bo.RequestUserBO;
import com.goovy.utils.UserContextUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestUserBO requestUserBO = UserContextUtils.USER_CONTEXT.get();
        if (Objects.nonNull(requestUserBO)) {
            requestTemplate.headers().put("user", Collections.singleton(requestUserBO.toString()));
        }
    }
}