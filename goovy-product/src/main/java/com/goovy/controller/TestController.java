package com.goovy.controller;
import com.goovy.response.Res;
import com.goovy.feign.ProductFeignClient;
import com.goovy.utils.UserContextUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController implements ProductFeignClient {

    @Override
    public Res test() {
        System.out.println(UserContextUtils.get());
        return Res.success("hello world");
    }
}
