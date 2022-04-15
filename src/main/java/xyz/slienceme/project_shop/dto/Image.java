package xyz.slienceme.project_shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 图片表
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Data
@ApiModel(value = "Image图片表的实体", reference = "Image")
public class Image {
    /**
     * 图片id
     */
    @ApiParam(value = "图片id", required = true)
    private Integer imageId;

    /**
     * 图片地址
     */
    @ApiParam(value = "图片地址")
    private String imageUrl;

    /**
     * 创建时间
     */
    @ApiParam(value = "创建时间")
    private LocalDateTime createdTime;

}