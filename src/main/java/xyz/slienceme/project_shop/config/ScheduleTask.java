package xyz.slienceme.project_shop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.dto.Orders;
import xyz.slienceme.project_shop.mapper.AuctionsMapper;
import xyz.slienceme.project_shop.mapper.GoodsMapper;
import xyz.slienceme.project_shop.mapper.OrdersMapper;
import xyz.slienceme.project_shop.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Configuration
@EnableScheduling
public class ScheduleTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private AuctionsMapper auctionsMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    /*@Scheduled(cron = "0/20 * * * * ?")*/
    public void generateOrder() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nowMilliSecond = new Date().getTime();
        String nowTime = sdf.format(nowMilliSecond);
        log.info("定时任务开始执行--当前时间: " + nowTime);
        List<HashMap<String, Object>> auctionsList = auctionsMapper.selectUndoneList(nowTime);
        for (HashMap<String, Object> auctionsi : auctionsList) {
            Goods goods = goodsMapper.selectByPrimaryKey((Integer) auctionsi.get("goodsId"));
            Orders orders = new Orders();
            orders.setSerialNum(StringUtil.serialNumber(goods.getGoodsId()));
            orders.setGoodsId(goods.getGoodsId());
            orders.setSellUsersId(goods.getUserId());
            orders.setBuyUsersId(goods.getPriceUserId());
            orders.setBuyPrice(goods.getPriceNow());
            orders.setCreatedBy(1);
            ordersMapper.insertSelective(orders);
            goods.setStateOn(2);//已售
            goodsMapper.updateByPrimaryKeySelective(goods);
            Auctions auctions = auctionsMapper.selectByPrimaryKey((Integer) auctionsi.get("goodsId"));
            auctions.setIsDelete(1);//结束
            auctionsMapper.updateByPrimaryKeySelective(auctions);
        }
    }
}
