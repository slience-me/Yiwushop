package xyz.slienceme.project_shop.dto;

import lombok.Data;

/**
 * <p>
 * 菜单角色表
 * </p>
 *
 * @author slience_me
 * @since 2021-08-09
 */
@Data
public class MenuRole {

    /**
     * 自增ID
     */
    private Integer menuRoleId;

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 角色ID
     */
    private Integer roleId;


}
