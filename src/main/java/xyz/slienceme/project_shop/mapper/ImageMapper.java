package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.slienceme.project_shop.dto.Image;

/**
 * <p>
 * 图片表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Mapper
public interface ImageMapper {
    int deleteByPrimaryKey(Integer imageId);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer imageId);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}