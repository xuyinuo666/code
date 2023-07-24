package com.goovy.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String username;

    private String passwd;

    private String scope;

    private String grant_type;

    private String client_id;

    private String client_secret;


}
