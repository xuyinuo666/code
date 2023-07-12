package com.goovy.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(("/test/oauth2"))
public class TestController {
    @Resource
    private PasswordEncoder passwordEncoder;
    @RequestMapping("/encode")
    public boolean encodePasswd(){
        System.out.println(passwordEncoder.encode("12345"));
        System.out.println(passwordEncoder.encode("secret"));
        return true;
    }
}
