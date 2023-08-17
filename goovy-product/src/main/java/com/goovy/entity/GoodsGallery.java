package com.goovy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author y1nuo
 * @since 2023-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("goods_gallery")
public class GoodsGallery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 是否是默认图片
     */
    private Integer isDefault;

    /**
     * 原图路径
     */
    private String original;

    /**
     * 小图路径
     */
    private String small;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 缩略图路径
     */
    private String thumbnail;


}
