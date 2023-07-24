package com.goovy.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.goovy.bo.RequestUserBO;
import com.goovy.constant.RedisCacheConstant;
import com.goovy.util.SpringContextUtils;
import com.goovy.utils.RedisUtil;
import com.goovy.utils.UserContextUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class RequestUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        RedisUtil redisUtil = SpringContextUtils.getBean(RedisUtil.class);
        String userStr = request.getHeader("user");
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(userStr)) {
            if (StrUtil.isNotBlank(token)) {
                Object user = redisUtil.get(RedisCacheConstant.USER_TOKEN+token);
                if (Objects.nonNull(user)) {
                    RequestUserBO requestUserBO = BeanUtil.copyProperties(user, RequestUserBO.class);
                    UserContextUtils.set(requestUserBO);
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextUtils.clear();
    }
}