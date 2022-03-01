package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Auctions;
import xyz.slienceme.project_shop.dto.Pawn;
import xyz.slienceme.project_shop.service.IAuctionsService;
import xyz.slienceme.project_shop.vo.AuctionScheduleVO;
import xyz.slienceme.project_shop.vo.AuctionsVO;
import xyz.slienceme.project_shop.vo.PawnScheduleVO;
import xyz.slienceme.project_shop.vo.PawnVO;

/**
 * <p>
 * 典当场次表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Slf4j
@Api(tags = "典当场次表")
@RestController
@RequestMapping("/pawn")
public class PawnController {

    @Autowired
    private IAuctionsService auctionsService;

    @ApiOperation("查询典当场次")
    @GetMapping("/pawnList")
    public Result pawnList(@RequestHeader("x-access-token") String accessToken,
                               @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                               @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                               @ApiParam(value = "商品名称、场次名称、用户名") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("查询典当场次列表接口调用--get---</auctionsList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return auctionsService.pawnList(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation("条件查询典当场次列表")
    @GetMapping("/data")
    public Result getData(@RequestHeader("x-access-token") String accessToken,
                          @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                          @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                          @ApiParam(value = "商品id") @RequestParam(value = "goodsId", required = false) Integer goodsId,
                          @ApiParam(value = "场次名称") @RequestParam(value = "pawnName", required = false) String pawnName) throws Exception {
        log.info("条件查询典当场次列表接口调用--get---</data>: ");
        return auctionsService.getPawnData(accessToken, pageNo, pageSize, goodsId, pawnName);
    }

    @ApiOperation("查询单个典当场次")
    @GetMapping("/pawnList/{auctionsId}")
    public Result getOne(@RequestHeader("x-access-token") String accessToken,
                         @PathVariable("auctionsId") @ApiParam(value = "场次id", required = true) Integer auctionsId) throws Exception {
        log.info("查询单个商品接口调用--get---</goodsList>:  pageNo=");
        return auctionsService.selectByPrimaryKeyPawn(accessToken, auctionsId);
    }

    /*@ApiOperation("添加典当场次")
    @PostMapping("/auctionsList")
    public Result auctionsAdd(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "分类名称") @RequestParam Integer goodsId,
                              @ApiParam(value = "分类名称") @RequestParam String auctionsName,
                              @ApiParam("格式 yyyy-MM-dd") @RequestParam(value = "startTime") String startTime,
                              @ApiParam("格式 yyyy-MM-dd") @RequestParam(value = "endTime") String endTime) throws Exception {
        log.info("添加典当场次接口调用---post--</auctionsList>:  auctionsName=" + auctionsName);
        return auctionsService.auctionsAdd(accessToken, goodsId, auctionsName, startTime, endTime);
    }*/

    @ApiOperation("添加典当场次")
    @PostMapping("/pawnList")
    public Result pawnAdd(@RequestHeader("x-access-token") String accessToken,
                              @RequestBody PawnVO pawnVO) throws Exception {
        log.info("添加典当场次接口调用---post--</pawnList>:  pawnVO=" + pawnVO);
        return auctionsService.pawnAdd(accessToken, pawnVO);
    }

    @ApiOperation("通过id删除典当场次")
    @DeleteMapping("/pawnList")
    public Result pawnDel(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "典当场次id") @RequestParam(value = "pawnId") Integer pawnId) throws Exception {
        log.info("通过id删除典当场次接口调用---delete--</pawnList>:  pawnId=" + pawnId);
        return auctionsService.pawnDel(accessToken, pawnId);
    }


    @ApiOperation("修改典当场次")
    @PutMapping("/pawnList")
    public Result pawnPut(@RequestHeader("x-access-token") String accessToken,
                              @RequestBody Pawn pawn) throws Exception {
        log.info("修改典当场次接口调用---put--</pawnList>:  pawn=" + pawn);
        return auctionsService.pawnPut(accessToken, pawn);
    }

    @ApiOperation("开始购买")
    @PutMapping("/do")
    public Result doPawn(@RequestHeader("x-access-token") String accessToken,
                             @ApiParam(value = "拍卖过程对象") @RequestBody PawnScheduleVO pawnScheduleVO) throws Exception {
        log.info("开始购买接口调用---put--</pawn/do>:  ");
        return auctionsService.doPawn(accessToken, pawnScheduleVO);
    }

}
