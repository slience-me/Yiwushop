package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Category;
import xyz.slienceme.project_shop.dto.Orders;
import xyz.slienceme.project_shop.mapper.AuctionScheduleMapper;
import xyz.slienceme.project_shop.mapper.OrdersMapper;
import xyz.slienceme.project_shop.service.IOrdersService;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.utils.StringUtil;
import xyz.slienceme.project_shop.vo.OrdersVO;
import xyz.slienceme.project_shop.vo.TokenVO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 订单列表
     *
     * @param accessToken
     * @param page
     * @param limit
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public Result orders(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = ordersMapper.selectList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 根据id删除订单
     */
    @Override
    public Result ordersDel(String accessToken, Integer ordersId) throws Exception {
        Orders orders = ordersMapper.selectByPrimaryKey(ordersId);
        orders.setIsDelete(1);
        int flag = ordersMapper.updateByPrimaryKeySelective(orders);
        if (flag > 0) {
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    @Override
    public Result ordersAdd(String accessToken, OrdersVO ordersVO) {
        Orders orders = new Orders();
        orders.setSerialNum(StringUtil.serialNumber(ordersVO.getGoodsId()));
        orders.setGoodsId(ordersVO.getGoodsId());
        orders.setSellUsersId(ordersVO.getSellUsersId());
        orders.setBuyUsersId(ordersVO.getBuyUsersId());
        orders.setBuyPrice(ordersVO.getBuyPrice());
        orders.setCreatedBy(1);
        int flag = ordersMapper.insertSelective(orders);
        if (flag > 0) {
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }


}
