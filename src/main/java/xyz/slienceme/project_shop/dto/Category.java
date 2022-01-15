package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 物品类型表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Data
public class Category {
    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 状态
     */
    private Integer isDelete;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
}