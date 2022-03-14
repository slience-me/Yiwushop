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

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    public Result memberAdd(String accessToken, UserVO userVO) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        User user1 = userMapper.selectByOpenId(userVO.getOpenid());
        if (Objects.nonNull(user1)){
            return Result.createByErrorMessage("该用户已存在！");
        } else {
            User user = new User();
            user.setOpenid(userVO.getOpenid());
            user.setUserName(userVO.getUserName());
            user.setIdCard(userVO.getIdCard());
            user.setUserNumber(userVO.getUserNumber());
            user.setUserPwd(userVO.getUserPwd());
            user.setUserAvatarurl(userVO.getUserAvatarurl());
            user.setUserGender(userVO.getUserGender());
            user.setUserPhone(userVO.getUserPhone());
            user.setUserAddress(userVO.getUserAddress());
            user.setCreatedBy(unsign.getUserId());
            userMapper.insertSelective(user);
        }
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result memberDel(String accessToken, Integer id) throws Exception {
        User user = userMapper.selectByPrimaryKey(id);
        user.setIsDelete(1);
        userMapper.updateByPrimaryKeySelective(user);
        return Result.createBySuccessMessage("成功");
    }

    @Override
    public Result memberPut(String accessToken, User user) throws Exception {
        User user1 = userMapper.selectByUserId(user.getUserId());
        if (Objects.isNull(user1)) {
            return Result.createByErrorMessage("userId不存在");
        } else {
            userMapper.updateByPrimaryKeySelective(user);
            return Result.createBySuccessMessage("成功");
        }
    }

    @Override
    public Result member(String accessToken, Integer page, Integer limit, String openid, String idCard, String userNumber, String userName, String userPhone, String userAddress) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = userMapper.selectConditionList(openid, idCard, userNumber, userName, userPhone, userAddress);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    @Override
    public Result memberOne(String accessToken, Integer id) throws Exception {
        User user = userMapper.selectByPrimaryKey(id);
        if (Objects.isNull(user)) {
            return Result.createByErrorMessage("id不正确");
        }
        return Result.createBySuccess(user);
    }

}
