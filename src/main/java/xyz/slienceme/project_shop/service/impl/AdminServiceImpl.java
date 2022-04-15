package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Admin;
import xyz.slienceme.project_shop.dto.AdminLogs;
import xyz.slienceme.project_shop.mapper.AdminLogsMapper;
import xyz.slienceme.project_shop.mapper.AdminMapper;
import xyz.slienceme.project_shop.service.IAdminService;
import org.springframework.data.redis.core.RedisTemplate;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.utils.PropertyUtil;
import xyz.slienceme.project_shop.vo.AdminVO;
import xyz.slienceme.project_shop.vo.LoginVO;
import xyz.slienceme.project_shop.vo.PwdVO;
import xyz.slienceme.project_shop.vo.TokenVO;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Value("${redis.admin.login.token}")
    private String redisAdminLoginKey;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminLogsMapper adminLogsMapper;

    /**
     * 管理员登陆
     *
     * @param loginVO 登录信息对象
     */
    @Override
    public Result login(LoginVO loginVO) throws Exception {
        HashMap<String, Object> admin = adminMapper.selectByNameAndPwd(loginVO.getUsername(), loginVO.getPwd());
        if (Objects.isNull(admin)) {
            System.out.println("用户不存在！" + loginVO);
            Admin admin1 = adminMapper.selectByName(loginVO.getUsername());
            System.out.println("admin1 = " + admin1);
            int num=0;
            if (Objects.nonNull(admin1)){
                int errorTimes = admin1.getErrorTimes() + 1;
                num=errorTimes;
                admin1.setErrorTimes(errorTimes);
                if (errorTimes >= 3){
                    admin1.setAdminStatus(1);
                    adminMapper.updateByPrimaryKeySelective(admin1);
                    return Result.createByErrorMessage("账户已被封禁,请联系管理员");
                }
                adminMapper.updateByPrimaryKeySelective(admin1);
            }
            return Result.createByErrorMessage("账号或密码错误"+ num +"次, 密码错误三次账户将被封禁");
        } else {
            System.out.println("用户存在！" + loginVO);
            if ((int)admin.get("adminStatus") == 1){
                return Result.createByErrorMessage("该账户已被封禁，请联系管理员解除");
            }
            Object adminId = admin.get("adminId");
            Admin admin1 = adminMapper.selectByPrimaryKey((Integer) adminId);
            admin1.setErrorTimes(0);
            adminMapper.updateByPrimaryKeySelective(admin1);
            TokenVO tokenVo = new TokenVO();
            tokenVo.setUserId(Integer.parseInt(admin.get("adminId").toString()));
            tokenVo.setStatus(1);
            HashMap<String, Object> hashMap = new HashMap<>();
            String token = JWT.sign(tokenVo, Long.parseLong(PropertyUtil.getValue("application.properties", "user.login.long.time")));
            hashMap.put("x-access-token", token);
            hashMap.put("userInfo", admin);
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            hashOperations.put(redisAdminLoginKey, admin.get("adminId").toString(), token);
            adminLogsMapper.insertSelective(new AdminLogs(tokenVo.getUserId(), "管理员登录了 " + admin.get("adminName")));
            return Result.createBySuccess(hashMap);
        }
    }

    @Override
    public Result adminList(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = adminMapper.selectList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }


    @Override
    public Result adminAdd(String accessToken, AdminVO adminVO) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Admin admin1 = adminMapper.selectByName(adminVO.getAdminNumber());
        if (Objects.nonNull(admin1)) {
            return Result.createByErrorMessage("账号已存在");
        }
        Admin admin = new Admin();
        admin.setAdminName(adminVO.getAdminName());
        admin.setAdminNumber(adminVO.getAdminNumber());
        if (Objects.isNull(adminVO.getAdminPwd())) {
            admin.setAdminPwd("dd4b21e9ef71e1291183a46b913ae6f2");
        } else {
            admin.setAdminPwd(adminVO.getAdminPwd());
        }
        admin.setRoleId(adminVO.getRoleId());
        admin.setCreatedBy(unsign.getUserId());
        int flag = adminMapper.insertSelective(admin);
        if(flag > 0){
            adminLogsMapper.insertSelective(new AdminLogs(unsign.getUserId(), "新增了管理员 " + admin.getAdminName()));
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    @Override
    public Result adminDel(String accessToken, Integer adminId) throws Exception {
        Integer userId = JWT.unsign(accessToken, TokenVO.class).getUserId();
        if (adminId == 1) {
            return Result.createByErrorMessage("默认管理员不可删除");
        }
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        admin.setIsDelete(1);
        int flag = adminMapper.updateByPrimaryKeySelective(admin);
        if(flag > 0){
            adminLogsMapper.insertSelective(new AdminLogs(userId, "删除了管理员 " + admin.getAdminName()));
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }


    @Override
    public Result adminPut(String accessToken, Admin admin) throws Exception {
        Integer userId = JWT.unsign(accessToken, TokenVO.class).getUserId();
        if (admin.getAdminId() == 1) {
            return Result.createByErrorMessage("默认管理员不可修改");
        }
        Admin admin1 = adminMapper.selectByPrimaryKey(admin.getAdminId());
        if (Objects.isNull(admin1)) {
            return Result.createByErrorMessage("账号不存在");
        }
        int flag = adminMapper.updateByPrimaryKeySelective(admin);
        if(flag > 0){
            adminLogsMapper.insertSelective(new AdminLogs(userId,"更新了管理员 " + admin.getAdminName() + "的信息"));
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    /**
     * 修改当前登陆账号的密码
     *
     * @param accessToken 请求token
     * @param pwdVO       密码封装对象
     */
    @Override
    public Result adminPwdPut(String accessToken, PwdVO pwdVO) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Admin admin = adminMapper.selectByPrimaryKey(unsign.getUserId());
        if (!Objects.equals(pwdVO.getOldPwd(), admin.getAdminPwd())) {
            return Result.createByErrorMessage("旧密码不正确");
        }
        admin.setAdminPwd(pwdVO.getNewPwd());
        int flag = adminMapper.updateByPrimaryKeySelective(admin);
        if(flag > 0){
            adminLogsMapper.insertSelective(new AdminLogs(unsign.getUserId(),"修改了管理员 " + admin.getAdminName() + "的密码"));
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    /**
     * 忘记密码去重置密码
     *
     * @param accessToken 请求token
     * @param adminId     管理员id
     */
    @Override
    public Result adminPwd(String accessToken, Integer adminId) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Admin admin = new Admin();
        admin.setAdminPwd("00000000");
        admin.setAdminId(adminId);
        Admin admin1 = adminMapper.selectByPrimaryKey(adminId);
        if (Objects.isNull(admin1)) {
            return Result.createByErrorMessage("失败,用户不存在!");
        } else {
            adminLogsMapper.insertSelective(new AdminLogs(unsign.getUserId(),"重置了管理员 " + admin.getAdminName() + "的密码"));
            adminMapper.updateByPrimaryKeySelective(admin);
            return Result.createBySuccessMessage("成功");
        }
    }


    /**
     * admin退出登陆
     *
     * @param accessToken 请求token
     */
    @Override
    public Result adminOut(String accessToken) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        redisTemplate.opsForHash().delete(redisAdminLoginKey, unsign.getUserId().toString());
        return Result.createBySuccessMessage("成功");
    }


    /**
     * 解封账户
     *
     * @param adminId 管理员id
     * @return
     */
    @Override
    public Result UnlockAccount(String accessToken ,Integer adminId) {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        admin.setAdminStatus(0);//设置管理员状态未0 解封
        admin.setErrorTimes(0);//同时将错误次数归零
        int flag = adminMapper.updateByPrimaryKeySelective(admin);
        if(flag > 0){
            adminLogsMapper.insertSelective(new AdminLogs(unsign.getUserId(),"解封了管理员账户 " + admin.getAdminName()));
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }
}
