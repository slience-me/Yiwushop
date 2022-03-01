package xyz.slienceme.project_shop.config.interceptor;
 //TODO 先不设置限制

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.slienceme.project_shop.dto.User;
import xyz.slienceme.project_shop.mapper.UserMapper;
import xyz.slienceme.project_shop.utils.Delay;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.vo.TokenVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class SecurityInterceptorApp implements HandlerInterceptor {

    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(SecurityInterceptorApp.class);

    private long lastTime = 0;

    @Autowired
    private UserMapper usersMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Value("${redis.app.login.token}")
    private String redisAppLoginKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        logger.info("请求路径：" + url);

        String token = request.getHeader("x-access-token");

        if (StringUtil.isEmpty(token)) {
            getMsg("请登录后访问", request, response, 2);
            return false;
        }

        TokenVO tokenVO = null;
        try {
            tokenVO = JWT.unsign(token, TokenVO.class);
            if (Objects.isNull(token)) {
                getMsg("请登录后访问", request, response, 2);
                return false;
            } else {//判断
                //如果用户ID 为null 则重新登陆
                if (Objects.isNull(tokenVO.getUserId())) {
                    getMsg("请登录后访问", request, response, 3);
                    return false;
                }

                //开始进入请求地址拦截
                HandlerMethod hm = (HandlerMethod) handler;
                Delay delay = hm.getMethodAnnotation(Delay.class);
                if (delay != null) {
                    String biaoshj = tokenVO.getUserId() + url;
                    boolean b = startDelay(biaoshj, delay.time());
                    if (!b) {
                        getMsg("请勿重复请求", request, response, 0);
                        return false;
                    }
                }

                User user = usersMapper.selectByPrimaryKey(tokenVO.getUserId());
                if (Objects.isNull(user)) {//根据用户ID查询用户如果已被删除 则重新登陆
                    getMsg("请登录后访问", request, response, 3);
                    return false;
                }
                if (Objects.isNull(user.getOpenid()) || Objects.equals("", user.getOpenid())) {
                    getMsg("请登录后访问", request, response, 3);
                    return false;
                }
                if (user.getIsDelete() == 1) {
                    getMsg("请登录后访问", request, response, 3);
                    return false;
                }

                try {
                    HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
                    if (!hashOperations.hasKey(redisAppLoginKey, tokenVO.getUserId().toString())) {
                        getMsg("请登录后访问", request, response, 2);
                        return false;
                    } else {
                        String s = hashOperations.get(redisAppLoginKey, tokenVO.getUserId().toString()).toString();
                        if (!s.equals(token)) {
                            getMsg("请登录后访问", request, response, 2);
                            return false;
                        }
                    }
                } catch (Exception e) {
                    logger.error("登陆报错：", e);
                    getMsg("请登录后访问", request, response, 2);
                    return false;
                }

            }
        } catch (Exception e) {
            getMsg("token outdate", request, response, 2);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //处理请求完成后视图渲染之前的处理操作
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //视图渲染之后的操作
    }

    private boolean startDelay(String value, int time) {
        //如果缓存中没有返回true
        boolean b = Boolean.TRUE.equals(redisTemplate.hasKey(value));
        if (!b) {//没有返回通过
            logger.info("time：" + time);
            redisTemplate.opsForValue().set(value, "1", time, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    //封装了返回前端的消息体
    private void getMsg(String msg, HttpServletRequest request, HttpServletResponse response, Integer status) throws Exception {
        JSONObject res = new JSONObject();
        res.put("msg", msg);
        res.put("status", status);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        out = response.getWriter();
        out.append(res.toString());
    }

}
