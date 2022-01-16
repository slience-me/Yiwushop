package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.ComplaintStatus;
import xyz.slienceme.project_shop.mapper.ComplaintMapper;
import xyz.slienceme.project_shop.mapper.ComplaintStatusMapper;
import xyz.slienceme.project_shop.service.IComplaintStatusService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 投诉状态类型表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Service
public class ComplaintStatusServiceImpl implements IComplaintStatusService {

    @Autowired
    private ComplaintStatusMapper complaintStatusMapper;

    @Override
    public Result complaintTypeList() throws Exception {
        PageHelper.startPage(1, 10);
        List<HashMap<String, Object>> list = complaintStatusMapper.selectList();
        return Result.createBySuccess(new PageInfo<>(list));
    }
}
