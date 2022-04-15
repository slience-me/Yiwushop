package xyz.slienceme.project_shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 工艺展示表
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Data
@ApiModel(value = "CommunityShow工艺展示表的实体", reference = "CommunityShow")
public class CommunityShow {
    /**
     * 展示id
     */
    @ApiParam(value = "展示id", required = true)
    private Integer showId;

    /**
     * 展示名称
     */
    @ApiParam(value = "展示名称, 描述")
    private String showName;

    /**
     * 照片
     */
    @ApiParam(value = "照片")
    private Integer showImgId;

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