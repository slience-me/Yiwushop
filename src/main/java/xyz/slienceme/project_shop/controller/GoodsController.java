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
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.dto.Pawn;
import xyz.slienceme.project_shop.service.IAuctionsService;
import xyz.slienceme.project_shop.service.IGoodsService;
import xyz.slienceme.project_shop.vo.AuctionsVO;
import xyz.slienceme.project_shop.vo.GoodsVO;
import xyz.slienceme.project_shop.vo.PawnVO;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Slf4j
@Api(tags = "商品表")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IAuctionsService auctionsService;

    @ApiOperation("查询商品列表")
    @GetMapping("/goodsList")
    public Result goodsList(@RequestHeader("x-access-token") String accessToken,
                            @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                            @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                            @ApiParam(value = "商品名称、描述、状态类型 0未上架 1已上架 2已售 3待典当，分类名称") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("查询商品列表接口调用--get---</goodsList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return goodsService.goodsList(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation("查询上架商品列表")
    @GetMapping("/goodsOnList")
    public Result goodsOnList(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                              @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                              @ApiParam(value = "商品名称、描述、状态类型 0未上架 1已上架 2已售 3待典当，分类名称") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("查询商品列表接口调用--get---</goodsOnList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return goodsService.goodsOnList(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation("查询未上架商品列表")
    @GetMapping("/goodsNoList")
    public Result goodsNoList(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                              @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                              @ApiParam(value = "商品名称、描述、状态类型 0未上架 1已上架 2已售 3待典当，分类名称") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("查询商品列表接口调用--get---</goodsNoList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return goodsService.goodsNoList(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation("查询已售商品列表")
    @GetMapping("/goodsDoneList")
    public Result goodsDoneList(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                              @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                              @ApiParam(value = "商品名称、描述、状态类型 0未上架 1已上架 2已售 3待典当，分类名称") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("查询商品列表接口调用--get---</goodsDoneList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return goodsService.goodsDoneList(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation("查询典当商品列表")
    @GetMapping("/goodsPawnList")
    public Result goodsPawnList(@RequestHeader("x-access-token") String accessToken,
                                @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                                @ApiParam(value = "商品名称、描述、状态类型 0未上架 1已上架 2已售 3待典当，分类名称") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("查询典当商品列表接口调用--get---</goodsPawnList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return goodsService.goodsPawnList(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation("添加商品")
    @PostMapping("/goodsList")
    public Result goodsAdd(@RequestHeader("x-access-token") String accessToken,
                           @RequestBody GoodsVO goodsVO) throws Exception {
        log.info("添加商品接口调用---post--</goodsList>:  GoodsVO=" + goodsVO);
        return goodsService.goodsAdd(accessToken, goodsVO);
    }


    @ApiOperation("通过id删除商品账号")
    @DeleteMapping("/goodsList")
    public Result goodsDel(@RequestHeader("x-access-token") String accessToken,
                           @ApiParam(value = "商品id") @RequestParam(value = "goodsId") Integer goodsId) throws Exception {
        log.info("通过id删除商品账号接口调用---delete--</goodsList>:  goodsId=" + goodsId);
        return goodsService.goodsDel(accessToken, goodsId);
    }


    @ApiOperation("修改商品")
    @PutMapping("/goodsList")
    public Result goodsPut(@RequestHeader("x-access-token") String accessToken,
                           @RequestBody Goods goods) throws Exception {
        log.info("修改商品接口调用---put--</goodsList>:  goods=" + goods);
        return goodsService.goodsPut(accessToken, goods);
    }

    /*@ApiOperation("上架商品")
    @PostMapping("/stateOn")
    public Result stateon(@RequestHeader("x-access-token") String accessToken,
                          @ApiParam(value = "分类名称") @RequestParam String auctionsName,
                          @ApiParam(value = "商品id") @RequestParam(value = "goodsId") Integer goodsId,
                          @ApiParam("格式 yyyy-MM-dd") @RequestParam(value = "startTime") String startTime,
                          @ApiParam("格式 yyyy-MM-dd") @RequestParam(value = "endTime") String endTime) throws Exception {
        log.info("上架商品接口调用---put-----</goodsList>           ");
        log.info("添加管理员接口调用---post--</auctionsList>:  auctionsName=" + auctionsName);
        return goodsService.stateOn(accessToken, auctionsName, goodsId, startTime, endTime);
    }*/

    @ApiOperation("上架商品")
    @PostMapping("/stateOn")
    public Result stateon(@RequestHeader("x-access-token") String accessToken,
                          @RequestBody AuctionsVO auctionsVO) throws Exception {
        log.info("上架商品接口调用---put-----</goodsList>           ");
        return goodsService.stateOn(accessToken, auctionsVO);
    }

    @ApiOperation("上架典当")
    @PostMapping("/stateOnToPawn")
    public Result stateOnToPawn(@RequestHeader("x-access-token") String accessToken,
                          @RequestBody PawnVO pawnVO) throws Exception {
        log.info("上架商品接口调用---put-----</stateOnToPawn>           ");
        return goodsService.stateOnToPawn(accessToken, pawnVO);
    }

}
