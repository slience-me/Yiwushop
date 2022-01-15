package xyz.slienceme.project_shop.mapper;

import xyz.slienceme.project_shop.dto.Admin;
/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}