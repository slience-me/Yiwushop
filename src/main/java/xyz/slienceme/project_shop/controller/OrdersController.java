package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.service.IAuctionScheduleService;
import xyz.slienceme.project_shop.service.IOrdersService;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Slf4j
@Api(tags = "订单表")
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @ApiOperation("查询订单表")
    @GetMapping("/ordersList")
    public Result ordersList(@RequestHeader("x-access-token") String accessToken,
                                      @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                      @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                             @ApiParam(value = "流水号、商品名称、买家名称、卖家名称") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("查询订单表接口调用--get---</ordersList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return ordersService.ordersList(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation("通过id删除订单")
    @DeleteMapping("/ordersList")
    public Result ordersDel(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "订单id") @RequestParam(value = "ordersId") Integer ordersId) throws Exception {
        log.info("通过id删除订单接口调用---delete--</ordersList>:  ordersId=" + ordersId);
        return ordersService.ordersDel(accessToken, ordersId);
    }

}
