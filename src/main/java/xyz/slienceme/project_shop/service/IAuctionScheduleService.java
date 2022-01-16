package xyz.slienceme.project_shop.service;

import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.vo.AuctionScheduleVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-16
 */
public interface IAuctionScheduleService {

    /**
     * 拍卖过程表列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     */
    Result auctionScheduleList(String accessToken,
                        Integer page,
                        Integer limit) throws Exception;

    /**
     * 根据拍卖过程信息添加
     */
    Result auctionScheduleAdd(String accessToken, AuctionScheduleVO auctionScheduleVO) throws Exception;
}
