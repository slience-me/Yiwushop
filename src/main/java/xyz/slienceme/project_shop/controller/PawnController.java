package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Pawn;
import xyz.slienceme.project_shop.service.IAuctionsService;
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
@RequestMapping("/admin")
public class PawnController {

    @Autowired
    private IAuctionsService auctionsService;

    @ApiOperation("条件查询典当场次列表")
    @GetMapping("/pawn")
    public Result pawn(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                              @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                              @ApiParam(value = "商品id") @RequestParam(value = "goodsId", required = false) Integer goodsId,
                              @ApiParam(value = "场次名称") @RequestParam(value = "pawnName", required = false) String pawnName) throws Exception {
        //log.info("条件查询典当场次列表接口调用--get---</pawnList/data>: goodsId=" + goodsId + "pawnName=" + pawnName);
        return auctionsService.pawn(accessToken, pageNo, pageSize, goodsId, pawnName);
    }

    @ApiOperation("查询单个典当场次")
    @GetMapping("/pawn/{auctionsId}")
    public Result pawnOne(@RequestHeader("x-access-token") String accessToken,
                             @PathVariable("auctionsId") @ApiParam(value = "场次id", required = true) Integer auctionsId) throws Exception {
        //log.info("查询单个典当场次接口调用--get---</pawnList/{auctionsId}>:  auctionsId=" + auctionsId);
        return auctionsService.selectByPrimaryKeyPawn(accessToken, auctionsId);
    }

    @ApiOperation("添加典当场次")
    @PostMapping("/pawn")
    public Result pawnAdd(@RequestHeader("x-access-token") String accessToken,
                          @RequestBody PawnVO pawnVO) throws Exception {
        log.info("添加典当场次接口调用---post--</pawnList>:  pawnVO=" + pawnVO);
        return auctionsService.pawnAdd(accessToken, pawnVO);
    }

    @ApiOperation("通过id删除典当场次")
    @DeleteMapping("/pawn")
    public Result pawnDel(@RequestHeader("x-access-token") String accessToken,
                          @ApiParam(value = "典当场次id") @RequestParam(value = "pawnId") Integer pawnId) throws Exception {
        log.info("通过id删除典当场次接口调用---delete--</pawnList>:  pawnId=" + pawnId);
        return auctionsService.pawnDel(accessToken, pawnId);
    }

    @ApiOperation("修改典当场次")
    @PutMapping("/pawn")
    public Result pawnPut(@RequestHeader("x-access-token") String accessToken,
                          @RequestBody Pawn pawn) throws Exception {
        log.info("修改典当场次接口调用---put--</pawnList>:  pawn=" + pawn);
        return auctionsService.pawnPut(accessToken, pawn);
    }
}
