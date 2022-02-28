package xyz.slienceme.project_shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
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
@ApiModel(value = "Goods商品表的实体", reference = "Goods")
public class Goods {

    /**
     * 商品id
     */
    @ApiParam(value = "商品id", required = true)
    private Integer goodsId;

    /**
     * 商品名称
     */
    @ApiParam(value = "商品名称")
    private String goodsName;

    /**
     * 商品价格
     */
    /*@ApiParam(value = "商品价格")
    private BigDecimal goodsPrice;*/

    /**
     * 当前价格
     */
    /*@ApiParam(value = "当前价格")
    private BigDecimal priceNow;*/

    /**
     * 当前最高价用户id
     */
   /* @ApiParam(value = "当前最高价用户id")
    private Integer priceUserId ;*/

    /**
     * 商品描述
     */
    @ApiParam(value = "商品描述")
    private String goodsInfo;

    /**
     * 是否上架  1. 未上架  2. 已上架， 3.添加典当
     */
    @ApiParam(value = "是否上架")
    private Integer stateOn;

    /**
     * 商品分类id
     */
    @ApiParam(value = "商品分类id")
    private Integer categoryId;

    /**
     * 所属用户id
     */
    @ApiParam(value = "所属用户id")
    private Integer userId;

    /**
     * 商品照片
     */
    @ApiParam(value = "商品照片")
    private Integer goodsImgId;

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


}