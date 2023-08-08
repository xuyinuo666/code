package com.goovy.interceptor;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.goovy.bo.RequestUserBO;
import com.goovy.exception.GoovyException;
import com.goovy.util.SpringContextUtils;
import com.goovy.utils.UserContextUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Component
public class RequestUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestUserBO requestUserBO = null;
        String token = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(token)) {
            MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
            paramMap.add("token", token);
//           Map<String,String> map = new HashMap<>();
//            map.put("token",token);
            RestTemplate restTemplate = SpringContextUtils.getBean(RestTemplate.class);
            Map body = restTemplate.postForObject("http://goovy-oauth2/oauth/check_token", paramMap, Map.class);
//            Map body = mapResponseEntity.getBody();
            try {
                String userName = MapUtil.getStr(body, "user_name");
                requestUserBO = new RequestUserBO();
                requestUserBO.setUsername(userName);
                requestUserBO.setToken(token);
            } catch (Exception e) {
                throw new GoovyException("please retry login!");
            }
        }
        if (Objects.nonNull(requestUserBO)) {
            UserContextUtils.set(requestUserBO);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextUtils.clear();
    }
}