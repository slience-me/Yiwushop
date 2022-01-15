package xyz.slienceme.project_shop.vo;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {

    /**
     * openid
     */
    private String openid;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 用户账号学号
     */
    private String userNumber;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户性别
     */
    private Integer userGender;

    /**
     * 用户手机
     */
    private String userPhone;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 信用值
     */
    private Integer userCredit;

    /**
     * 状态
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 创建人
     */
    private Integer createdBy;
}
