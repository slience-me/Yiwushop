package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 投诉表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Data
public class Complaint {

    /**
     * 投诉id
     */
    private Integer complaintId;

    /**
     * 订单id
     */
    private Integer ordersId;

    /**
     * 申请人
     */
    private Integer userId;

    /**
     * 备注-可以填写申诉结果
     */
    private String remark;

    /**
     * 投诉状态
     */
    private Integer complaintStatus;

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