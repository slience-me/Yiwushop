package xyz.slienceme.project_shop.dto;

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
public class Auctions {
    /**
     * 场次id
     */
    private Integer auctionsId;

    /**
     * 场次名称
     */
    private String auctionsName;

    /**
     * 开始时间
     */
    private LocalDateTime start;

    /**
     * 结束时间
     */
    private LocalDateTime end;

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