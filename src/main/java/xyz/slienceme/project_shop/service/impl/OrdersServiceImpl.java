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
     * @param accessToken
     * @param page
     * @param limit
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public Result ordersList(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page,limit);
        List<HashMap<String, Object>> list = ordersMapper.selectList();
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 根据id删除订单
     */
    @Override
    public Result ordersDel(String accessToken, Integer ordersId) throws Exception {
        Orders orders = ordersMapper.selectByPrimaryKey(ordersId);
        orders.setIsDelete(1);
        ordersMapper.updateByPrimaryKeySelective(orders);
        return Result.createBySuccessMessage("成功");
    }


}
