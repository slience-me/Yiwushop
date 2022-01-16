package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.vo.ComplaintVO;

/**
 * <p>
 * 投诉表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface IComplaintService {

    /**
     * 投诉表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    Result complaintList(String accessToken,
                        Integer page,
                        Integer limit,
                        String keyword) throws Exception;

    /**
     * 根据投诉信息添加
     */
    Result complaintAdd(String accessToken, ComplaintVO complaintVO) throws Exception;

    /**
     * 根据id删除投诉
     */
    Result complaintDel(String accessToken, Integer complaintId) throws Exception;
}
