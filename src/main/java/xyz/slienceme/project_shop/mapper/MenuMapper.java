package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.Menu;
import xyz.slienceme.project_shop.vo.MenuListVO;

import java.util.List;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 查询菜单
     * @param level 等级
     * @param type 类型
     * @param parentId 父级
     * @param roleIdSign 标识
     */
    List<MenuListVO> selectAll(@Param("level") Integer level,
                               @Param("type") Integer type,
                               @Param("parentId") Integer parentId,
                               @Param("roleIdSign") Integer roleIdSign);

    /**
     *
     * @param roleId 角色id
     */
    List<MenuListVO> selectAllByRoleId(@Param("roleId") Integer roleId);

}