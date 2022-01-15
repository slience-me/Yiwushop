package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.User;
import xyz.slienceme.project_shop.vo.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface IUserService {

    /**
     * 微信登陆
     *
     * @param openid     openid
     * @param sessionKey sessionkey
     * @return 微信登陆JSON数据
     * @throws Exception
     */
    Result getCode(String openid, String sessionKey) throws Exception;

    Result addMember(String accessToken, UserVO userVO) throws Exception;

    Result deleteMember(String accessToken, Integer id) throws Exception;

    Result updateMember(String accessToken, User user) throws Exception;

    Result members(String accessToken,
                   Integer page,
                   Integer limit,
                   String keyword,
                   String openid) throws Exception;

}
