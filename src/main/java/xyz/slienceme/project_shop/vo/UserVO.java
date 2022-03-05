package xyz.slienceme.project_shop.vo;
import lombok.Data;

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
     * 用户头像
     */
    private String userAvatarurl;

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

}
