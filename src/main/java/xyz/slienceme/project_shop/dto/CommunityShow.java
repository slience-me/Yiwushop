package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 工艺展示表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Data
public class CommunityShow {
    /**
     * 展示id
     */
    private Integer showId;

    /**
     * 展示名称
     */
    private String showName;

    /**
     * 照片
     */
    private Integer showImgId;

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