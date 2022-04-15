package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.service.IAuctionScheduleService;
import xyz.slienceme.project_shop.vo.AuctionScheduleVO;

/**
 * <p>
 * 拍卖过程前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-03-16
 */
@Slf4j
@Api(tags = "拍卖过程表")
@RestController
@RequestMapping("/admin")
public class AuctionScheduleController {

    @Autowired
    private IAuctionScheduleService auctionScheduleService;

    @ApiOperation("查询拍卖过程表")
    @GetMapping("/process")
    public Result process(@RequestHeader("x-access-token") String accessToken,
                                      @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                      @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                                      @ApiParam(value = "商品id", required = true) @RequestParam(value = "goodsId") Integer goodsId) throws Exception {
        //log.info("查询拍卖过程表接口调用--get---</auctionScheduleList>:  pageNo=" + pageNo + ",pageSize=" + pageSize);
        return auctionScheduleService.auctionScheduleList(accessToken, pageNo, pageSize, goodsId);
    }

    @ApiOperation("添加拍卖过程表")
    @PostMapping("/process")
    public Result processAdd(@RequestHeader("x-access-token") String accessToken,
                                     @RequestBody AuctionScheduleVO auctionScheduleVO) throws Exception {
        log.info("添加拍卖过程表接口调用---post--</auctionScheduleList>:  auctionScheduleVO=" + auctionScheduleVO);
        return auctionScheduleService.auctionScheduleAdd(accessToken, auctionScheduleVO);
    }
}
