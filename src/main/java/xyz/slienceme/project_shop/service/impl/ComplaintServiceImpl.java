package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Category;
import xyz.slienceme.project_shop.dto.Complaint;
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.mapper.ComplaintMapper;
import xyz.slienceme.project_shop.service.IComplaintService;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.vo.ComplaintVO;
import xyz.slienceme.project_shop.vo.TokenVO;

import javax.xml.ws.Action;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 投诉表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Service
public class ComplaintServiceImpl implements IComplaintService {

    @Autowired
    private ComplaintMapper complaintMapper;

    /**
     * 根据投诉信息添加
     */
    @Override
    public Result complaintAdd(String accessToken, ComplaintVO complaintVO) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Complaint complaint = new Complaint();
        complaint.setOrdersId(complaintVO.getOrdersId());
        complaint.setUserId(unsign.getUserId());
        complaint.setRemark(complaintVO.getRemark());
        complaint.setComplaintStatus(1);//待受理
        complaint.setCreatedBy(unsign.getUserId());
        int flag = complaintMapper.insertSelective(complaint);
        if (flag > 0) {
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    /**
     * 根据id删除投诉
     */
    @Override
    public Result complaintDel(String accessToken, Integer complaintId) throws Exception {
        Complaint complaint = complaintMapper.selectByPrimaryKey(complaintId);
        complaint.setIsDelete(1);
        int flag = complaintMapper.updateByPrimaryKeySelective(complaint);
        if (flag > 0) {
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    @Override
    public Result complaint(String accessToken, Integer pageNo, Integer pageSize, String ordersId, Integer userId, Integer complaintStatus) {
        PageHelper.startPage(pageNo, pageSize);
        List<HashMap<String, Object>> list = complaintMapper.selectConditionList(ordersId, userId, complaintStatus);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    @Override
    public Result selectByPrimaryKey(String accessToken, Integer complaintId) {
        Complaint complaint = complaintMapper.selectByPrimaryKey(complaintId);
        if (Objects.isNull(complaint)) {
            return Result.createByErrorMessage("id不正确");
        }
        return Result.createBySuccess(complaint);
    }

    @Override
    public Result complaintPut(String accessToken, Complaint complaint) {
        Complaint complaint1 = complaintMapper.selectByPrimaryKey(complaint.getComplaintId());
        if (Objects.isNull(complaint1)) {
            return Result.createByErrorMessage("投诉单不存在");
        }
        int flag = complaintMapper.updateByPrimaryKeySelective(complaint);
        if (flag > 0) {
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }
}
