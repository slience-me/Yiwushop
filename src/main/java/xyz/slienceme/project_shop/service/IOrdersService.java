package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.vo.OrdersVO;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface IOrdersService {

    /**
     * 订单列表
     * @param accessToken
     * @param page
     * @param limit
     * @param keyword
     * @return
     * @throws Exception
     */
    Result orders(String accessToken,
                               Integer page,
                               Integer limit,
                               String keyword) throws Exception;

    /**
     * 根据id删除订单
     */
    Result ordersDel(String accessToken, Integer ordersId) throws Exception;


    Result ordersAdd(String accessToken, OrdersVO ordersVO);
}
