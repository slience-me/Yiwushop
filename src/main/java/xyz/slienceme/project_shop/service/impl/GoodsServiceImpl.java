package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.dto.Pawn;
import xyz.slienceme.project_shop.mapper.AuctionsMapper;
import xyz.slienceme.project_shop.mapper.GoodsMapper;
import xyz.slienceme.project_shop.mapper.PawnMapper;
import xyz.slienceme.project_shop.service.IGoodsService;
import xyz.slienceme.project_shop.utils.DateUtil;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.vo.AuctionsVO;
import xyz.slienceme.project_shop.vo.GoodsVO;
import xyz.slienceme.project_shop.vo.PawnVO;
import xyz.slienceme.project_shop.vo.TokenVO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private AuctionsMapper auctionsMapper;
    @Autowired
    private PawnMapper pawnMapper;

    /**
     * 查询商品列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     * @return
     * @throws Exception
     */
    @Override
    public Result goodsList(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = goodsMapper.selectList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 查询上架商品列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     * @return
     * @throws Exception
     */
    @Override
    public Result goodsOnList(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = goodsMapper.selectOnList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 查询未上架商品列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     * @return
     * @throws Exception
     */
    @Override
    public Result goodsNoList(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = goodsMapper.selectNoList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 查询已售商品列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    @Override
    public Result goodsDoneList(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = goodsMapper.selectDoneList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 根据商品信息添加商品
     *
     * @param accessToken
     * @param goodsVO
     * @return
     * @throws Exception
     */
    @Override
    public Result goodsAdd(String accessToken, GoodsVO goodsVO) throws Exception {
        Goods goods = new Goods();
        goods.setGoodsName(goodsVO.getGoodsName());
        //goods.setGoodsPrice(goodsVO.getGoodsPrice());
//        goods.setPriceNow(goodsVO.getGoodsPrice());
        //goods.setPriceUserId(goodsVO.getUserId());//默认自己
        goods.setGoodsInfo(goodsVO.getGoodsInfo());
        goods.setStateOn(0);//默认不上架
        goods.setCategoryId(goodsVO.getCategoryId());
        goods.setUserId(goodsVO.getUserId());
        goods.setGoodsImgId(0);//TODO 先留空
        goodsMapper.insertSelective(goods);
        return Result.createBySuccessMessage("成功");
    }

    /**
     * 根据id删除商品
     *
     * @param accessToken
     * @param goodsId
     * @return
     * @throws Exception
     */
    @Override
    public Result goodsDel(String accessToken, Integer goodsId) throws Exception {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        goods.setIsDelete(1);
        goodsMapper.updateByPrimaryKeySelective(goods);
        return Result.createBySuccessMessage("成功");
    }

    /**
     * 根据商品信息修改商品
     *
     * @param accessToken
     * @param goods
     * @return
     * @throws Exception
     */
    @Override
    public Result goodsPut(String accessToken, Goods goods) throws Exception {
        Goods goods1 = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        System.out.println("goods1 = " + goods1);
        if (Objects.isNull(goods1)) {
            return Result.createByErrorMessage("商品不存在");
        }
        goodsMapper.updateByPrimaryKeySelective(goods);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result stateOn(String accessToken, AuctionsVO auctionsVO) throws Exception {
        Goods goods1 = goodsMapper.selectByPrimaryKey(auctionsVO.getGoodsId());
        System.out.println("goods1 = " + goods1);
        if (Objects.isNull(goods1)) {
            return Result.createByErrorMessage("商品不存在");
        } else {
            goods1.setStateOn(1);//上架拍卖
        }
        goodsMapper.updateByPrimaryKeySelective(goods1);
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Auctions auctions = new Auctions();
        auctions.setAuctionsName(auctionsVO.getAuctionsName());
        auctions.setGoodsId(auctionsVO.getGoodsId());
        auctions.setStart(DateUtil.StringToLocalDateTime(auctionsVO.getStartTime()));
        auctions.setEnd(DateUtil.StringToLocalDateTime(auctionsVO.getEndTime()));
        auctions.setCreatedBy(unsign.getUserId());
        auctions.setStartingPrice(auctionsVO.getStartingPrice());
        auctions.setPresentPerson(0);//默认0
        auctions.setPresentPrice(BigDecimal.valueOf(0));//默认0
        auctions.setFixedPrice(BigDecimal.valueOf(0));//默认0
        auctionsMapper.insertSelective(auctions);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result stateOnToPawn(String accessToken, PawnVO pawnVO) throws Exception {
        Goods goods1 = goodsMapper.selectByPrimaryKey(pawnVO.getGoodsId());
        System.out.println("goods1 = " + goods1);
        if (Objects.isNull(goods1)) {
            return Result.createByErrorMessage("商品不存在");
        } else {
            goods1.setStateOn(3);//上架典当
        }
        goodsMapper.updateByPrimaryKeySelective(goods1);
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Pawn pawn = new Pawn();
        pawn.setPawnName(pawnVO.getPawnName());
        pawn.setGoodsId(pawnVO.getGoodsId());
        pawn.setStart(DateUtil.StringToLocalDateTime(pawnVO.getStartTime()));
        pawn.setEnd(DateUtil.StringToLocalDateTime(pawnVO.getEndTime()));
        pawn.setStartingPrice(pawnVO.getFixedPrice());
        pawn.setCreatedBy(unsign.getUserId());

        pawn.setStartingPrice(BigDecimal.valueOf(0));//默认0
        pawn.setPresentPerson(0);//默认0
        pawn.setPresentPrice(BigDecimal.valueOf(0));//默认0
        pawnMapper.insertSelective(pawn);
        return Result.createBySuccessMessage("成功");
    }
}
