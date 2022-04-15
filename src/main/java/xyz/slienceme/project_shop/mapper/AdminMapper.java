package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.Admin;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Mapper
public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    /**
     * 根据用户名密码查询admin
     *
     * @param username 用户名
     * @param password      密码
     */
    HashMap<String, Object> selectByNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * 查询管理员列表
     *
     * @param keyword 关键词
     */
    List<HashMap<String, Object>> selectList(@Param("keyword") String keyword);

    /**
     * 根据管理员名称查询管理员
     *
     * @param username 用户名
     */
    Admin selectByName(@Param("username") String username);

    /**
     * 查询是否绑定管理员
     *
     * @param roleId 角色id
     */
    List<Admin> selectByRoleId(@Param("roleId") Integer roleId);
}