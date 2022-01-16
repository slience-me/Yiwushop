package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Category;
import xyz.slienceme.project_shop.dto.Complaint;
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
     * 投诉表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    @Override
    public Result complaintList(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page,limit);
        List<HashMap<String, Object>> list = complaintMapper.selectList();//TODO 先留着不开发
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 根据投诉信息添加
     */
    @Override
    public Result complaintAdd(String accessToken, ComplaintVO complaintVO) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Complaint complaint = new Complaint();
        complaint.setOrdersId(complaintVO.getOrdersId());
        complaint.setUserId(unsign.getUserId());
        complaint.setRemark(complaint.getRemark());
        complaint.setComplaintStatus(1);//待受理
        complaintMapper.insertSelective(complaint);
        return Result.createBySuccessMessage("成功");
    }

    /**
     * 根据id删除投诉
     */
    @Override
    public Result complaintDel(String accessToken, Integer complaintId) throws Exception {
        Complaint complaint = complaintMapper.selectByPrimaryKey(complaintId);
        complaint.setIsDelete(1);
        complaintMapper.updateByPrimaryKeySelective(complaint);
        return Result.createBySuccessMessage("成功");
    }
}
