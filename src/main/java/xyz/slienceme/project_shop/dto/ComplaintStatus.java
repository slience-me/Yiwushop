package xyz.slienceme.project_shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * <p>
 * 投诉状态类型表
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Data
@ApiModel(value = "ComplaintStatus投诉状态类型表的实体", reference = "ComplaintStatus")
public class ComplaintStatus {

    /**
     * 投诉状态id
     */
    @ApiParam(value = "投诉状态id", required = true)
    private Integer complaintStatusId;

    /**
     * 投诉状态类型名称
     */
    @ApiParam(value = "投诉状态类型名称")
    private String complaintStatusName;

}