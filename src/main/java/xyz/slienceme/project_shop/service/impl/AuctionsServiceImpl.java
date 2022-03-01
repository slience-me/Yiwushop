package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.AuctionSchedule;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.dto.Pawn;
import xyz.slienceme.project_shop.mapper.AuctionsMapper;
import xyz.slienceme.project_shop.mapper.GoodsMapper;
import xyz.slienceme.project_shop.mapper.PawnMapper;
import xyz.slienceme.project_shop.service.IAuctionsService;
import xyz.slienceme.project_shop.utils.DateUtil;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.vo.AuctionsVO;
import xyz.slienceme.project_shop.vo.PawnScheduleVO;
import xyz.slienceme.project_shop.vo.PawnVO;
import xyz.slienceme.project_shop.vo.TokenVO;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 竞拍场次表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Service
public class AuctionsServiceImpl implements IAuctionsService {

    @Autowired
    private AuctionsMapper auctionsMapper;
    @Autowired
    private PawnMapper pawnMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 竞拍场次表列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    @Override
    public Result auctionsList(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = auctionsMapper.selectList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 根据竞拍场次信息添加
     */
    @Override
    public Result auctionsAdd(String accessToken, AuctionsVO auctionsVO) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Auctions auctions = new Auctions();
        auctions.setGoodsId(auctionsVO.getGoodsId());
        auctions.setAuctionsName(auctionsVO.getAuctionsName());
        auctions.setStart(DateUtil.StringToLocalDateTime(auctionsVO.getStartTime()));
        auctions.setEnd(DateUtil.StringToLocalDateTime(auctionsVO.getEndTime()));
        auctions.setFixedPrice(auctionsVO.getFixedPrice());
        auctions.setPresentPerson(auctionsVO.getPresentPerson());
        auctions.setPresentPrice(auctionsVO.getPresentPrice());
        auctions.setStartingPrice(auctionsVO.getStartingPrice());
        auctions.setCreatedBy(unsign.getUserId());
        auctionsMapper.insertSelective(auctions);
        return Result.createBySuccessMessage("成功");
    }

    /**
     * 根据id删除竞拍场次
     */
    @Override
    public Result auctionsDel(String accessToken, Integer auctionsId) throws Exception {
        Auctions auctions = auctionsMapper.selectByPrimaryKey(auctionsId);
        auctions.setIsDelete(1);
        auctionsMapper.updateByPrimaryKeySelective(auctions);
        return Result.createBySuccessMessage("成功");
    }

    /**
     * 根据竞拍场次信息修改竞拍场次
     */
    @Override
    public Result auctionsPut(String accessToken, Auctions auctions) throws Exception {
        Auctions auctions1 = auctionsMapper.selectByPrimaryKey(auctions.getAuctionsId());
        System.out.println("auctions1 = " + auctions1);
        if (Objects.isNull(auctions1)) {
            return Result.createByErrorMessage("该拍卖场次不存在");
        }
        auctionsMapper.updateByPrimaryKeySelective(auctions);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result pawnList(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = pawnMapper.selectList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    @Override
    public Result pawnAdd(String accessToken, PawnVO pawnVO) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Pawn pawn = new Pawn();
        pawn.setGoodsId(pawnVO.getGoodsId());
        pawn.setPawnName(pawnVO.getPawnName());
        pawn.setStart(DateUtil.StringToLocalDateTime(pawnVO.getStartTime()));
        pawn.setEnd(DateUtil.StringToLocalDateTime(pawnVO.getEndTime()));
        pawn.setFixedPrice(pawnVO.getFixedPrice());
        pawn.setPresentPerson(pawnVO.getPresentPerson());
        pawn.setPresentPrice(pawnVO.getPresentPrice());
        pawn.setStartingPrice(pawnVO.getStartingPrice());
        pawn.setCreatedBy(unsign.getUserId());
        pawnMapper.insertSelective(pawn);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result pawnDel(String accessToken, Integer pawnId) throws Exception {
        Pawn pawn = pawnMapper.selectByPrimaryKey(pawnId);
        pawn.setIsDelete(1);
        pawnMapper.updateByPrimaryKeySelective(pawn);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result pawnPut(String accessToken, Pawn pawn) throws Exception {
        Pawn pawn1 = pawnMapper.selectByPrimaryKey(pawn.getAuctionsId());
        System.out.println("auctions1 = " + pawn1);
        if (Objects.isNull(pawn1)) {
            return Result.createByErrorMessage("该场次不存在");
        }
        pawnMapper.updateByPrimaryKeySelective(pawn);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result doPawn(String accessToken, PawnScheduleVO pawnScheduleVO) throws Exception {
        Pawn pawn = pawnMapper.selectByPrimaryKey(pawnScheduleVO.getPawnId());
        Goods goods1 = goodsMapper.selectByPrimaryKey(pawn.getGoodsId());
        System.out.println("pawn = " + pawn);
        if (Objects.isNull(pawn)) {
            return Result.createByErrorMessage("订单不存在");
        }
        pawn.setPresentPerson(pawnScheduleVO.getUserId());
        pawnMapper.updateByPrimaryKeySelective(pawn);
        goods1.setStateOn(3);//设置已售
        goodsMapper.updateByPrimaryKeySelective(goods1);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result selectByPrimaryKey(String accessToken, Integer auctionsId) {
        Auctions auctions= auctionsMapper.selectByPrimaryKey(auctionsId);
        if (Objects.isNull(auctions)){
            return Result.createByErrorMessage("id不正确");
        }
        return Result.createBySuccess(auctions);
    }

    @Override
    public Result getData(String accessToken, Integer pageNo, Integer pageSize, Integer goodsId, String auctionsName) {
        PageHelper.startPage(pageNo, pageSize);
        List<HashMap<String, Object>> list = auctionsMapper.selectConditionList(goodsId, auctionsName);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    @Override
    public Result getPawnData(String accessToken, Integer pageNo, Integer pageSize, Integer goodsId, String pawnName) {
        PageHelper.startPage(pageNo, pageSize);
        List<HashMap<String, Object>> list = pawnMapper.selectConditionList(goodsId, pawnName);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    @Override
    public Result selectByPrimaryKeyPawn(String accessToken, Integer auctionsId) {
        Pawn pawn= pawnMapper.selectByPrimaryKey(auctionsId);
        if (Objects.isNull(pawn)){
            return Result.createByErrorMessage("id不正确");
        }
        return Result.createBySuccess(pawn);
    }
}
