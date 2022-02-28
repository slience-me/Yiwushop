package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Pawn {
    /**
     * 典当id
     */
    private Integer auctionsId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 典当名称
     */
    private String pawnName;

    /**
     * 开始时间
     */
    private LocalDateTime start;

    /**
     * 结束时间
     */
    private LocalDateTime end;

    /**
     * 目前价格
     */
    private BigDecimal presentPrice;

    /**
     * 目前标价人id
     */
    private Integer presentPerson;

    /**
     * 一口价
     */
    private BigDecimal fixedPrice;

    /**
     * 起拍价
     */
    private BigDecimal startingPrice;

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