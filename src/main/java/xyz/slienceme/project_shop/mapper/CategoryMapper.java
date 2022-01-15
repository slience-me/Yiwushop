package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.slienceme.project_shop.dto.Category;

/**
 * <p>
 * 物品类型表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}