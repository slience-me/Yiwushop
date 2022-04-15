package xyz.slienceme.project_shop.service;

import xyz.slienceme.project_shop.common.Result;

/**
 * <p>
 * 管理员操作日志 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-04-10
 */

public interface IAdminLogsService {

    /**
     * 查询操作日志
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param adminId     管理员Id
     */
    Result logList(String accessToken,
                   Integer page,
                   Integer limit,
                   Integer adminId) throws Exception;

    /**
     * 删除操作日志
     *
     * @param accessToken 请求token
     * @param adminLogsId 日志id
     */
    Result logDel(String accessToken,
                  Integer adminLogsId) throws Exception;


}