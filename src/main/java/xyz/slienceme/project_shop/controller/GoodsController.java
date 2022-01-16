package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Admin;
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.service.IAdminService;
import xyz.slienceme.project_shop.service.IGoodsService;
import xyz.slienceme.project_shop.vo.AdminVO;
import xyz.slienceme.project_shop.vo.GoodsVO;
import xyz.slienceme.project_shop.vo.LoginVO;
import xyz.slienceme.project_shop.vo.PwdVO;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Api(tags = "商品表")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private IGoodsService goodsService;

    @ApiOperation("查询商品列表")
    @GetMapping("/goodsList")
    public Result goodsList(@RequestHeader("x-access-token") String accessToken,
                            @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                            @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                            @ApiParam(value = "商品名称、描述") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("查询商品列表接口调用--get---</goodsList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return goodsService.goodsList(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation("查询上架商品列表")
    @GetMapping("/goodsOnList")
    public Result goodsOnList(@RequestHeader("x-access-token") String accessToken,
                            @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                            @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                            @ApiParam(value = "商品名称、描述") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("查询商品列表接口调用--get---</goodsList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return goodsService.goodsOnList(accessToken, pageNo, pageSize, keyword);
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

    @ApiOperation("上架商品")
    @PutMapping("/stateOn")
    public Result stateon(@RequestHeader("x-access-token") String accessToken,
                          @ApiParam(value = "商品id") @RequestParam(value = "goodsId") Integer goodsId) throws Exception {
        log.info("上架商品接口调用---put-----</goodsList>           ");
        return goodsService.stateOn(accessToken, goodsId);
    }

}
