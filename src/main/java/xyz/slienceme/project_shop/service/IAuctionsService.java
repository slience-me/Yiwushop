package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.vo.AuctionsVO;

/**
 * <p>
 * 竞拍场次表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface IAuctionsService {

    /**
     * 竞拍场次表列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    Result auctionsList(String accessToken,
                        Integer page,
                        Integer limit,
                        String keyword) throws Exception;

    /**
     * 根据竞拍场次信息添加
     */
    Result auctionsAdd(String accessToken, AuctionsVO auctionsVO) throws Exception;

    /**
     * 根据id删除竞拍场次
     */
    Result auctionsDel(String accessToken, Integer auctionsId) throws Exception;

    /**
     * 根据竞拍场次信息修改竞拍场次
     */
    Result auctionsPut(String accessToken, Auctions auctions) throws Exception;

}
