package xyz.slienceme.project_shop.vo;

import lombok.Data;

@Data
public class ComplaintVO {

    /**
     * 订单id
     */
    private Integer ordersId;

    /**
     * 备注-可以填写申诉结果
     */
    private String remark;

}