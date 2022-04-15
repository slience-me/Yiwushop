package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.AdminLogs;
import xyz.slienceme.project_shop.mapper.AdminLogsMapper;
import xyz.slienceme.project_shop.service.IAdminLogsService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 管理员操作日志 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-19
 */
@Service
public class AdminLogsServiceImpl implements IAdminLogsService {

    @Autowired
    private AdminLogsMapper adminLogsMapper;

    @Override
    public Result logList(String accessToken, Integer page, Integer limit, Integer adminId) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = adminLogsMapper.selectList(adminId);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    @Override
    public Result logDel(String accessToken, Integer adminLogsId) throws Exception {
        AdminLogs adminLogs = adminLogsMapper.selectByPrimaryKey(adminLogsId);
        adminLogs.setIsDelete(1);
        adminLogsMapper.updateByPrimaryKeySelective(adminLogs);
        return Result.createBySuccessMessage("成功");
    }
}