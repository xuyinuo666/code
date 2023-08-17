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
 * 省市区地区信息
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "area_id", type = IdType.AUTO)
    private Long areaId;

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
     * 地址
     */
    private String areaName;

    /**
     * 上级地址
     */
    private Long parentId;

    /**
     * 等级（从1开始）
     */
    private Integer level;


}
