package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.AuctionSchedule;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.mapper.AuctionScheduleMapper;
import xyz.slienceme.project_shop.mapper.AuctionsMapper;
import xyz.slienceme.project_shop.mapper.GoodsMapper;
import xyz.slienceme.project_shop.service.IAuctionScheduleService;
import xyz.slienceme.project_shop.utils.DateUtil;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.vo.AuctionScheduleVO;
import xyz.slienceme.project_shop.vo.TokenVO;

import java.math.BigDecimal;
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
    private GoodsMapper goodsMapper;

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

    /**
     * 开始竞拍
     * @param accessToken
     * @return
     * @throws Exception
     */
    @Override
    public Result doAuctions(String accessToken, AuctionScheduleVO auctionScheduleVO) throws Exception {
        Goods goods1 = goodsMapper.selectByPrimaryKey(auctionScheduleVO.getGoodsId());
        System.out.println("goods1 = " + goods1);
        if (Objects.isNull(goods1)) {
            return Result.createByErrorMessage("商品不存在");
        }
        if (auctionScheduleVO.getAuctionSchedulePrice().compareTo(goods1.getPriceNow())>-1){
            AuctionSchedule auctionSchedule = new AuctionSchedule();
            auctionSchedule.setGoodsId(auctionScheduleVO.getGoodsId());
            auctionSchedule.setUserId(auctionScheduleVO.getUserId());
            auctionSchedule.setAuctionSchedulePrice(auctionScheduleVO.getAuctionSchedulePrice());
            auctionScheduleMapper.insertSelective(auctionSchedule);
            goods1.setGoodsPrice(auctionScheduleVO.getAuctionSchedulePrice());
            goods1.setPriceUserId(auctionScheduleVO.getUserId());
            goodsMapper.updateByPrimaryKeySelective(goods1);
        } else {
            return Result.createByErrorMessage("出价异常");
        }
        return Result.createBySuccessMessage("成功");
    }
}
