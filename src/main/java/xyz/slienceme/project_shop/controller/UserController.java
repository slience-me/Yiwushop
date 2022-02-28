package xyz.slienceme.project_shop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Admin;
import xyz.slienceme.project_shop.dto.User;
import xyz.slienceme.project_shop.mapper.AdminMapper;
import xyz.slienceme.project_shop.service.IUserService;
import xyz.slienceme.project_shop.utils.HttpClientHelper;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.utils.PropertyUtil;
import xyz.slienceme.project_shop.vo.TokenVO;
import xyz.slienceme.project_shop.vo.UserVO;

import java.util.HashMap;
import java.util.Objects;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
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
    @Value("${redis.app.login.token}")
    private String redisAppLoginKey;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private IUserService userService;
    @Autowired
    private AdminMapper adminMapper;

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
    public Result addMember(@RequestHeader("x-access-token") String accessToken,
                            @RequestBody UserVO userVO) throws Exception {
        log.info("新增成员接口调用-------post---------</member>:  user=" + userVO.getUserName());
        return userService.addMember(accessToken, userVO);
    }

    @ApiOperation("删除成员")
    @DeleteMapping("/member")
    public Result deleteMember(@RequestHeader("x-access-token") String accessToken,
                               @ApiParam(value = "id", required = true) @RequestParam(value = "id") Integer id) throws Exception {
        log.info("删除成员接口调用-------Delete---------</member>:  id=" + id);
        return userService.deleteMember(accessToken, id);
    }

    @ApiOperation("修改成员信息")
    @PutMapping("/member")
    public Result updateMember(@RequestHeader("x-access-token") String accessToken,
                               @RequestBody User user) throws Exception {
        log.info("修改成员信息接口调用-------Put---------</member>:  user=" + user.getUserId());
        return userService.updateMember(accessToken, user);
    }


    @ApiOperation("按页查询所有Member")
    @GetMapping("/member")
    public Result getMembers(@RequestHeader("x-access-token") String accessToken,
                             @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                             @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                             @ApiParam(value = "名称、描述") @RequestParam(value = "keyword", required = false) String keyword,
                             @ApiParam(value = "openid") @RequestParam(value = "openid", required = false) String openid) throws Exception {
        log.info("按页查询所有Member接口调用-------get---------</member>:  ");
        return userService.members(accessToken, pageNo, pageSize, keyword, openid);
    }

}
