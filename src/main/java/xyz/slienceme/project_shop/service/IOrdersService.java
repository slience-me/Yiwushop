package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;

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
    Result ordersList(String accessToken,
                               Integer page,
                               Integer limit,
                               String keyword) throws Exception;

}
