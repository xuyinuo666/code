package com.goovy.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.goovy.bo.RequestUserBO;
import com.goovy.utils.UserContextUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestUserBO requestUserBO = UserContextUtils.USER_CONTEXT.get();
        if (Objects.nonNull(requestUserBO)) {
            requestTemplate.header("Authorization", requestUserBO.getToken());
        }
    }
}
