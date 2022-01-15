package xyz.slienceme.project_shop.mapper;

import xyz.slienceme.project_shop.dto.Goods;
/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}