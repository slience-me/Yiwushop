package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Data
public class Orders {
    /**
     * 订单id
     */
    private Integer ordersId;

    /**
     * 流水号
     */
    private String serialNum;

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

    /**
     * 状态
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 创建人
     */
    private Integer createdBy;

}