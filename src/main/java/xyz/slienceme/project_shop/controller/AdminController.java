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
import xyz.slienceme.project_shop.dto.Admin;
import xyz.slienceme.project_shop.service.IAdminService;
import xyz.slienceme.project_shop.vo.AdminVO;
import xyz.slienceme.project_shop.vo.LoginVO;
import xyz.slienceme.project_shop.vo.PwdVO;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Slf4j
@Api(tags = "管理员表")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation("管理员登陆")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVO loginVO) throws Exception {
        log.info("管理员登陆接口调用-----</login>:  " + loginVO);
        return adminService.login(loginVO);
    }

    @ApiOperation("查询管理员列表")
    @GetMapping("/adminList")
    public Result adminList(@RequestHeader("x-access-token") String accessToken,
                            @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                            @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                            @ApiParam(value = "管理员名称、描述") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        //log.info("查询管理员列表接口调用--get---</adminList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return adminService.adminList(accessToken, pageNo, pageSize, keyword);
    }


    @ApiOperation("添加管理员")
    @PostMapping("/adminList")
    public Result adminAdd(@RequestHeader("x-access-token") String accessToken,
                           @RequestBody AdminVO adminVO) throws Exception {
        log.info("添加管理员接口调用---post--</adminList>:  AdminAddVO=" + adminVO);
        return adminService.adminAdd(accessToken, adminVO);
    }


    @ApiOperation("通过id删除管理员账号")
    @DeleteMapping("/adminList")
    public Result adminDel(@RequestHeader("x-access-token") String accessToken,
                           @ApiParam(value = "管理员id") @RequestParam(value = "adminId") Integer adminId) throws Exception {
        log.info("通过id删除管理员账号接口调用---delete--</adminList>:  adminId=" + adminId);
        return adminService.adminDel(accessToken, adminId);
    }


    @ApiOperation("修改管理员")
    @PutMapping("/adminList")
    public Result adminPut(@RequestHeader("x-access-token") String accessToken,
                           @RequestBody Admin admin) throws Exception {
        log.info("修改管理员接口调用---put--</adminList>:  Admin=" + admin);
        return adminService.adminPut(accessToken, admin);
    }


    @ApiOperation("修改密码")
    @PutMapping("/adminPwd")
    public Result adminPwdPut(@RequestHeader("x-access-token") String accessToken,
                              @RequestBody PwdVO pwdVO) throws Exception {
        log.info("修改密码接口调用---put--</adminPwd>:  PwdVO=" + pwdVO);
        return adminService.adminPwdPut(accessToken, pwdVO);
    }

    @ApiOperation("重置密码")
    @GetMapping("/adminPwd")
    public Result adminPwd(@RequestHeader("x-access-token") String accessToken,
                           @ApiParam(value = "管理员id") @RequestParam(value = "adminId") Integer adminId) throws Exception {
        log.info("重置密码接口调用---put--</adminPwd>:  adminId=" + adminId);
        return adminService.adminPwd(accessToken, adminId);
    }

    @ApiOperation("admin退出登陆")
    @GetMapping("/out")
    public Result adminOut(@RequestHeader("x-access-token") String accessToken) throws Exception {
        log.info("admin退出登陆接口调用---put--</out>");
        return adminService.adminOut(accessToken);
    }
}
