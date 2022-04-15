package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.dto.Pawn;
import xyz.slienceme.project_shop.vo.AuctionsVO;
import xyz.slienceme.project_shop.vo.PawnScheduleVO;
import xyz.slienceme.project_shop.vo.PawnVO;

/**
 * <p>
 * 竞拍场次表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
public interface IAuctionsService {


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

    /**
     * 根据竞拍场次信息添加
     */
    Result pawnAdd(String accessToken, PawnVO pawnVO) throws Exception;

    /**
     * 根据id删除竞拍场次
     */
    Result pawnDel(String accessToken, Integer auctionsId) throws Exception;

    /**
     * 根据竞拍场次信息修改竞拍场次
     */
    Result pawnPut(String accessToken, Pawn pawn) throws Exception;

    Result selectByPrimaryKey(String accessToken, Integer auctionsId);

    Result auctions(String accessToken, Integer pageNo, Integer pageSize, Integer goodsId, String auctionsName);

    Result pawn(String accessToken, Integer pageNo, Integer pageSize, Integer goodsId, String pawnName);

    Result selectByPrimaryKeyPawn(String accessToken, Integer auctionsId);

}
