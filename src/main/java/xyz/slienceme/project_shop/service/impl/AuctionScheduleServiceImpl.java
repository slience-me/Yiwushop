package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.AuctionSchedule;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.mapper.AuctionScheduleMapper;
import xyz.slienceme.project_shop.mapper.AuctionsMapper;
import xyz.slienceme.project_shop.service.IAuctionScheduleService;
import xyz.slienceme.project_shop.utils.DateUtil;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.vo.AuctionScheduleVO;
import xyz.slienceme.project_shop.vo.TokenVO;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-16
 */
@Service
public class AuctionScheduleServiceImpl implements IAuctionScheduleService {

    @Autowired
    private AuctionScheduleMapper auctionScheduleMapper;

    /**
     * 拍卖过程表列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     */
    @Override
    public Result auctionScheduleList(String accessToken, Integer page, Integer limit) throws Exception {
        PageHelper.startPage(page,limit);
        List<HashMap<String, Object>> list = auctionScheduleMapper.selectList();
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 根据拍卖过程信息添加
     */
    @Override
    public Result auctionScheduleAdd(String accessToken, AuctionScheduleVO auctionScheduleVO) throws Exception {
        AuctionSchedule auctionSchedule = new AuctionSchedule();
        auctionSchedule.setGoodsId(auctionScheduleVO.getGoodsId());
        auctionSchedule.setUserId(auctionScheduleVO.getUserId());
        auctionSchedule.setAuctionSchedulePrice(auctionScheduleVO.getAuctionSchedulePrice());
        auctionScheduleMapper.insertSelective(auctionSchedule);
        return Result.createBySuccessMessage("成功");
    }
}
