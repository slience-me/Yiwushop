package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.slienceme.project_shop.dto.MenuRole;

@Mapper
public interface MenuRoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    /**
     * 删除角色关联
     *
     * @param roleId 角色id
     */
    int delByRoleId(Integer roleId);
}