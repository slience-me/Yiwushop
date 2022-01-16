package xyz.slienceme.project_shop.vo;

import lombok.Data;

@Data
public class RoleUpdateVO {

    private Integer roleId;
    private String roleName;
    private Integer[] menuIds;

}
