package com.goovy.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("goods_sku")
public class GoodsSku implements Serializable {

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
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 删除标志 true/false 删除/未删除
     */
    private Boolean deleteFlag;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 审核信息
     */
    private String authMessage;

    /**
     * 大图路径
     */
    private String big;

    /**
     * 品牌ID
     */
    private String brandId;

    /**
     * 购买数量
     */
    private Integer buyCount;

    /**
     * 分类路径
     */
    private String categoryPath;

    /**
     * 评价数量
     */
    private Integer commentNum;

    /**
     * 成本价格
     */
    private Double cost;

    /**
     * 运费承担者
     */
    private String freightPayer;

    /**
     * 配送模版ID
     */
    private String freightTemplateId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 计量单位
     */
    private String goodsUnit;

    /**
     * 商品视频
     */
    private String goodsVideo;

    /**
     * 商品好评率
     */
    private Double grade;

    /**
     * 商品详情
     */
    private String intro;

    /**
     * 审核状态
     */
    private String authFlag;

    /**
     * 是否是促销商品
     */
    private Boolean promotionFlag;

    /**
     * 上架状态
     */
    private String marketEnable;

    /**
     * 移动端商品详情
     */
    private String mobileIntro;

    /**
     * 原图路径
     */
    private String original;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 促销价
     */
    private Double promotionPrice;

    /**
     * 库存
     */
    private Integer quantity;

    /**
     * 是否为推荐商品
     */
    private Boolean recommend;

    /**
     * 销售模式
     */
    private String salesModel;

    /**
     * 是否自营
     */
    private Boolean selfOperated;

    /**
     * 店铺ID
     */
    private String storeId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 卖点
     */
    private String sellingPoint;

    /**
     * 小图路径
     */
    private String small;

    /**
     * 商品编号
     */
    private String sn;

    /**
     * 规格信息json
     */
    private String specs;

    /**
     * 运费模板id
     */
    private String templateId;

    /**
     * 缩略图路径
     */
    private String thumbnail;

    /**
     * 下架原因
     */
    private String underMessage;

    /**
     * 浏览数量
     */
    private Integer viewCount;

    /**
     * 重量
     */
    private Double weight;

    /**
     * 规格信息
     */
    private String simpleSpecs;

    /**
     * 店铺分类路径
     */
    private String storeCategoryPath;

    private String goodsType;


}
