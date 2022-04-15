package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Admin;
import xyz.slienceme.project_shop.dto.AdminLogs;
import xyz.slienceme.project_shop.dto.CommunityShow;
import xyz.slienceme.project_shop.dto.User;
import xyz.slienceme.project_shop.mapper.AdminLogsMapper;
import xyz.slienceme.project_shop.mapper.AdminMapper;
import xyz.slienceme.project_shop.mapper.CommunityShowMapper;
import xyz.slienceme.project_shop.service.ICommunityShowService;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.vo.CommunityShowVO;
import xyz.slienceme.project_shop.vo.TokenVO;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 工艺展示表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Service
public class CommunityShowServiceImpl implements ICommunityShowService {

    @Autowired
    private CommunityShowMapper communityShowMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminLogsMapper adminLogsMapper;

    @Override
    public Result communityAdd(String accessToken, CommunityShowVO communityShowVO) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        CommunityShow communityShow = new CommunityShow();
        communityShow.setShowName(communityShowVO.getShowName());
        communityShow.setShowImgId(communityShowVO.getShowImgId());
        communityShow.setCreatedBy(unsign.getUserId());
        int flag = communityShowMapper.insertSelective(communityShow);
        if (flag > 0) {
            adminLogsMapper.insertSelective(new AdminLogs(unsign.getUserId(), "新增公益展示 " + communityShow.getShowName()));
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    @Override
    public Result communityDel(String accessToken, Integer id) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        CommunityShow communityShow = communityShowMapper.selectByPrimaryKey(id);
        communityShow.setIsDelete(1);
        int flag = communityShowMapper.updateByPrimaryKeySelective(communityShow);
        if (flag > 0) {
            adminLogsMapper.insertSelective(new AdminLogs(unsign.getUserId(), "删除公益展示 " + communityShow.getShowName()));
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    @Override
    public Result communityPut(String accessToken, CommunityShow communityShow) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        CommunityShow communityShow1 = communityShowMapper.selectByPrimaryKey(communityShow.getShowId());
        if (Objects.isNull(communityShow1)) {
            return Result.createByErrorMessage("该公益展示信息不存在");
        } else {
            int flag = communityShowMapper.updateByPrimaryKeySelective(communityShow);
            if (flag > 0) {
                adminLogsMapper.insertSelective(new AdminLogs(unsign.getUserId(), "修改公益展示信息 " + communityShow.getShowName()));
                return Result.createBySuccessMessage("成功");
            } else {
                return Result.createByErrorMessage("操作失败请稍后重试");
            }
        }
    }

    @Override
    public Result community(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = communityShowMapper.selectList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }
}
