package com.goovy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单资源
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("menu_permission")
public class MenuPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单资源用户id
     */
    @TableId(value = "menu_permission_id", type = IdType.AUTO)
    private Long menuPermissionId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 资源关联菜单
     */
    private Long menuId;

    /**
     * 业务类型 1 店铺菜单 2平台菜单
     */
    private Integer bizType;

    /**
     * 权限对应的编码
     */
    private String permission;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源对应服务器路径
     */
    private String uri;

    /**
     * 请求方法 1.GET 2.POST 3.PUT 4.DELETE
     */
    private Integer method;


}
