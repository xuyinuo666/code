package com.goovy.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author FrozenWatermelon
 * @date 2020/6/24
 */
@Configuration
@MapperScan({ "com.goovy.**.mapper" })
public class MybatisConfig {


}
