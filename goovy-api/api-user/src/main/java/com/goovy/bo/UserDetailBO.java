package com.goovy.bo;

import com.goovy.dto.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserDetailBO {
    private String username;

    private String scope;

    private List<String> author;


}
