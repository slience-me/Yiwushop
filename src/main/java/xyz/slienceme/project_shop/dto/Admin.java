package xyz.slienceme.project_shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Data
@ApiModel(value = "Admin管理员表的实体", reference = "Admin")
public class Admin {
    /**
     * 管理员id
     */
    @ApiParam(value = "管理员id", required = true)
    private Integer adminId;

    /**
     * 管理员名称
     */
    @ApiParam(value = "管理员名称")
    private String adminName;

    /**
     * 管理员账号
     */
    @ApiParam(value = "管理员账号")
    private String adminNumber;

    /**
     * 管理员密码
     */
    @ApiParam(value = "管理员密码")
    private String adminPwd;

    /**
     * 角色id
     */
    @ApiParam(value = "角色id")
    private Integer roleId;

    /**
     * 密码错误次数
     */
    private Integer errorTimes;

    /**
     * 封禁状态 0 开放 1封禁
     */
    private Integer adminStatus;

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