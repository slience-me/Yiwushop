package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Data
public class Admin {
    /**
     * 管理员id
     */
    private Integer adminId;

    /**
     * 管理员名称
     */
    private String adminName;

    /**
     * 管理员账号
     */
    private String adminNumber;

    /**
     * 管理员密码
     */
    private String adminPwd;

    /**
     * 角色id
     */
    private Integer roleId;

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