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
import xyz.slienceme.project_shop.service.IAdminRoleMenuService;
import xyz.slienceme.project_shop.vo.RoleUpdateVO;
@Slf4j
@Api(tags = "管理员角色菜单相关")
@RestController
@RequestMapping("/admin")
public class AdminRoleMenuController {

    @Autowired
    private IAdminRoleMenuService adminRoleMenuService;

    @ApiOperation(value = "菜单列表", notes = "菜单管理", httpMethod = "GET")
    @GetMapping("/menu")
    public Result findAll(@RequestHeader("x-access-token") String accessToken,
                          @ApiParam(value = "角色id，当前角色的菜单返回 isCheck=1") @RequestParam(value = "roleId", required = false) Integer roleId) {
        log.info("查询菜单列表接口调用-------get---------</menu>:  roleId=" + roleId);
        return adminRoleMenuService.selectAll(accessToken, roleId);
    }

    @ApiOperation(value = "角色列表", notes = "角色管理", httpMethod = "GET")
    @GetMapping("/rolelist")
    public Result selectAll(@RequestHeader("x-access-token") String accessToken,
                            @ApiParam(required = false, value = "第几页") @RequestParam(value = "pageNo", required = false) Integer pageNo,
                            @ApiParam(required = false, value = "每页条数") @RequestParam(value = "pageSize", required = false) Integer pageSize,
                            @ApiParam(value = "角色名称、描述") @RequestParam(required = false) String keyword) {
        log.info("查询角色列表接口调用-------get------</rolelist>");
        return adminRoleMenuService.selectRoleAll(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation(value = "获取角色详细信息", notes = "角色管理")
    @GetMapping(value = "/rolelist/{roleId}")
    public Result selectByPrimaryKey(@RequestHeader("x-access-token") String accessToken,
                                     @PathVariable("roleId") @ApiParam(value = "角色id", required = true) Integer roleId) {
        log.info("获取角色详细信息接口调用-----get------</rolelist/{roleId}>:  roleId=" + roleId);
        return adminRoleMenuService.selectByPrimaryKey(accessToken, roleId);
    }

    @ApiOperation(value = "新建角色", notes = "角色管理")
    @PostMapping("/rolelist")
    public Result insert(@RequestHeader("x-access-token") String accessToken,
                         @RequestBody RoleUpdateVO roleUpdateVO) {
        log.info("新建角色接口调用------post-----</rolelist>:  roleUpdateVO" + roleUpdateVO.getRoleName());
        return adminRoleMenuService.insert(accessToken, roleUpdateVO);
    }

    @ApiOperation(value = "修改角色信息", notes = "角色管理")
    @PutMapping("/rolelist")
    public Result update(@RequestHeader("x-access-token") String accessToken,
                         @RequestBody RoleUpdateVO roleUpdateVO) {
        log.info("修改角色信息接口调用-----put-------</rolelist>:  roleUpdateVO=" + roleUpdateVO.getRoleName());
        return adminRoleMenuService.updateByPrimaryKey(accessToken, roleUpdateVO);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/rolelist")
    public Result rolelistDel(@RequestHeader("x-access-token") String accessToken,
                              @RequestParam("roleId") Integer roleId) throws Exception {
        log.info("删除角色接口调用-------del-----</rolelist>:  roleId=" + roleId);
        return adminRoleMenuService.rolelistDel(accessToken, roleId);
    }

    @ApiOperation(value = "获取用户权限", notes = "角色管理")
    @GetMapping("/permission")
    public Result getPermission(@RequestHeader("x-access-token") String accessToken) {
        log.info("获取用户权限接口调用-----get----</permission>");
        return adminRoleMenuService.getPermission(accessToken);
    }

}
