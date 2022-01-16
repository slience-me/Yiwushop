package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;

/**
 * <p>
 * 投诉状态类型表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface IComplaintStatusService {

    /**
     * 查询投诉类型
     * @return
     * @throws Exception
     */
    Result complaintTypeList() throws Exception;

}
