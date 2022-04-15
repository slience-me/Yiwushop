package xyz.slienceme.project_shop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.User;
import xyz.slienceme.project_shop.service.IUserService;
import xyz.slienceme.project_shop.utils.HttpClientHelper;
import xyz.slienceme.project_shop.vo.UserVO;

import java.util.Objects;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Slf4j
@Api(tags = "用户表")
@RestController
@RequestMapping("/app")
public class UserController {

    @Value("${XF.wx_openid_url}")
    private String wx_openid_url;
    @Value("${XF.wx_appid}")
    private String wx_appid;
    @Value("${XF.wx_secret}")
    private String wx_secret;
    @Autowired
    private IUserService userService;

    @ApiOperation("根据js_code获取openid")
    @GetMapping("/getCode")
    public Result getOpenid(@ApiParam("返回值isLogin 0不需要登陆(用户) 1需要登陆(游客)") @RequestParam("code") String code) throws Exception {
        // 根据Code获取Openid
        String openidUrl = wx_openid_url + "appid=" + wx_appid + "&secret=" + wx_secret + "&js_code=" + code + "&grant_type=authorization_code";
        String openidMsg = HttpClientHelper.doPost(openidUrl, "", "UTF-8");
        // 解析返回信息
        JSONObject result = JSON.parseObject(openidMsg);
        log.info("微信登路返回信息：" + result.toString());
        String openid = result.getString("openid");
        String session_key = result.getString("session_key");
        if (Objects.isNull(openid) || "".equals(openid)) {
            return Result.createByErrorMessage("登陆失败请刷新后重试 errcode=" + result.getString("errcode"));
        }
        log.info("根据js_code获取openid接口调用-------get---------</getCode>:  code=" + code);
        return userService.getCode(openid, session_key);
    }

    @ApiOperation("新增成员")
    @PostMapping("/member")
    public Result memberAdd(@RequestHeader("x-access-token") String accessToken,
                            @RequestBody UserVO userVO) throws Exception {
        log.info("新增成员接口调用-------post---------</member>:  user=" + userVO.getUserName());
        return userService.memberAdd(accessToken, userVO);
    }

    @ApiOperation("删除成员")
    @DeleteMapping("/member")
    public Result memberDel(@RequestHeader("x-access-token") String accessToken,
                               @ApiParam(value = "id", required = true) @RequestParam(value = "id") Integer id) throws Exception {
        log.info("删除成员接口调用-------Delete---------</member>:  id=" + id);
        return userService.memberDel(accessToken, id);
    }

    @ApiOperation("修改成员信息")
    @PutMapping("/member")
    public Result memberPut(@RequestHeader("x-access-token") String accessToken,
                               @RequestBody User user) throws Exception {
        log.info("修改成员信息接口调用-------Put---------</member>:  user=" + user.getUserId());
        return userService.memberPut(accessToken, user);
    }

    @ApiOperation("条件查询所有Member")
    @GetMapping("/member")
    public Result member(@RequestHeader("x-access-token") String accessToken,
                             @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                             @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                             @ApiParam(value = "openid") @RequestParam(value = "openid", required = false) String openid,
                          @ApiParam(value = "身份证") @RequestParam(value = "idCard", required = false) String idCard,
                          @ApiParam(value = "用户账号学号") @RequestParam(value = "userNumber", required = false) String userNumber,
                          @ApiParam(value = "用户名称") @RequestParam(value = "userName", required = false) String userName,
                          @ApiParam(value = "用户手机") @RequestParam(value = "userPhone", required = false) String userPhone,
                          @ApiParam(value = "用户地址") @RequestParam(value = "userAddress", required = false) String userAddress) throws Exception {
        //log.info("条件查询所有Member接口调用-------get---------</data>:  ");
        return userService.member(accessToken, pageNo, pageSize, openid, idCard,userNumber,userName,userPhone,userAddress);
    }

    @ApiOperation("查询单个用户")
    @GetMapping("/member/{userId}")
    public Result memberOne(@RequestHeader("x-access-token") String accessToken,
                         @PathVariable("userId") @ApiParam(value = "用户id", required = true) Integer userId) throws Exception {
        //log.info("查询单个用户接口调用-------get---------</member>:  ");
        return userService.memberOne(accessToken, userId);
    }

}
