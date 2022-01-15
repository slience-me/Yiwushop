package xyz.slienceme.project_shop.vo;

import lombok.Data;

/**
 * @Author slience_me
 * @Time : 2021/7/16  10:13
 */
@Data
public class PwdVO {

    private String oldPwd;//旧密码
    private String newPwd;//新密码
    private String username;//用户名
}
