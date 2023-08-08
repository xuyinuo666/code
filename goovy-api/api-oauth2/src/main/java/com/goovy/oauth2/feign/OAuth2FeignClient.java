package com.goovy.oauth2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("goovy-oauth2")
public interface OAuth2FeignClient {
    @GetMapping("/oauth/check_token")
    Map<String, Object> checkToken(@RequestParam String token);
}
