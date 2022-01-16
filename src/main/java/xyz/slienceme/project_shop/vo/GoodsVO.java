package xyz.slienceme.project_shop.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsVO {

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
    /*private BigDecimal priceNow;*/

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

}