package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.dto.Category;
import xyz.slienceme.project_shop.mapper.AuctionsMapper;
import xyz.slienceme.project_shop.mapper.CategoryMapper;
import xyz.slienceme.project_shop.service.IAuctionsService;
import xyz.slienceme.project_shop.utils.DateUtil;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.vo.TokenVO;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 竞拍场次表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Service
public class AuctionsServiceImpl implements IAuctionsService {

    @Autowired
    private AuctionsMapper auctionsMapper;

    /**
     * 竞拍场次表列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    @Override
    public Result auctionsList(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page,limit);
        List<HashMap<String, Object>> list = auctionsMapper.selectList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 根据竞拍场次信息添加
     */
    @Override
    public Result auctionsAdd(String accessToken, Integer goodsId, String auctionsName, String startTime, String endTime) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Auctions auctions = new Auctions();
        auctions.setGoodsId(goodsId);
        auctions.setAuctionsName(auctionsName);
        auctions.setStart(DateUtil.StringToLocalDateTime(startTime));
        auctions.setEnd(DateUtil.StringToLocalDateTime(endTime));
        auctions.setCreatedBy(unsign.getUserId());
        auctionsMapper.insertSelective(auctions);
        return Result.createBySuccessMessage("成功");
    }

    /**
     * 根据id删除竞拍场次
     */
    @Override
    public Result auctionsDel(String accessToken, Integer auctionsId) throws Exception {
        Auctions auctions = auctionsMapper.selectByPrimaryKey(auctionsId);
        auctions.setIsDelete(1);
        auctionsMapper.updateByPrimaryKeySelective(auctions);
        return Result.createBySuccessMessage("成功");
    }

    /**
     * 根据竞拍场次信息修改竞拍场次
     */
    @Override
    public Result auctionsPut(String accessToken, Auctions auctions) throws Exception {
        Auctions auctions1 = auctionsMapper.selectByPrimaryKey(auctions.getAuctionsId());
        System.out.println("auctions1 = " + auctions1);
        if (Objects.isNull(auctions1)) {
            return Result.createByErrorMessage("该拍卖场次不存在");
        }
        auctionsMapper.updateByPrimaryKeySelective(auctions);
        return Result.createBySuccessMessage("成功");
    }
}
