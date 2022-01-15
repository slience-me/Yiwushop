package xyz.slienceme.project_shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 竞拍场次表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Data
@ApiModel(value = "Auctions竞拍场次表的实体", reference = "Auctions")
public class Auctions {
    /**
     * 场次id
     */
    @ApiParam(value = "场次id", required = true)
    private Integer auctionsId;

    /**
     * 场次名称
     */
    @ApiParam(value = "场次名称")
    private String auctionsName;

    /**
     * 开始时间
     */
    @ApiParam(value = "开始时间")
    private LocalDateTime start;

    /**
     * 结束时间
     */
    @ApiParam(value = "结束时间")
    private LocalDateTime end;

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