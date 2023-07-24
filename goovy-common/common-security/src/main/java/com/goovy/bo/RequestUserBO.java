package com.goovy.bo;

import lombok.Data;

import java.util.List;
@Data
public class RequestUserBO {
    private String username;

    private List<String> role;

    private List<String> permission;

}
