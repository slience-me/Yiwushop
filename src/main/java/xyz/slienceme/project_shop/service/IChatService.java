package xyz.slienceme.project_shop.service;

import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Chat;
import xyz.slienceme.project_shop.vo.ChatVO;

/**
 * <p>
 * 聊天表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-03-04
 */
public interface IChatService {

    /**
     * 条件查询聊天列表
     *
     * @param accessToken    请求token
     * @param pageNo         页码
     * @param pageSize       个数
     * @param sellUser       卖家id
     * @param buyUser        卖家id
     * @param msgType        消息类型
     * @param readStatusSell 卖家消息读取状态
     * @param readStatusBuy  买家家消息读取状态
     * @param isDeleteBuy    买家删除状态
     * @param isDeleteSell   卖家删除状态
     * @return
     */
    Result chat(String accessToken,
                    Integer pageNo,
                    Integer pageSize,
                    Integer sellUser,
                    Integer buyUser,
                    Integer msgType,
                    Integer readStatusSell,
                    Integer readStatusBuy,
                    Integer isDeleteBuy,
                    Integer isDeleteSell);


    /**
     * 添加聊天
     *
     * @param accessToken 请求token
     * @param chatVO      消息vo
     * @return
     */
    Result chatAdd(String accessToken,
                   ChatVO chatVO);

    /**
     * 删除聊天记录
     *
     * @param accessToken 请求token
     * @param chatId      消息id
     * @param userType    用户类型 1买家  2卖家
     * @return
     */
    Result chatDel(String accessToken,
                   Integer chatId,
                   Integer userType);

    /**
     * 更新聊天
     *
     * @param accessToken 请求token
     * @param chat
     * @return
     */
    Result chatPut(String accessToken, Chat chat);

    /**
     * 查询单个聊天
     *
     * @param accessToken 请求token
     * @param chatId
     * @return
     */
    Result selectByPrimaryKey(String accessToken, Integer chatId);

}
