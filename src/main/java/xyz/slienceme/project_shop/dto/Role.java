package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author slience_me
 * @since 2021-08-09
 */
@Data
public class Role {

    /**
     * 自增id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 状态 0正常 1删除
     */
    private Integer roleStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;


}
