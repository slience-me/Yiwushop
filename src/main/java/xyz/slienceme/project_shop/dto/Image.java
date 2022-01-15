package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 图片表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Data
public class Image {
    /**
     * 图片id
     */
    private Integer imageId;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

}