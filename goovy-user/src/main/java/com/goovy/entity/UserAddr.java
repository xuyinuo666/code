package com.goovy.entity;

import java.math.BigDecimal;
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
 * 用户地址
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_addr")
public class UserAddr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "addr_id", type = IdType.AUTO)
    private Long addrId;

    /**
     * 建立时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 是否默认地址 1是
     */
    private Integer isDefault;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 省ID
     */
    private Long provinceId;

    /**
     * 省
     */
    private String province;

    /**
     * 城市ID
     */
    private Long cityId;

    /**
     * 城市
     */
    private String city;

    /**
     * 区ID
     */
    private Long areaId;

    /**
     * 区
     */
    private String area;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 地址
     */
    private String addr;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;


}
