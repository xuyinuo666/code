package com.goovy.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.goovy.response.Res;

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
    @GetMapping("/getAreaList")
    public Res<List<String>> getAreaList(){
        return Res.success(Arrays.asList("1","2"));
    }
}
