package com.goovy.oauth2.feign;

import com.alibaba.fastjson.JSONObject;
import com.goovy.dto.UserLoginDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.goovy.response.Res;

@FeignClient("goovy-oauth2")
public interface OAuth2FeignClient {
    @PostMapping("/api/oauth/checkToken")
    Res<JSONObject> checkToken(@RequestBody String token);

    @PostMapping("/api/oauth/getToken")
    Res getToken(@RequestBody UserLoginDTO userLogin);
}
