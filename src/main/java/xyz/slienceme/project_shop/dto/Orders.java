package xyz.slienceme.project_shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
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
 * @since 2022-03-15
 */
@Data
@ApiModel(value = "Orders订单表的实体", reference = "Orders")
public class Orders {
    /**
     * 订单id
     */
    @ApiParam(value = "订单id", required = true)
    private Integer ordersId;

    /**
     * 流水号
     */
    @ApiParam(value = "流水号")
    private String serialNum;

    /**
     * 商品id
     */
    @ApiParam(value = "商品id")
    private Integer goodsId;

    /**
     * 竞拍商品拥有者id
     */
    @ApiParam(value = "竞拍商品拥有者id")
    private Integer sellUsersId;

    /**
     * 购买商品用户id
     */
    @ApiParam(value = "购买商品用户id")
    private Integer buyUsersId;

    /**
     * 购买价格-成本价格
     */
    @ApiParam(value = "购买价格-成本价格")
    private BigDecimal buyPrice;

    /**
     * 状态
     */
    @ApiParam(value = "状态")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @ApiParam(value = "创建时间")
    private LocalDateTime createdTime;

    /**
     * 创建人
     */
    @ApiParam(value = "创建人")
    private Integer createdBy;

}