package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 平台菜单表
 * </p>
 *
 * @author slience_me
 * @since 2021-08-09
 */
@Data
public class Menu {

    /**
     * 菜单id 自增id
     */
    private Integer menuId;

    /**
     * 菜单名称 中文名
     */
    private String menuTitle;

    /**
     * 菜单文件名 英文名
     */
    private String menuName;

    /**
     * 菜单级别
     */
    private Integer menuLevel;

    /**
     * 菜单根id 最根部的菜单id
     */
    private Integer menuRootId;

    /**
     * 菜单父id 直属的父菜单id
     */
    private Integer menuParentId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 菜单路径 路由路径
     */
    private String menuPath;

    /**
     * 菜单完整路由 菜单完整路由
     */
    private String menuRouter;

    /**
     * 菜单图标 图标
     */
    private String menuIcon;

    /**
     * 接口方法 get post put delete
     */
    private String method;

    /**
     * 接口路径 api_path
     */
    private String apiPath;

    /**
     * 是否隐藏
     */
    private Integer hidden;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态 0正常 1删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
