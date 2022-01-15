package xyz.slienceme.project_shop.vo;

import lombok.Data;

/**
 * @Author slience_me
 * @Time : 2021/7/16  8:25
 */
@Data
public class TokenVO {

    private Integer userId;//用户id
    private String openId;//openid
    private Integer status;//用户状态 0用户，1管理员
}
