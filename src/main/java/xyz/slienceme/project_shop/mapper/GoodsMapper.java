package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.Goods;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);
    Goods selectByPrimaryKey1(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    /**
     * 查询商品列表
     *
     * @param keyword 关键词
     */
    List<HashMap<String, Object>> selectList(@Param("keyword") String keyword);

    /**
     * 查询上架商品列表
     *
     * @param keyword 关键词
     */
    List<HashMap<String, Object>> selectOnList(@Param("keyword") String keyword);

    /**
     * 查询未上架商品列表
     *
     * @param keyword 关键词
     */
    List<HashMap<String, Object>> selectNoList(@Param("keyword") String keyword);

    /**
     * 查询已售商品列表
     *
     * @param keyword 关键词
     */
    List<HashMap<String, Object>> selectDoneList(@Param("keyword") String keyword);

    /**
     * 查询典当商品列表
     *
     * @param keyword 关键词
     */
    List<HashMap<String, Object>> selectPawnList(@Param("keyword") String keyword);


    List<HashMap<String, Object>> selectConditionList(@Param("goodsName") String goodsName,
                                                      @Param("goodsInfo") String goodsInfo,
                                                      @Param("stateOn") Integer stateOn,
                                                      @Param("categoryId") Integer categoryId,
                                                      @Param("userId") Integer userId);
}