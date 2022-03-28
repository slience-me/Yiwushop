package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.dto.Pawn;
import xyz.slienceme.project_shop.mapper.AuctionsMapper;
import xyz.slienceme.project_shop.mapper.GoodsMapper;
import xyz.slienceme.project_shop.mapper.PawnMapper;
import xyz.slienceme.project_shop.service.IAuctionsService;
import xyz.slienceme.project_shop.utils.DateUtil;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.vo.AuctionsVO;
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
        int flag = auctionsMapper.insertSelective(auctions);
        if(flag > 0){
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    /**
     * 根据id删除竞拍场次
     */
    @Override
    public Result auctionsDel(String accessToken, Integer auctionsId) throws Exception {
        Auctions auctions = auctionsMapper.selectByPrimaryKey(auctionsId);
        auctions.setIsDelete(1);
        int flag = auctionsMapper.updateByPrimaryKeySelective(auctions);
        if(flag > 0){
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    /**
     * 根据竞拍场次信息修改竞拍场次
     */
    @Override
    public Result auctionsPut(String accessToken, Auctions auctions) throws Exception {
        Auctions auctions1 = auctionsMapper.selectByPrimaryKey(auctions.getAuctionsId());
        if (Objects.isNull(auctions1)) {
            return Result.createByErrorMessage("该拍卖场次不存在");
        }
        int flag = auctionsMapper.updateByPrimaryKeySelective(auctions);
        if(flag > 0){
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
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
        int flag = pawnMapper.insertSelective(pawn);
        if(flag > 0){
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    @Override
    public Result pawnDel(String accessToken, Integer pawnId) throws Exception {
        Pawn pawn = pawnMapper.selectByPrimaryKey(pawnId);
        pawn.setIsDelete(1);
        int flag = pawnMapper.updateByPrimaryKeySelective(pawn);
        if(flag > 0){
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    @Override
    public Result pawnPut(String accessToken, Pawn pawn) throws Exception {
        Pawn pawn1 = pawnMapper.selectByPrimaryKey(pawn.getAuctionsId());
        if (Objects.isNull(pawn1)) {
            return Result.createByErrorMessage("该场次不存在");
        }
        int flag = pawnMapper.updateByPrimaryKeySelective(pawn);
        if(flag > 0){
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
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
    public Result auctions(String accessToken, Integer pageNo, Integer pageSize, Integer goodsId, String auctionsName) {
        PageHelper.startPage(pageNo, pageSize);
        List<HashMap<String, Object>> list = auctionsMapper.selectConditionList(goodsId, auctionsName);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    @Override
    public Result pawn(String accessToken, Integer pageNo, Integer pageSize, Integer goodsId, String pawnName) {
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
