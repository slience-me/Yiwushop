package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.CommunityShow;
import xyz.slienceme.project_shop.dto.User;
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
 * @since 2022-01-15
 */
@Service
public class CommunityShowServiceImpl implements ICommunityShowService {

    @Autowired
    private CommunityShowMapper communityShowMapper;

    @Override
    public Result addCommunity(String accessToken, CommunityShowVO communityShowVO) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        CommunityShow communityShow = new CommunityShow();
        communityShow.setShowName(communityShowVO.getShowName());
        communityShow.setShowImgId(communityShowVO.getShowImgId());
        communityShow.setCreatedBy(unsign.getUserId());
        communityShowMapper.insertSelective(communityShow);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result deleteCommunity(String accessToken, Integer id) throws Exception {
        CommunityShow communityShow = communityShowMapper.selectByPrimaryKey(id);
        communityShow.setIsDelete(1);
        communityShowMapper.updateByPrimaryKeySelective(communityShow);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result updateCommunity(String accessToken, CommunityShow communityShow) throws Exception {
        CommunityShow communityShow1 = communityShowMapper.selectByPrimaryKey(communityShow.getShowId());
        if (Objects.isNull(communityShow1)) {
            return Result.createByErrorMessage("该公益展示信息不存在");
        } else {
            communityShowMapper.updateByPrimaryKeySelective(communityShow);
            return Result.createBySuccessMessage("成功");
        }
    }

    @Override
    public Result community(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = communityShowMapper.selectList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }
}
