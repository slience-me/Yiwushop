package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
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

    @Override
    public Result ordersList(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page,limit);
        List<HashMap<String, Object>> list = ordersMapper.selectList();
        return Result.createBySuccess(new PageInfo<>(list));
    }


}
