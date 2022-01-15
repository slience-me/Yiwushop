package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Data
public class Goods {

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 当前价格
     */
    private BigDecimal priceNow;

    /**
     * 商品描述
     */
    private String goodsInfo;

    /**
     * 是否上架
     */
    private Integer stateOn;

    /**
     * 商品分类id
     */
    private Integer categoryId;

    /**
     * 所属用户id
     */
    private Integer userId;

    /**
     * 商品照片
     */
    private Integer goodsImgId;

    /**
     * 状态
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;


}