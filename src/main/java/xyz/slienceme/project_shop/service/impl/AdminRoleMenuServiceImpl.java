package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Admin;
import xyz.slienceme.project_shop.dto.MenuRole;
import xyz.slienceme.project_shop.dto.Role;
import xyz.slienceme.project_shop.mapper.AdminMapper;
import xyz.slienceme.project_shop.mapper.MenuMapper;
import xyz.slienceme.project_shop.mapper.MenuRoleMapper;
import xyz.slienceme.project_shop.mapper.RoleMapper;
import xyz.slienceme.project_shop.service.IAdminRoleMenuService;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.vo.MenuListVO;
import xyz.slienceme.project_shop.vo.RoleUpdateVO;
import xyz.slienceme.project_shop.vo.TokenVO;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AdminRoleMenuServiceImpl implements IAdminRoleMenuService {

    private final Gson gson = new Gson();
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Value("${redis.platform.role-menu-key}")
    private String redisRoleMenuKey;
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 查询菜单
     *
     * @param accessToken 请求token
     * @param roleId      角色id
     */
    @Override
    public Result selectAll(String accessToken, Integer roleId) {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        //一级菜单
        List<MenuListVO> menus1s = menuMapper.selectAll(1, 0, null, roleId);
        for (MenuListVO menus1 : menus1s) {
            //二级菜单
            List<MenuListVO> menus2s = menuMapper.selectAll(null, null, menus1.getMenuId(), roleId);
            for (MenuListVO menus2 : menus2s) {
                //三级菜单
                List<MenuListVO> menus3s = menuMapper.selectAll(null, null, menus2.getMenuId(), roleId);
                for (MenuListVO menus3 : menus3s) {
                    //四级操作
                    List<MenuListVO> op4s = menuMapper.selectAll(null, null, menus3.getMenuId(), roleId);
                    menus3.setChildren(op4s);
                }
                menus2.setChildren(menus3s);
            }
            menus1.setChildren(menus2s);
        }
        /*AdminLogs adminLogs = new AdminLogs();
        adminLogs.setAdminId(unsign.getUserId());
        adminLogs.setAdminLogsOperate("查询菜单列表, roleId=" + roleId);
        adminLogsMapper.insertSelective(adminLogs);*/
        return Result.createBySuccess(menus1s);
    }

    /**
     * 角色列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    @Override
    public Result selectRoleAll(String accessToken, Integer page, Integer limit, String keyword) {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        if (Objects.isNull(page)) {
            List<HashMap<String, Object>> list = roleMapper.selectAll(keyword);
            return Result.createBySuccess(list);
        }
        PageHelper.startPage(page, limit);
        List<HashMap<String, Object>> list = roleMapper.selectAll(keyword);
        for (HashMap<String, Object> role : list) {
            //从redis取
            List<?> menus;
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            if (hashOperations.hasKey(redisRoleMenuKey, role.get("roleId").toString())) {
                menus = gson.fromJson(hashOperations.get(redisRoleMenuKey, role.get("roleId").toString()), List.class);
            } else {
                menus = selectByRoleId(accessToken, Integer.valueOf(role.get("roleId").toString()), null, null);
                hashOperations.put(redisRoleMenuKey, role.get("roleId").toString(), gson.toJson(menus));
            }
            role.put("menus", menus);
        }
        /*AdminLogs adminLogs = new AdminLogs();
        adminLogs.setAdminId(unsign.getUserId());
        adminLogs.setAdminLogsOperate("查询角色列表");
        adminLogsMapper.insertSelective(adminLogs);*/
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 查询角色详细信息
     *
     * @param accessToken 请求token
     * @param roleId      角色id
     */
    @Override
    public Result selectByPrimaryKey(String accessToken, Integer roleId) {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        /*AdminLogs adminLogs = new AdminLogs();
        adminLogs.setAdminId(unsign.getUserId());
        adminLogs.setAdminLogsOperate("查询角色详细信息, roleId=" + roleId);
        adminLogsMapper.insertSelective(adminLogs);*/
        return Result.createBySuccess(roleMapper.selectByPrimaryKey(roleId));
    }

    /**
     * 新建角色
     *
     * @param accessToken  请求token
     * @param roleUpdateVO 对象
     */
    @Override
    public Result insert(String accessToken, RoleUpdateVO roleUpdateVO) {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        //查询名称时候存在
        Role role = roleMapper.selectByName(roleUpdateVO.getRoleName());
        if (Objects.nonNull(role)) {
            return Result.createByErrorMessage("角色名称已存在");
        }
        Role role1 = new Role();
        role1.setCreateBy(unsign.getUserId());
        role1.setRoleStatus(0);
        role1.setRoleName(roleUpdateVO.getRoleName());
        roleMapper.insertSelective(role1);
        Role role2 = roleMapper.selectByName(roleUpdateVO.getRoleName());
        //添加
        for (int i = 0; i < roleUpdateVO.getMenuIds().length; i++) {
            MenuRole menuRole = new MenuRole();
            menuRole.setMenuId(roleUpdateVO.getMenuIds()[i]);
            menuRole.setRoleId(role2.getRoleId());
            menuRoleMapper.insertSelective(menuRole);
        }
        //存到redis
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        List<MenuListVO> menus = selectByRoleId(accessToken, role2.getRoleId(), null, null);
        hashOperations.put(redisRoleMenuKey, role2.getRoleId().toString(), gson.toJson(menus));
        return Result.createBySuccessMessage("成功");
    }

    /**
     * 修改角色信息
     *
     * @param accessToken  请求token
     * @param roleUpdateVO 对象
     */
    @Override
    public Result updateByPrimaryKey(String accessToken, RoleUpdateVO roleUpdateVO) {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Role role = roleMapper.selectByName(roleUpdateVO.getRoleName());
        if (Objects.nonNull(role)) {
            if (roleUpdateVO.getRoleId().intValue() != role.getRoleId().intValue()) {
                return Result.createByErrorMessage("角色名称已存在");
            }
        }
        Role role1 = new Role();
        role1.setRoleId(roleUpdateVO.getRoleId());
        role1.setRoleName(roleUpdateVO.getRoleName());
        roleMapper.updateByPrimaryKeySelective(role1);

        if (roleUpdateVO.getMenuIds() != null && roleUpdateVO.getMenuIds().length > 0) {
            //删除关联
            menuRoleMapper.delByRoleId(roleUpdateVO.getRoleId());
            //添加
            for (int i = 0; i < roleUpdateVO.getMenuIds().length; i++) {
                MenuRole menuRole = new MenuRole();
                menuRole.setMenuId(roleUpdateVO.getMenuIds()[i]);
                menuRole.setRoleId(roleUpdateVO.getRoleId());
                menuRoleMapper.insertSelective(menuRole);
            }
            //存到redis
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            List<MenuListVO> menus = selectByRoleId(accessToken, role1.getRoleId(), null, null);
            hashOperations.put(redisRoleMenuKey, role1.getRoleId().toString(), gson.toJson(menus));
        }
        return Result.createBySuccessMessage("成功");
    }

    /**
     * 获取用户权限
     *
     * @param accessToken 请求token
     */
    @Override
    public Result getPermission(String accessToken) {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Admin admin = adminMapper.selectByPrimaryKey(unsign.getUserId());
        Integer roleId = admin.getRoleId();
        //查询用户信息
        List<MenuListVO> menus = menuMapper.selectAllByRoleId(roleId);
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (MenuListVO menu : menus) {
            if (menu.getType() == 0 && StringUtils.isNotBlank(menu.getMenuName())) {
                Map<String, Object> m = new HashMap<>();
                m.put("describe", menu.getMenuTitle());
                m.put("permissionId", menu.getMenuName());
                m.put("menuId", menu.getMenuId());
                resultList.add(m);
            }
        }
        menus.removeIf(menu -> menu.getType() == 0);
        for (Map<String, Object> m : resultList) {
            List<Map<String, Object>> actionEntitySet = new ArrayList<>();
            for (MenuListVO menu : menus) {
                if (m.get("menuId").equals(menu.getMenuParentId()) && StringUtils.isNotBlank(menu.getMenuName())) {
                    Map<String, Object> button = new HashMap<>();
                    button.put("action", menu.getMenuName());
                    button.put("describe", menu.getMenuTitle());
                    button.put("defaultCheck", false);
                    button.put("menuId", menu.getMenuId());
                    actionEntitySet.add(button);
                }
            }
            m.put("actionEntitySet", actionEntitySet);
            menus.removeIf(menu -> m.get("menuId").equals(menu.getMenuParentId()));
        }
        return Result.createBySuccess(resultList);
    }

    /**
     * 删除角色
     *
     * @param accessToken 请求token
     * @param roleId      角色id
     */
    @Override
    public Result rolelistDel(String accessToken, Integer roleId) {
        if (roleId == 1) {
            return Result.createByErrorMessage("默认角色不可删除");
        }
        //判断此角色下是否有管理员
        List<Admin> list = adminMapper.selectByRoleId(roleId);
        if (Objects.nonNull(list) && list.size() != 0) {
            return Result.createByErrorMessage("角色已绑定管理员不可删除");
        }
        Role role = new Role();
        role.setRoleStatus(1);
        role.setRoleId(roleId);
        roleMapper.updateByPrimaryKeySelective(role);
        return Result.createBySuccessMessage("成功");
    }

    private List<MenuListVO> selectByRoleId(String accessToken, Integer roleId, Integer menuParentId, List<MenuListVO> menus) {
        List<MenuListVO> menu1s = new ArrayList<>();
        if (menuParentId == null) {
            menus = menuMapper.selectAllByRoleId(roleId);
        }
        for (MenuListVO menu : menus) {
            if ((menuParentId == null && menu.getMenuLevel() == 1) || Objects.equals(menuParentId, menu.getMenuParentId())) {
                menu1s.add(menu);
            }
        }
        for (MenuListVO menu1 : menu1s) {
            menu1.setChildren(selectByRoleId(accessToken, roleId, menu1.getMenuId(), menus));
        }
        menus.removeAll(menu1s);
        return menu1s;
    }

}
