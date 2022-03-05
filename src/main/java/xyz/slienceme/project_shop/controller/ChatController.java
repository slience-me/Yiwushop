package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Chat;
import xyz.slienceme.project_shop.service.IChatService;
import xyz.slienceme.project_shop.vo.ChatVO;

/**
 * <p>
 * 聊天前端控制 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-03-04
 */
@Slf4j
@Api(tags = "聊天表")
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private IChatService chatService;

    @ApiOperation("条件查询聊天列表")
    @GetMapping("")
    public Result chatList(@RequestHeader("x-access-token") String accessToken,
                           @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                           @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                           @ApiParam(value = "卖家id") @RequestParam(value = "sellUser", required = false) Integer sellUser,
                           @ApiParam(value = "买家id") @RequestParam(value = "buyUser", required = false) Integer buyUser,
                           @ApiParam(value = "消息类型 0 文字 1 图片") @RequestParam(value = "msgType", required = false) Integer msgType,
                           @ApiParam(value = "卖家读取状态") @RequestParam(value = "readStatusSell", required = false) Integer readStatusSell,
                           @ApiParam(value = "买家读取状态") @RequestParam(value = "readStatusBuy", required = false) Integer readStatusBuy,
                           @ApiParam(value = "买家删除状态") @RequestParam(value = "isDeleteBuy", required = false) Integer isDeleteBuy,
                           @ApiParam(value = "卖家删除状态") @RequestParam(value = "isDeleteSell", required = false) Integer isDeleteSell) throws Exception {
        //log.info("查询聊天列表接口调用--get---</chat>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",sellUser=" + sellUser + ",buyUser=" + buyUser + ",msgType=" + msgType + ",readStatusSell=" + readStatusSell + ",readStatusBuy=" + readStatusBuy + ",isDeleteBuy=" + isDeleteBuy + ",isDeleteSell=" + isDeleteSell);
        return chatService.chatList(accessToken, pageNo, pageSize, sellUser, buyUser, msgType, readStatusSell, readStatusBuy, isDeleteBuy, isDeleteSell);
    }

    @ApiOperation("查询买家聊天列表")
    @GetMapping("/buy")
    public Result chatListForBuy(@RequestHeader("x-access-token") String accessToken,
                                 @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                 @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                                 @ApiParam(value = "卖家id") @RequestParam(value = "sellUser", required = false) Integer sellUser,
                                 @ApiParam(value = "买家id") @RequestParam(value = "buyUser", required = false) Integer buyUser,
                                 @ApiParam(value = "消息类型 0 文字 1 图片") @RequestParam(value = "msgType", required = false) Integer msgType,
                                 @ApiParam(value = "卖家读取状态") @RequestParam(value = "readStatusSell", required = false) Integer readStatusSell,
                                 @ApiParam(value = "买家读取状态") @RequestParam(value = "readStatusBuy", required = false) Integer readStatusBuy) throws Exception {
        //log.info("查询买家聊天列表接口调用--get---</chat/buy>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",sellUser=" + sellUser + ",buyUser=" + buyUser + ",msgType=" + msgType + ",readStatusSell=" + readStatusSell + ",readStatusBuy=" + readStatusBuy);
        return chatService.chatListForBuy(accessToken, pageNo, pageSize, sellUser, buyUser, msgType, readStatusSell, readStatusBuy);
    }

    @ApiOperation("查询卖家聊天列表")
    @GetMapping("/sell")
    public Result chatListForSell(@RequestHeader("x-access-token") String accessToken,
                                  @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                  @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                                  @ApiParam(value = "卖家id") @RequestParam(value = "sellUser", required = false) Integer sellUser,
                                  @ApiParam(value = "买家id") @RequestParam(value = "buyUser", required = false) Integer buyUser,
                                  @ApiParam(value = "消息类型 0 文字 1 图片") @RequestParam(value = "msgType", required = false) Integer msgType,
                                  @ApiParam(value = "卖家读取状态") @RequestParam(value = "readStatusSell", required = false) Integer readStatusSell,
                                  @ApiParam(value = "买家读取状态") @RequestParam(value = "readStatusBuy", required = false) Integer readStatusBuy) throws Exception {
        //log.info("查询买家聊天列表接口调用--get---</chat/sell>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",sellUser=" + sellUser + ",buyUser=" + buyUser + ",msgType=" + msgType + ",readStatusSell=" + readStatusSell + ",readStatusBuy=" + readStatusBuy);
        return chatService.chatListForSell(accessToken, pageNo, pageSize, sellUser, buyUser, msgType, readStatusSell, readStatusBuy);
    }

    @ApiOperation("添加聊天")
    @PostMapping("")
    public Result chatAdd(@RequestHeader("x-access-token") String accessToken,
                          @RequestBody ChatVO chatVO) throws Exception {
        //log.info("添加聊天接口调用---post--</chat>:  chatVO=" + chatVO);
        return chatService.chatAdd(accessToken, chatVO);
    }

    @ApiOperation("更新聊天")
    @PutMapping("")
    public Result chatPut(@RequestHeader("x-access-token") String accessToken,
                          @RequestBody Chat chat) throws Exception {
        //log.info("修改已读状态接口调用---put--</chat>:  chat=" + chat);
        return chatService.chatPut(accessToken, chat);
    }

    @ApiOperation("查询单个聊天")
    @GetMapping("/{chatId}")
    public Result getGoodsOne(@RequestHeader("x-access-token") String accessToken,
                              @PathVariable("chatId") @ApiParam(value = "聊天id", required = true) Integer chatId) throws Exception {
        log.info("查询单个聊天接口调用--get---</chat/{chatId}>: chatId=" + chatId);
        return chatService.selectByPrimaryKey(accessToken, chatId);
    }

    @ApiOperation("更新聊天状态")
    @GetMapping("/status")
    public Result chatPutUser(@RequestHeader("x-access-token") String accessToken,
                          @ApiParam(value = "消息id") @RequestParam(value = "chatId") Integer chatId,
                          @ApiParam(value = "用户类型 1买家  2卖家") @RequestParam(value = "userType") Integer userType) throws Exception {
        //og.info("更新聊天状态接口调用---get--</chat/status>:  chatId=" + chatId + ",userType=" + userType);
        return chatService.chatPutUser(accessToken, chatId, userType);
    }

    @ApiOperation("通过id删除消息")
    @DeleteMapping("")
    public Result chatDel(@RequestHeader("x-access-token") String accessToken,
                          @ApiParam(value = "消息id") @RequestParam(value = "chatId") Integer chatId,
                          @ApiParam(value = "用户类型 1买家  2卖家") @RequestParam(value = "userType") Integer userType) throws Exception {
        log.info("通过id删除消息接口调用---delete--</chat>:  chatId=" + chatId + ",userType=" + userType);
        return chatService.chatDel(accessToken, chatId, userType);
    }
}
