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
import xyz.slienceme.project_shop.vo.AuctionScheduleVO;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    private AuctionsMapper auctionsMapper;

    /**
     * 拍卖过程表列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     */
    @Override
    public Result auctionScheduleList(String accessToken, Integer page, Integer limit, Integer goodsId) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = auctionScheduleMapper.selectList(goodsId);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 根据拍卖过程信息添加
     */
    @Override
    public Result auctionScheduleAdd(String accessToken, AuctionScheduleVO auctionScheduleVO) throws Exception {
        Auctions auctions = auctionsMapper.selectByPrimaryKey(auctionScheduleVO.getAuctionsId());
        AuctionSchedule auctionSchedule = new AuctionSchedule();
        auctionSchedule.setGoodsId(auctions.getGoodsId());
        auctionSchedule.setUserId(auctionScheduleVO.getUserId());
        auctionSchedule.setAuctionSchedulePrice(auctionScheduleVO.getAuctionSchedulePrice());
        int flag = auctionScheduleMapper.insertSelective(auctionSchedule);
        if (flag > 0) {
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    /**
     * 开始竞拍
     *
     * @param accessToken
     * @return
     * @throws Exception
     */
    @Override
    public Result doAuctions(String accessToken, AuctionScheduleVO auctionScheduleVO) throws Exception {
        Auctions auctions = auctionsMapper.selectByPrimaryKey(auctionScheduleVO.getAuctionsId());
        if (Objects.isNull(auctions)) {
            return Result.createByErrorMessage("场次不存在");
        }
        if (auctionScheduleVO.getAuctionSchedulePrice().compareTo(auctions.getPresentPrice()) > -1) {
            AuctionSchedule auctionSchedule = new AuctionSchedule();
            auctionSchedule.setGoodsId(auctions.getGoodsId());
            auctionSchedule.setUserId(auctionScheduleVO.getUserId());
            auctionSchedule.setAuctionSchedulePrice(auctionScheduleVO.getAuctionSchedulePrice());
            int flag1 = auctionScheduleMapper.insertSelective(auctionSchedule);
            auctions.setPresentPrice(auctionScheduleVO.getAuctionSchedulePrice());
            auctions.setPresentPerson(auctionScheduleVO.getUserId());
            int flag2 = auctionsMapper.updateByPrimaryKeySelective(auctions);
            if (flag1 > 0 && flag2 > 0) {
                return Result.createBySuccessMessage("成功");
            } else {
                return Result.createByErrorMessage("操作失败请稍后重试");
            }
        } else {
            return Result.createByErrorMessage("出价异常");
        }
    }
}
