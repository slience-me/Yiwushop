package xyz.slienceme.project_shop.mapper;

import xyz.slienceme.project_shop.dto.User;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}