package xyz.slienceme.project_shop.vo;

import lombok.Data;

@Data
public class AdminVO {

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
     * 角色ID
     */
    private Integer roleId;

}
