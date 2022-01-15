package xyz.slienceme.project_shop.mapper;

import xyz.slienceme.project_shop.dto.CommunityShow;
/**
 * <p>
 * 工艺展示表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface CommunityShowMapper {
    int deleteByPrimaryKey(Integer showId);

    int insert(CommunityShow record);

    int insertSelective(CommunityShow record);

    CommunityShow selectByPrimaryKey(Integer showId);

    int updateByPrimaryKeySelective(CommunityShow record);

    int updateByPrimaryKey(CommunityShow record);
}