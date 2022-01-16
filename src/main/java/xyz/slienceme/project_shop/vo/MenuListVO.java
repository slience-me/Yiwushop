package xyz.slienceme.project_shop.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.slienceme.project_shop.dto.Menu;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MenuListVO extends Menu {

    private List<MenuListVO> children;

    private List<MenuListVO> operations;

    private Integer isCheck;

}
