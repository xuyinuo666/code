package com.goovy.feign;

import com.alibaba.fastjson.JSONObject;
import com.goovy.response.Res;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("goovy-user")
public interface UserFeignClient {
    @GetMapping("/api/user/getUser")
    Res<JSONObject> checkToken(@RequestBody String token);

    @GetMapping("/api/user/getUserPermission")
    Res<JSONObject> getUserPermission(@RequestBody String token);
}
