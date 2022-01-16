package xyz.slienceme.project_shop.vo;

import lombok.Data;
import xyz.slienceme.project_shop.dto.Role;

import java.util.List;

@Data
public class RoleVO extends Role {

    private List<MenuVO> menuList;

}
