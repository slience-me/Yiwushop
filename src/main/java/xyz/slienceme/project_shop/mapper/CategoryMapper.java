package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.Admin;
import xyz.slienceme.project_shop.dto.Category;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 物品类型表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 查询物品类型列表
     *
     * @param keyword 关键词
     */
    List<HashMap<String, Object>> selectList(@Param("keyword") String keyword);

    /**
     * 根据物品类型名称查询物品类型
     *
     * @param categoryName 物品类型名
     */
    Category selectByName(@Param("categoryName") String categoryName);

}