package xyz.slienceme.project_shop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 工艺展示表
 */
@Data
public class CommunityShowVO {

    /**
     * 展示名称
     */
    @ApiParam(value = "展示名称")
    private String showName;

    /**
     * 照片
     */
    @ApiParam(value = "照片")
    private Integer showImgId;

}