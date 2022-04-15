package xyz.slienceme.project_shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 投诉表
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Data
@ApiModel(value = "Admin管理员表的实体", reference = "Complaint")
public class Complaint {

    /**
     * 投诉id
     */
    @ApiParam(value = "投诉id", required = true)
    private Integer complaintId;

    /**
     * 订单id
     */
    @ApiParam(value = "订单id")
    private Integer ordersId;

    /**
     * 申请人
     */
    @ApiParam(value = "申请人")
    private Integer userId;

    /**
     * 备注-可以填写申诉结果
     */
    @ApiParam(value = "备注-可以填写申诉结果")
    private String remark;

    /**
     * 投诉状态
     */
    @ApiParam(value = "投诉状态")
    private Integer complaintStatus;

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