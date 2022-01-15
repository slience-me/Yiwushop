package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Admin;
import xyz.slienceme.project_shop.vo.AdminVO;
import xyz.slienceme.project_shop.vo.LoginVO;
import xyz.slienceme.project_shop.vo.PwdVO;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface IAdminService {

    /**
     * 查询管理员列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    Result adminList(String accessToken,
                     Integer page,
                     Integer limit,
                     String keyword) throws Exception;

    /**
     * 管理员登陆
     *
     * @param loginVO 登录信息对象
     * @throws Exception
     */
    Result login(LoginVO loginVO) throws Exception;


    /**
     * 根据管理员信息添加管理员
     */
    Result adminAdd(String accessToken, AdminVO adminVO) throws Exception;

    /**
     * 根据id删除管理员
     */
    Result adminDel(String accessToken, Integer adminId) throws Exception;

    /**
     * 根据管理员信息修改管理员
     */
    Result adminPut(String accessToken, Admin admin) throws Exception;

    /**
     * 重置密码
     *
     * @param accessToken 请求token
     * @param adminId     管理员id
     */
    Result adminPwd(String accessToken, Integer adminId) throws Exception;

    /**
     * 修改密码
     *
     * @param accessToken 请求token
     * @param pwdVO       密码封装对象
     */
    Result adminPwdPut(String accessToken, PwdVO pwdVO) throws Exception;

    /**
     * admin退出登陆
     *
     * @param accessToken 请求token
     */
    Result adminOut(String accessToken) throws Exception;

}
