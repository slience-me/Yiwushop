package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.Role;
import xyz.slienceme.project_shop.vo.RoleVO;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface RoleMapper {

    int deleteByPrimaryKey(Integer roleId);
    int insert(Role record);
    int insertSelective(Role record);
    Role selectByPrimaryKey(Integer roleId);
    int updateByPrimaryKeySelective(Role record);
    int updateByPrimaryKey(Role record);

    /**
     * 查询角色列表
     */
    List<RoleVO> selectList();

    /**
     * 查询名称时候存在
     * @param roleName 角色名称
     */
    Role selectByName(@Param("roleName") String roleName);

    /**
     * 查询角色列表
     * @param keyword 关键词
     */
    List<HashMap<String, Object>> selectAll(@Param("keyword") String keyword);
}