package com.goovy.controller;

import com.goovy.utils.RedisUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.goovy.response.Res;

import javax.annotation.Resource;

@RestController
@RequestMapping(("/test/oauth2"))
@Tag(name = "oauth2测试接口")
public class TestController {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RedisUtil redisUtil;
    @RequestMapping("/encode")
    @Operation(summary = "加密密码" , description = "加密密码")
    public Res<String> encodePasswd(@RequestParam String passwd){
        return Res.success(passwordEncoder.encode(passwd));
    }

    @RequestMapping("/redisToken")
    public Res<String> redisToken(){
        System.out.println(redisUtil.get("user::token:access:EEtC9Eg_Id--M6telgaH1dgr8Io"));
        return Res.success();
    }
}
