package com.goovy.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import pojo.BaseModel;

/**
 * <p>
 * 
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
