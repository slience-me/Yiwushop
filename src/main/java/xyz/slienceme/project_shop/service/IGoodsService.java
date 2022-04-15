package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.vo.GoodsVO;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
public interface IGoodsService {

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

    Result selectByPrimaryKey(String accessToken, Integer goodsId);

    Result goods(String accessToken, Integer pageNo, Integer pageSize, String goodsName, String goodsInfo, Integer stateOn, Integer categoryId, Integer userId);
}
