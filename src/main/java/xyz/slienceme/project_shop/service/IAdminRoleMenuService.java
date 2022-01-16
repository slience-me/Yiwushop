package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.vo.RoleUpdateVO;

public interface IAdminRoleMenuService {


    /**
     * 查询菜单
     *
     * @param accessToken 请求token
     * @param roleId      角色id
     */
    Result selectAll(String accessToken,
                     Integer roleId);

    /**
     * 角色列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    Result selectRoleAll(String accessToken,
                         Integer page,
                         Integer limit,
                         String keyword);

    /**
     * 查询角色详细信息
     *
     * @param accessToken 请求token
     * @param roleId      角色id
     */
    Result selectByPrimaryKey(String accessToken,
                              Integer roleId);

    /**
     * 新建角色
     *
     * @param accessToken  请求token
     * @param roleUpdateVO 对象
     */
    Result insert(String accessToken,
                  RoleUpdateVO roleUpdateVO);

    /**
     * 修改角色信息
     *
     * @param accessToken  请求token
     * @param roleUpdateVO 对象
     */
    Result updateByPrimaryKey(String accessToken,
                              RoleUpdateVO roleUpdateVO);

    /**
     * 获取用户权限
     *
     * @param accessToken 请求token
     */
    Result getPermission(String accessToken);

    /**
     * 删除角色
     *
     * @param accessToken 请求token
     * @param roleId      角色id
     */
    Result rolelistDel(String accessToken,
                       Integer roleId);
}
