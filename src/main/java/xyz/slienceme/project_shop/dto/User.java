package xyz.slienceme.project_shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Data
@ApiModel(value = "User用户表的实体", reference = "User")
public class User {
    /**
     * 用户id
     */
    @ApiParam(value = "用户id", required = true)
    private Integer userId;

    /**
     * openid
     */
    @ApiParam(value = "openid")
    private String openid;

    /**
     * 身份证
     */
    @ApiParam(value = "身份证")
    private String idCard;

    /**
     * 用户账号学号
     */
    @ApiParam(value = "用户账号学号")
    private String userNumber;

    /**
     * 用户密码
     */
    @ApiParam(value = "用户密码")
    private String userPwd;

    /**
     * 用户头像
     */
    private String userAvatarurl;

    /**
     * 用户名称
     */
    @ApiParam(value = "用户名称")
    private String userName;

    /**
     * 用户性别
     */
    @ApiParam(value = "用户性别")
    private Integer userGender;

    /**
     * 用户手机
     */
    @ApiParam(value = "用户手机")
    private String userPhone;

    /**
     * 用户地址
     */
    @ApiParam(value = "用户地址")
    private String userAddress;

    /**
     * 信用值
     */
    @ApiParam(value = "管理员名称")
    private Integer userCredit;

    /**
     * 状态
     */
    @ApiParam(value = "状态")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @ApiParam(value = "创建时间")
    private LocalDateTime createdTime;

    /**
     * 创建人
     */
    @ApiParam(value = "创建人")
    private Integer createdBy;

}