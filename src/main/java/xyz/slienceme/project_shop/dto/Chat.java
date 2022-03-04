package xyz.slienceme.project_shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Chat {

    /**
     * 自增id
     */
    private Integer chatId;

    /**
     * 卖家id
     */
    private Integer sellUser;

    /**
     * 买家id
     */
    private Integer buyUser;

    /**
     * 消息类型 0 文字 1 图片
     */
    private Integer msgType;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 卖家读取状态
     */
    private Integer readStatusSell;

    /**
     * 买家读取状态
     */
    private Integer readStatusBuy;

    /**
     * 预留空间
     */
    private String other;

    /**
     * 买家删除状态
     */
    private Integer isDeleteBuy;

    /**
     * 卖家删除状态
     */
    private Integer isDeleteSell;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

}