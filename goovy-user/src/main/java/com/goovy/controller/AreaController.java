package com.goovy.controller;


import com.goovy.feign.ProductFeignClient;
import com.goovy.utils.UserContextUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.goovy.response.Res;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 省市区地区信息 前端控制器
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-15
 */
@RestController
@RequestMapping("/area")
public class AreaController {
    @Resource
    private ProductFeignClient productFeignClient;
    @GetMapping("/getAreaList")
    public Res<List<String>> getAreaList(){
        Res test = productFeignClient.test();
        System.out.println(UserContextUtils.get());
        System.out.println(test);
        return Res.success(Arrays.asList("1","2"));
    }
}
