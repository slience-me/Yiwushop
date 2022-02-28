package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.CommunityShow;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 工艺展示表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Mapper
public interface CommunityShowMapper {
    int deleteByPrimaryKey(Integer showId);

    int insert(CommunityShow record);

    int insertSelective(CommunityShow record);

    CommunityShow selectByPrimaryKey(Integer showId);

    int updateByPrimaryKeySelective(CommunityShow record);

    int updateByPrimaryKey(CommunityShow record);

    List<HashMap<String, Object>> selectList(@Param("keyword") String keyword);
}