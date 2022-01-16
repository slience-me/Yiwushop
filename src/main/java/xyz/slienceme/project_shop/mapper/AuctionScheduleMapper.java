package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.AuctionSchedule;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  拍卖过程Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-16
 */
@Mapper
public interface AuctionScheduleMapper {
    int deleteByPrimaryKey(Integer auctionScheduleId);

    int insert(AuctionSchedule record);

    int insertSelective(AuctionSchedule record);

    AuctionSchedule selectByPrimaryKey(Integer auctionScheduleId);

    int updateByPrimaryKeySelective(AuctionSchedule record);

    int updateByPrimaryKey(AuctionSchedule record);

    /**
     * 查询拍卖过程列表
     *
     */
    List<HashMap<String, Object>> selectList();
}