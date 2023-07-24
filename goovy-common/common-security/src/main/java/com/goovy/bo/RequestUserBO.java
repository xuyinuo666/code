package com.goovy.bo;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class RequestUserBO {
    private String username;

    private List<String> role;

    private List<String> permission;

}
