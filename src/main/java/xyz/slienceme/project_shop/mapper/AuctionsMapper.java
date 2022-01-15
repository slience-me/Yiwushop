package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.slienceme.project_shop.dto.Auctions;

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
}