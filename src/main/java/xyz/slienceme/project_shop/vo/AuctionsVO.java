package xyz.slienceme.project_shop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class AuctionsVO {

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 场次名称
     */
    private String auctionsName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

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

}