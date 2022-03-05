package xyz.slienceme.project_shop.vo;

import lombok.Data;

@Data
public class ChatVO {

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
     * 发送者
     */
    private String sendUser;

}