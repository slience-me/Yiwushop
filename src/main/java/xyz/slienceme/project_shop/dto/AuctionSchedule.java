package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 拍卖过程表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-16
 */
@Data
public class AuctionSchedule {

    /**
     * 拍卖过程id
     */
    private Integer auctionScheduleId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 拍卖价格
     */
    private BigDecimal auctionSchedulePrice;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
}
