package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Chat;
import xyz.slienceme.project_shop.mapper.ChatMapper;
import xyz.slienceme.project_shop.service.IChatService;
import xyz.slienceme.project_shop.vo.ChatVO;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class ChatServiceImpl implements IChatService {

    @Autowired
    private ChatMapper chatMapper;

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
    @Override
    public Result chatList(String accessToken, Integer pageNo, Integer pageSize, Integer sellUser, Integer buyUser, Integer msgType, Integer readStatusSell, Integer readStatusBuy, Integer isDeleteBuy, Integer isDeleteSell) {
        PageHelper.startPage(pageNo, pageSize);
        List<HashMap<String, Object>> list = chatMapper.selectList(sellUser, buyUser, msgType, readStatusSell, readStatusBuy, isDeleteBuy, isDeleteSell);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 查询买家聊天列表
     *
     * @param accessToken    请求token
     * @param pageNo         页码
     * @param pageSize       个数
     * @param sellUser       卖家id
     * @param buyUser        卖家id
     * @param msgType        消息类型
     * @param readStatusSell 卖家消息读取状态
     * @param readStatusBuy  买家家消息读取状态
     * @return
     */
    @Override
    public Result chatListForBuy(String accessToken, Integer pageNo, Integer pageSize, Integer sellUser, Integer buyUser, Integer msgType, Integer readStatusSell, Integer readStatusBuy) {
        PageHelper.startPage(pageNo, pageSize);
        List<HashMap<String, Object>> list = chatMapper.selectListForBuy(sellUser, buyUser, msgType, readStatusSell, readStatusBuy);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 查询卖家聊天列表
     *
     * @param accessToken    请求token
     * @param pageNo         页码
     * @param pageSize       个数
     * @param sellUser       卖家id
     * @param buyUser        卖家id
     * @param msgType        消息类型
     * @param readStatusSell 卖家消息读取状态
     * @param readStatusBuy  买家家消息读取状态
     * @return
     */
    @Override
    public Result chatListForSell(String accessToken, Integer pageNo, Integer pageSize, Integer sellUser, Integer buyUser, Integer msgType, Integer readStatusSell, Integer readStatusBuy) {
        PageHelper.startPage(pageNo, pageSize);
        List<HashMap<String, Object>> list = chatMapper.selectListForSell(sellUser, buyUser, msgType, readStatusSell, readStatusBuy);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 添加聊天
     *
     * @param accessToken 请求token
     * @param chatVO      消息vo
     * @return
     */
    @Override
    public Result chatAdd(String accessToken, ChatVO chatVO) {
        Chat chat = new Chat();
        chat.setBuyUser(chatVO.getBuyUser());
        chat.setSellUser(chatVO.getSellUser());
        chat.setMsgType(chatVO.getMsgType());
        chat.setMsgContent(chatVO.getMsgContent());
        chat.setOther(chatVO.getSendUser());
        chatMapper.insertSelective(chat);
        return Result.createBySuccessMessage("成功");
    }

    /**
     * 删除聊天记录
     *
     * @param accessToken 请求token
     * @param chatId      消息id
     * @param userType    用户类型
     * @return
     */
    @Override
    public Result chatDel(String accessToken, Integer chatId, Integer userType) {
        Chat chat = chatMapper.selectByPrimaryKey(chatId);
        if (userType.equals(1)){
            chat.setIsDeleteBuy(1);  //用户类型 1买家  2卖家
        } else if (userType.equals(2)){
            chat.setIsDeleteSell(1);
        } else {
            return Result.createByErrorMessage("userType参数不正确");
        }
        chatMapper.updateByPrimaryKeySelective(chat);
        return Result.createBySuccessMessage("成功");
    }

    /**
     *
     * @param accessToken 请求token
     * @param chat
     * @return
     */
    @Override
    public Result chatPut(String accessToken, Chat chat) {
        Chat chat1 = chatMapper.selectByPrimaryKey(chat.getChatId());
        if (Objects.isNull(chat1)) {
            return Result.createByErrorMessage("聊天id不存在");
        }
        chatMapper.updateByPrimaryKeySelective(chat);
        return Result.createBySuccessMessage("成功");
    }

    /**
     *
     * @param accessToken 请求token
     * @param chatId
     * @return
     */
    @Override
    public Result selectByPrimaryKey(String accessToken, Integer chatId) {
        Chat chat = chatMapper.selectByPrimaryKey(chatId);
        if (Objects.isNull(chat)){
            return Result.createByErrorMessage("id不正确");
        }
        return Result.createBySuccess(chat);
    }

    /**
     *
     * @param accessToken 请求token
     * @param chatId      消息id
     * @param userType    用户类型 1买家  2卖家
     * @return
     */
    @Override
    public Result chatPutUser(String accessToken, Integer chatId, Integer userType) {
        Chat chat1 = chatMapper.selectByPrimaryKey(chatId);
        if (Objects.isNull(chat1)) {
            return Result.createByErrorMessage("聊天id不存在");
        }
        if (userType.equals(1)){
            chat1.setReadStatusBuy(1);  //用户类型 1买家  2卖家
        } else if (userType.equals(2)){
            chat1.setReadStatusSell(1);
        } else {
            return Result.createByErrorMessage("userType参数不正确");
        }
        chatMapper.updateByPrimaryKeySelective(chat1);
        return Result.createBySuccessMessage("成功");
    }
}
