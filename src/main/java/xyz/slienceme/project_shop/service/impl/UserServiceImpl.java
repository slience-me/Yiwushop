package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.User;
import xyz.slienceme.project_shop.mapper.UserMapper;
import xyz.slienceme.project_shop.service.IUserService;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.utils.PropertyUtil;
import xyz.slienceme.project_shop.vo.TokenVO;
import xyz.slienceme.project_shop.vo.UserVO;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Value("${redis.app.login.token}")
    private String redisAppLoginKey;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static Integer getTime(String timeFormat) {
        String[] num = timeFormat.split(":");
        int sum = 0;
        int hour = Integer.parseInt(num[0]);
        int min = Integer.parseInt(num[1]);
        int sec = Integer.parseInt(num[2]);
        sum = hour * 3600 + min * 60 + sec;
        return sum * 1000;
    }

    /**
     * 微信登陆
     *
     * @param openid     openid
     * @param sessionKey sessionkey
     * @return 微信登陆JSON数据
     * @throws Exception
     */
    @Synchronized
    @Override
    public Result getCode(String openid, String sessionKey) throws Exception {
        //TODO 处理业务
        HashMap<String, Object> hashMap = new HashMap<>();
        User user = userMapper.selectByOpenId(openid);
        TokenVO tokenVo = new TokenVO();
        tokenVo.setOpenId(openid);
        if (Objects.nonNull(user)) {//查询是否绑定了账号 绑定了则直接登陆成功
            tokenVo.setUserId(user.getUserId());
            String token = JWT.sign(tokenVo, Long.parseLong(PropertyUtil.getValue("application.properties", "user.login.long.time")));
            hashMap.put("x-access-token", token);
            hashMap.put("userInfo", user);
            hashMap.put("isLogin", 0);
            hashMap.put("session_key", sessionKey);
            hashMap.put("openid", openid);
            //将token存储到redis中
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            hashOperations.put(redisAppLoginKey, user.getUserId().toString(), token);
            return Result.createBySuccess(hashMap);
        } else {
            tokenVo.setUserId(0);
            String token = JWT.sign(tokenVo, Long.parseLong(PropertyUtil.getValue("application.properties", "user.login.long.time")));
            hashMap.put("x-access-token", token);
            hashMap.put("openid", openid);
            return Result.createByErrorDataAndMsg(hashMap, "用户不存在");
        }

    }

    @Override
    public Result addMember(String accessToken, UserVO userVO) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        User user = new User();
        user.setOpenid(userVO.getOpenid());
        user.setUserName(userVO.getUserName());
        user.setIdCard(userVO.getIdCard());
        user.setUserNumber(userVO.getUserNumber());
        user.setUserPwd(userVO.getUserPwd());
        user.setUserGender(userVO.getUserGender());
        user.setUserPhone(userVO.getUserPhone());
        user.setUserAddress(userVO.getUserAddress());
        user.setCreatedBy(unsign.getUserId());
        userMapper.insertSelective(user);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result deleteMember(String accessToken, Integer id) throws Exception {
        User user = userMapper.selectByPrimaryKey(id);
        user.setIsDelete(1);
        userMapper.updateByPrimaryKeySelective(user);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result updateMember(String accessToken, User user) throws Exception {
        User user1 = userMapper.selectByUserId(user.getUserId());
        if (Objects.isNull(user1)) {
            return Result.createByErrorMessage("userId不存在");
        } else {
            userMapper.updateByPrimaryKeySelective(user);
            return Result.createBySuccessMessage("成功");
        }
    }

    @Override
    public Result members(String accessToken,
                          Integer page,
                          Integer limit,
                          String keyword,
                          String openid) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = userMapper.selectList(keyword, openid);
        return Result.createBySuccess(new PageInfo<>(list));
    }

}
