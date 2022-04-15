package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Complaint;
import xyz.slienceme.project_shop.vo.ComplaintVO;

/**
 * <p>
 * 投诉表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
public interface IComplaintService {

    /**
     * 根据投诉信息添加
     */
    Result complaintAdd(String accessToken, ComplaintVO complaintVO) throws Exception;

    /**
     * 根据id删除投诉
     */
    Result complaintDel(String accessToken, Integer complaintId) throws Exception;

    Result complaint(String accessToken, Integer pageNo, Integer pageSize, String ordersId, Integer userId, Integer complaintStatus);

    Result selectByPrimaryKey(String accessToken, Integer complaintId);

    Result complaintPut(String accessToken, Complaint complaint);
}
