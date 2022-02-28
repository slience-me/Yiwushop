package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.vo.AuctionsVO;
import xyz.slienceme.project_shop.vo.GoodsVO;
import xyz.slienceme.project_shop.vo.PawnVO;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface IGoodsService {

    /**
     * 查询商品列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    Result goodsList(String accessToken,
                     Integer page,
                     Integer limit,
                     String keyword) throws Exception;

    /**
     * 查询上架商品列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    Result goodsOnList(String accessToken,
                       Integer page,
                       Integer limit,
                       String keyword) throws Exception;

    /**
     * 查询未上架商品列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    Result goodsNoList(String accessToken,
                       Integer page,
                       Integer limit,
                       String keyword) throws Exception;

    /**
     * 查询已售商品列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    Result goodsDoneList(String accessToken,
                         Integer page,
                         Integer limit,
                         String keyword) throws Exception;

    /**
     * 查询典当商品列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    Result goodsPawnList(String accessToken,
                         Integer page,
                         Integer limit,
                         String keyword) throws Exception;

    /**
     * 根据商品信息添加商品
     */
    Result goodsAdd(String accessToken, GoodsVO goodsVO) throws Exception;

    /**
     * 根据id删除商品
     */
    Result goodsDel(String accessToken, Integer goodsId) throws Exception;

    /**
     * 根据商品信息修改商品
     */
    Result goodsPut(String accessToken, Goods goods) throws Exception;

    /**
     * 上架商品拍卖
     *
     * @param accessToken
     * @return
     */
    Result stateOn(String accessToken, AuctionsVO auctionsVO) throws Exception;

    /**
     * 上架商品典当
     *
     * @param accessToken
     * @return
     */
    Result stateOnToPawn(String accessToken, PawnVO pawnVO) throws Exception;

}
