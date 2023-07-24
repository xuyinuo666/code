package com.goovy.feign;

import com.alibaba.fastjson.JSONObject;
import com.goovy.response.Res;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("goovy-product")
public interface ProductFeignClient {
    @GetMapping("/api/product/test")
    Res test();
}
