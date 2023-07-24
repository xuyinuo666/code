package com.goovy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "gateway")
@Data
@Component
public class WhiteAndBlackProperties {
    private List<String> whiteList = new ArrayList<>();


}
