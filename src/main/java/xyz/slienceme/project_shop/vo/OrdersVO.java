package xyz.slienceme.project_shop.vo;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class OrdersVO {

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 竞拍商品拥有者id
     */
    private Integer sellUsersId;

    /**
     * 购买商品用户id
     */
    private Integer buyUsersId;

    /**
     * 购买价格-成本价格
     */
    private BigDecimal buyPrice;

}