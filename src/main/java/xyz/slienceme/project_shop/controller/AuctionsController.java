package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.service.IAuctionsService;
import xyz.slienceme.project_shop.vo.AuctionsVO;

/**
 * <p>
 * 竞拍场次表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Slf4j
@Api(tags = "竞拍场次表")
@RestController
@RequestMapping("/admin")
public class AuctionsController {

    @Autowired
    private IAuctionsService auctionsService;

    @ApiOperation("条件查询竞拍场次列表")
    @GetMapping("/auctions")
    public Result auctions(@RequestHeader("x-access-token") String accessToken,
                                  @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                  @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                                  @ApiParam(value = "商品id") @RequestParam(value = "goodsId", required = false) Integer goodsId,
                                  @ApiParam(value = "场次名称") @RequestParam(value = "auctionsName", required = false) String auctionsName) throws Exception {
        //log.info("条件查询竞拍场次列表接口调用--get---</auctionsList/data>: goodsId" + goodsId + " auctionsName=" + auctionsName);
        return auctionsService.auctions(accessToken, pageNo, pageSize, goodsId, auctionsName);
    }

    @ApiOperation("查询单个竞拍场次")
    @GetMapping("/auctions/{auctionsId}")
    public Result auctionsOne(@RequestHeader("x-access-token") String accessToken,
                                 @PathVariable("auctionsId") @ApiParam(value = "场次id", required = true) Integer auctionsId) throws Exception {
        //log.info("查询单个竞拍场次接口调用--get---</auctionsList/{auctionsId}>:  auctionsId=" + auctionsId);
        return auctionsService.selectByPrimaryKey(accessToken, auctionsId);
    }

    @ApiOperation("添加竞拍场次")
    @PostMapping("/auctions")
    public Result auctionsAdd(@RequestHeader("x-access-token") String accessToken,
                              @RequestBody AuctionsVO auctionsVO) throws Exception {
        log.info("添加竞拍场次接口调用---post--</auctionsList>:  AuctionsVO=" + auctionsVO);
        return auctionsService.auctionsAdd(accessToken, auctionsVO);
    }

    @ApiOperation("通过id删除竞拍场次")
    @DeleteMapping("/auctions")
    public Result auctionsDel(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "竞拍场次id") @RequestParam(value = "auctionsId") Integer auctionsId) throws Exception {
        log.info("通过id删除竞拍场次接口调用---delete--</auctionsList>:  auctionsId=" + auctionsId);
        return auctionsService.auctionsDel(accessToken, auctionsId);
    }


    @ApiOperation("修改竞拍场次")
    @PutMapping("/auctions")
    public Result auctionsPut(@RequestHeader("x-access-token") String accessToken,
                              @RequestBody Auctions auctions) throws Exception {
        log.info("修改竞拍场次接口调用---put--</auctionsList>:  auctions=" + auctions);
        return auctionsService.auctionsPut(accessToken, auctions);
    }

}
