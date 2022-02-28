package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.CommunityShow;
import xyz.slienceme.project_shop.vo.CommunityShowVO;

/**
 * <p>
 * 工艺展示表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface ICommunityShowService {

    Result addCommunity(String accessToken, CommunityShowVO communityShowVO) throws Exception;
    Result deleteCommunity(String accessToken, Integer id) throws Exception;
    Result updateCommunity(String accessToken, CommunityShow communityShow) throws Exception;
    Result community(String accessToken,
                   Integer page,
                   Integer limit,
                   String keyword) throws Exception;
}
