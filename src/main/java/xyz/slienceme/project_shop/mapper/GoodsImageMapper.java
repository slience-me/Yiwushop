package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.GoodsImage;

import java.util.List;

@Mapper
public interface GoodsImageMapper {
    int deleteByPrimaryKey(Integer goodsImgId);

    int insert(GoodsImage record);

    int insertSelective(GoodsImage record);

    GoodsImage selectByPrimaryKey(Integer goodsImgId);

    int updateByPrimaryKeySelective(GoodsImage record);

    int updateByPrimaryKey(GoodsImage record);

    List<String> selectImageByGoodsId(@Param("goodsId") Integer goodsId);
}