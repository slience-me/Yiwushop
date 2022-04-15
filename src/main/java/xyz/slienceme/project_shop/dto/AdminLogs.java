package xyz.slienceme.project_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * 管理员操作日志
 * </p>
 *
 * @author slience_me
 * @since 2022-03-19
 */
@NoArgsConstructor
@Data
public class AdminLogs {


    /**
     * 自增ID
     */
    private Integer adminLogsId;

    /**
     * 操作
     */
    private String adminLogsOperate;

    /**
     * 管理员ID
     */
    private Integer adminId;

    /**
     * 状态 0正常 1删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    public AdminLogs(Integer adminId, String adminLogsOperate) {
        this.adminId = adminId;
        this.adminLogsOperate = adminLogsOperate;
    }
}