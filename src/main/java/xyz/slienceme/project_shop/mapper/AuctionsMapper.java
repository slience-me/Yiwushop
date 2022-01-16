package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.Auctions;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 竞拍场次表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Mapper
public interface AuctionsMapper {
    int deleteByPrimaryKey(Integer auctionsId);

    int insert(Auctions record);

    int insertSelective(Auctions record);

    Auctions selectByPrimaryKey(Integer auctionsId);

    int updateByPrimaryKeySelective(Auctions record);

    int updateByPrimaryKey(Auctions record);

    /**
     * 查询竞拍场次列表
     *
     * @param keyword 关键词
     */
    List<HashMap<String, Object>> selectList(@Param("keyword") String keyword);

    /**
     * 查询未结束场次列表
     *
     * @param nowTime 当前时间
     */
    List<HashMap<String, Object>> selectUndoneList(@Param("nowTime") String nowTime);

}