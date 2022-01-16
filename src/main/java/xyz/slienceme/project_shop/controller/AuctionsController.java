package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.service.IAuctionsService;

/**
 * <p>
 * 竞拍场次表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Api(tags = "竞拍场次表")
@RestController
@RequestMapping("/auctions")
public class AuctionsController {

    public static final Logger log = LoggerFactory.getLogger(AuctionsController.class);

    @Autowired
    private IAuctionsService auctionsService;

    @ApiOperation("查询竞拍场次")
    @GetMapping("/auctionsList")
    public Result auctionsList(@RequestHeader("x-access-token") String accessToken,
                               @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                               @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                               @ApiParam(value = "管理员名称、描述") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("查询竞拍场次列表接口调用--get---</auctionsList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return auctionsService.auctionsList(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation("添加竞拍场次")
    @PostMapping("/auctionsList")
    public Result auctionsAdd(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "分类名称") @RequestParam String auctionsName,
                              @ApiParam("格式 yyyy-MM-dd") @RequestParam(value = "startTime") String startTime,
                              @ApiParam("格式 yyyy-MM-dd") @RequestParam(value = "endTime") String endTime) throws Exception {
        log.info("添加管理员接口调用---post--</auctionsList>:  auctionsName=" + auctionsName);
        return auctionsService.auctionsAdd(accessToken, auctionsName, startTime, endTime);
    }

    @ApiOperation("通过id删除竞拍场次")
    @DeleteMapping("/auctionsList")
    public Result auctionsDel(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "竞拍场次id") @RequestParam(value = "auctionsId") Integer auctionsId) throws Exception {
        log.info("通过id删除竞拍场次接口调用---delete--</auctionsList>:  auctionsId=" + auctionsId);
        return auctionsService.auctionsDel(accessToken, auctionsId);
    }


    @ApiOperation("修改竞拍场次")
    @PutMapping("/auctionsList")
    public Result auctionsPut(@RequestHeader("x-access-token") String accessToken,
                              @RequestBody Auctions auctions) throws Exception {
        log.info("修改竞拍场次接口调用---put--</auctionsList>:  auctions=" + auctions);
        return auctionsService.auctionsPut(accessToken, auctions);
    }
}
