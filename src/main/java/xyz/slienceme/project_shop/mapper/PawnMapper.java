package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.Pawn;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PawnMapper {

    int deleteByPrimaryKey(Integer auctionsId);
    int insert(Pawn record);
    int insertSelective(Pawn record);
    Pawn selectByPrimaryKey(Integer auctionsId);
    int updateByPrimaryKeySelective(Pawn record);
    int updateByPrimaryKey(Pawn record);

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

    List<HashMap<String, Object>> selectConditionList(@Param("goodsId") Integer goodsId,
                                                      @Param("pawnName") String pawnName);
}