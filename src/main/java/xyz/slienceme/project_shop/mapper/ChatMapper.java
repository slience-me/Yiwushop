package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.Chat;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ChatMapper {
    int deleteByPrimaryKey(Integer chatId);

    int insert(Chat record);

    int insertSelective(Chat record);

    Chat selectByPrimaryKey(Integer chatId);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);

    /**
     * 条件查询聊天列表
     *
     * @param sellUser       卖家id
     * @param buyUser        卖家id
     * @param msgType        消息类型
     * @param readStatusSell 卖家消息读取状态
     * @param readStatusBuy  买家家消息读取状态
     * @param isDeleteBuy    买家删除状态
     * @param isDeleteSell   卖家删除状态
     * @return
     */
    List<HashMap<String, Object>> selectList(@Param("sellUser") Integer sellUser,
                                             @Param("buyUser") Integer buyUser,
                                             @Param("msgType") Integer msgType,
                                             @Param("readStatusSell") Integer readStatusSell,
                                             @Param("readStatusBuy") Integer readStatusBuy,
                                             @Param("isDeleteBuy") Integer isDeleteBuy,
                                             @Param("isDeleteSell") Integer isDeleteSell);

    /**
     * 查询买家聊天列表
     * @param sellUser       卖家id
     * @param buyUser        卖家id
     * @param msgType        消息类型
     * @param readStatusSell 卖家消息读取状态
     * @param readStatusBuy  买家家消息读取状态
     * @return
     */
    List<HashMap<String, Object>> selectListForBuy(@Param("sellUser") Integer sellUser,
                                                   @Param("buyUser") Integer buyUser,
                                                   @Param("msgType") Integer msgType,
                                                   @Param("readStatusSell") Integer readStatusSell,
                                                   @Param("readStatusBuy") Integer readStatusBuy);

    /**
     * 查询卖家聊天列表
     * @param sellUser       卖家id
     * @param buyUser        卖家id
     * @param msgType        消息类型
     * @param readStatusSell 卖家消息读取状态
     * @param readStatusBuy  买家家消息读取状态
     * @return
     */
    List<HashMap<String, Object>> selectListForSell(@Param("sellUser") Integer sellUser,
                                                    @Param("buyUser") Integer buyUser,
                                                    @Param("msgType") Integer msgType,
                                                    @Param("readStatusSell") Integer readStatusSell,
                                                    @Param("readStatusBuy") Integer readStatusBuy);
}