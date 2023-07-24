package com.goovy.oauth2.dto;

import com.goovy.dto.BaseDTO;
import lombok.Data;

@Data
public class UserDTO extends BaseDTO {

    private Long id;

    //@Api(value = "手机号")
    private String phone;

    //@ApiModelProperty(value = "用户名")
    private String username;

    //@ApiModelProperty(value = "密码（加密）")
    private String passwd;

    //@ApiModelProperty(value = "盐加密")
    private String salt;

    //@ApiModelProperty(value = "是否被锁")
    private Boolean isBlocked;

}
