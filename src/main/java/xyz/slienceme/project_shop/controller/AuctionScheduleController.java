package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @since 2022-01-16
 */
@Api(tags = "拍卖过程表")
@RestController
@RequestMapping("/auction")
public class AuctionScheduleController {

    public static final Logger log = LoggerFactory.getLogger(AuctionScheduleController.class);

    @Autowired
    private IAuctionScheduleService auctionScheduleService;

    @ApiOperation("查询拍卖过程表")
    @GetMapping("/auctionScheduleList")
    public Result auctionScheduleList(@RequestHeader("x-access-token") String accessToken,
                                      @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                      @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize) throws Exception {
        log.info("查询拍卖过程表接口调用--get---</auctionScheduleList>:  pageNo=" + pageNo + ",pageSize=" + pageSize);
        return auctionScheduleService.auctionScheduleList(accessToken, pageNo, pageSize);
    }

    @ApiOperation("添加拍卖过程表")
    @PostMapping("/auctionScheduleList")
    public Result auctionScheduleAdd(@RequestHeader("x-access-token") String accessToken,
                                     @ApiParam(value = "分类名称") @RequestParam AuctionScheduleVO auctionScheduleVO) throws Exception {
        log.info("添加拍卖过程表接口调用---post--</auctionScheduleList>:  auctionScheduleVO=" + auctionScheduleVO);
        return auctionScheduleService.auctionScheduleAdd(accessToken, auctionScheduleVO);
    }

}
