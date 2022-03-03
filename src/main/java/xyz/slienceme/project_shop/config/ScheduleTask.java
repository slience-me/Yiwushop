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
import xyz.slienceme.project_shop.dto.Pawn;
import xyz.slienceme.project_shop.mapper.AuctionsMapper;
import xyz.slienceme.project_shop.mapper.GoodsMapper;
import xyz.slienceme.project_shop.mapper.OrdersMapper;
import xyz.slienceme.project_shop.mapper.PawnMapper;
import xyz.slienceme.project_shop.utils.StringUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Configuration
@EnableScheduling
public class ScheduleTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private AuctionsMapper auctionsMapper;
    @Autowired
    private PawnMapper pawnMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Scheduled(cron = "0 */1 * * * ?")//每分钟执行
    public void generateOrder() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nowMilliSecond = new Date().getTime();
        String nowTime = sdf.format(nowMilliSecond);
        log.info("定时任务开始执行--当前时间: " + nowTime);
        List<HashMap<String, Object>> auctionsList = auctionsMapper.selectUndoneList(nowTime);
        List<HashMap<String, Object>> pawnList = pawnMapper.selectUndoneList(nowTime);
        for (HashMap<String, Object> auctionsi : auctionsList) {
            Orders orders = new Orders();
            orders.setSerialNum(StringUtil.serialNumber((Integer) auctionsi.get("goodsId")));
            orders.setGoodsId((Integer) auctionsi.get("goodsId"));
            orders.setSellUsersId((Integer) auctionsi.get("createdBy"));
            orders.setBuyUsersId((Integer) auctionsi.get("presentPerson"));
            orders.setBuyPrice((BigDecimal) auctionsi.get("presentPrice"));
            orders.setCreatedBy(1);
            log.info("生成订单 ："+ orders.getSerialNum());
            ordersMapper.insertSelective(orders);
            Goods goods = goodsMapper.selectByPrimaryKey((Integer) auctionsi.get("goodsId"));
            goods.setStateOn(3);//已售
            log.info("商品 [" + goods.getGoodsName() + "] 状态设置已售");
            goodsMapper.updateByPrimaryKeySelective(goods);
            Auctions auctions = auctionsMapper.selectByPrimaryKey((Integer) auctionsi.get("auctionsId"));
            auctions.setIsDelete(1);//结束
            log.info("竞拍场次删除 ：id="+ auctions.getAuctionsId());
            auctionsMapper.updateByPrimaryKeySelective(auctions);
        }
        for (HashMap<String, Object> pawni : pawnList) {
            Orders orders = new Orders();
            orders.setSerialNum(StringUtil.serialNumber((Integer) pawni.get("goodsId")));
            orders.setGoodsId((Integer) pawni.get("goodsId"));
            orders.setSellUsersId((Integer) pawni.get("createdBy"));
            orders.setBuyUsersId((Integer) pawni.get("presentPerson"));
            orders.setBuyPrice((BigDecimal) pawni.get("presentPrice"));
            orders.setCreatedBy(1);
            log.info("生成订单 ："+ orders.getSerialNum());
            ordersMapper.insertSelective(orders);
            Goods goods = goodsMapper.selectByPrimaryKey((Integer) pawni.get("goodsId"));
            goods.setStateOn(3);//已售
            log.info("商品 " + goods.getGoodsName() + "状态设置已售");
            goodsMapper.updateByPrimaryKeySelective(goods);
            Pawn pawn = pawnMapper.selectByPrimaryKey((Integer) pawni.get("auctionsId"));
            pawn.setIsDelete(1);//结束
            log.info("典当场次删除 ：id="+ pawn.getAuctionsId());
            pawnMapper.updateByPrimaryKeySelective(pawn);
        }
    }
}
