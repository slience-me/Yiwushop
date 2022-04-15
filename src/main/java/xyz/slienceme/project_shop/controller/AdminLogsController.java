package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.service.IAdminLogsService;

/**
 * <p>
 * 管理员操作日志 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-04-10
 */
@Slf4j
@Api(tags = "管理员日志相关")
@RestController
@RequestMapping("/adminlogs")
public class AdminLogsController {

    @Autowired
    private IAdminLogsService adminLogsService;

    @ApiOperation("查询浏览记录")
    @GetMapping("/log")
    public Result glanceLog(@RequestHeader("x-access-token") String accessToken,
                            @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                            @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                            @ApiParam(value = "管理员Id") @RequestParam(value = "adminId", required = false) Integer adminId) throws Exception {
        log.info("[查询浏览记录接口调用-----get-----</log> adminId=" + adminId+ "]");
        return adminLogsService.logList(accessToken, pageNo, pageSize, adminId);
    }

    @ApiOperation("删除浏览记录")
    @DeleteMapping("/log")
    public Result logDel(@RequestHeader("x-access-token") String accessToken,
                         @ApiParam(value = "adminLogsId", required = true) @RequestParam(value = "adminLogsId") Integer adminLogsId) throws Exception {
        log.info("[删除浏览记录接口调用------delete------</log> adminLogsId=" + adminLogsId+ "]");
        return adminLogsService.logDel(accessToken, adminLogsId);
    }

}