//package com.goovy.config;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.util.StrUtil;
//import com.goovy.bo.RequestUserBO;
//import com.goovy.constant.RedisCacheConstant;
//import com.goovy.utils.RedisUtil;
//import com.goovy.utils.UserContextUtils;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Objects;
//
//@Component
//public class RequestUserInterceptor implements HandlerInterceptor {
//    @Resource
//    @Lazy
//    private RedisUtil redisUtil;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("===================================================");
//        String token = request.getHeader("Authorization");
//        if (StrUtil.isNotBlank(token)) {
//            Object user = redisUtil.hget(RedisCacheConstant.USER_TOKEN, token);
//            if (Objects.nonNull(user)) {
//                RequestUserBO requestUserBO = BeanUtil.copyProperties(user, RequestUserBO.class);
//                UserContextUtils.set(requestUserBO);
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        UserContextUtils.clear();
//    }
//}