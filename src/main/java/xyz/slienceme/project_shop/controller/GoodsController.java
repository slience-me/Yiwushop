package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.service.IAuctionsService;
import xyz.slienceme.project_shop.service.IGoodsService;
import xyz.slienceme.project_shop.vo.GoodsVO;

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
@RequestMapping("/admin")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IAuctionsService auctionsService;

    @ApiOperation("条件查询商品列表")
    @GetMapping("/goods")
    public Result goods(@RequestHeader("x-access-token") String accessToken,
                               @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                               @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                               @ApiParam(value = "商品名称") @RequestParam(value = "goodsName", required = false) String goodsName,
                               @ApiParam(value = "商品描述") @RequestParam(value = "goodsInfo", required = false) String goodsInfo,
                               @ApiParam(value = "状态类型 1未上架 2已上架 3已售 4待典当") @RequestParam(value = "stateOn", required = false) Integer stateOn,
                               @ApiParam(value = "商品分类id") @RequestParam(value = "categoryId", required = false) Integer categoryId,
                               @ApiParam(value = "所属用户id") @RequestParam(value = "userId", required = false) Integer userId) throws Exception {
        //log.info("条件查询商品列表接口调用--get---</data>: goodsName=" + goodsName + " goodsInfo=" + goodsInfo + " stateOn=" + stateOn + " categoryId=" + categoryId + " userId=" + userId);
        return goodsService.goods(accessToken, pageNo, pageSize, goodsName, goodsInfo, stateOn, categoryId, userId);
    }

    @ApiOperation("查询单个商品")
    @GetMapping("/goods/{goodsId}")
    public Result goodsOne(@RequestHeader("x-access-token") String accessToken,
                              @PathVariable("goodsId") @ApiParam(value = "商品id", required = true) Integer goodsId) throws Exception {
        //log.info("查询单个商品接口调用--get---</goodsList/{goodsId}>: goodsId=" + goodsId);
        return goodsService.selectByPrimaryKey(accessToken, goodsId);
    }

    @ApiOperation("添加商品")
    @PostMapping("/goods")
    public Result goodsAdd(@RequestHeader("x-access-token") String accessToken,
                           @RequestBody GoodsVO goodsVO) throws Exception {
        log.info("添加商品接口调用---post--</goodsList>:  GoodsVO=" + goodsVO);
        return goodsService.goodsAdd(accessToken, goodsVO);
    }


    @ApiOperation("通过id删除商品账号")
    @DeleteMapping("/goods")
    public Result goodsDel(@RequestHeader("x-access-token") String accessToken,
                           @ApiParam(value = "商品id") @RequestParam(value = "goodsId") Integer goodsId) throws Exception {
        log.info("通过id删除商品账号接口调用---delete--</goodsList>:  goodsId=" + goodsId);
        return goodsService.goodsDel(accessToken, goodsId);
    }


    @ApiOperation("修改商品")
    @PutMapping("/goods")
    public Result goodsPut(@RequestHeader("x-access-token") String accessToken,
                           @RequestBody Goods goods) throws Exception {
        log.info("修改商品接口调用---put--</goodsList>:  goods=" + goods);
        return goodsService.goodsPut(accessToken, goods);
    }

}
