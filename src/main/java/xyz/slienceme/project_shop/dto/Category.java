package xyz.slienceme.project_shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 物品类型表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Data
@ApiModel(value = "Category物品类型表的实体", reference = "Category")
public class Category {
    /**
     * 分类id
     */
    @ApiParam(value = "分类id", required = true)
    private Integer categoryId;

    /**
     * 分类名称
     */
    @ApiParam(value = "分类名称")
    private String categoryName;

    /**
     * 状态
     */
    @ApiParam(value = "状态")
    private Integer isDelete;

    /**
     * 创建人
     */
    @ApiParam(value = "创建人")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @ApiParam(value = "创建时间")
    private LocalDateTime createdTime;
}