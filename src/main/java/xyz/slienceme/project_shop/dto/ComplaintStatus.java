package xyz.slienceme.project_shop.dto;

import lombok.Data;

/**
 * <p>
 * 投诉状态类型表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Data
public class ComplaintStatus {

    /**
     * 投诉状态id
     */
    private Integer complaintStatusId;

    /**
     * 投诉状态类型名称
     */
    private String complaintStatusName;

}