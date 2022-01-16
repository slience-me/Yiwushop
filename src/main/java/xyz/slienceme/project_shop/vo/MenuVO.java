package xyz.slienceme.project_shop.vo;

import lombok.Data;
import xyz.slienceme.project_shop.dto.Menu;

import java.util.List;

@Data
public class MenuVO extends Menu {

    private List<MenuVO> list;

}
