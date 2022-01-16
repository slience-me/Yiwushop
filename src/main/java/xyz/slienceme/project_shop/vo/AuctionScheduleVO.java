package xyz.slienceme.project_shop.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AuctionScheduleVO {

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

}
