// pages/message/index.js
import {request,post} from "../../utils/request.js";
const wxapi = require("../../utils/wxapi");
const util = require("../../utils/utils");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    words:[],
    dialogues:[],
    //回话对象格式
    // dialogue = {
    //   userId,user,msgNum,lateWord,lateTime
    // }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow:async function () {
    var that = this;
    const userId = app.globalData.userInfo.userId;
    //获取消息信息
    let res = await request({url:"/chat",data:{pageNo:1,pageSize:999,buyUser:userId}});
    let words1 = res.data.data.list;
    res = await request({url:"/chat",data:{pageNo:1,pageSize:999,sellUser:userId}});
    let words2 = res.data.data.list; 
    //拼接数组
    let words = words1.concat(words2);
    let dialogues = [];
    //获取会话的信息
    for (let i = 0; i < words.length; i++) {
      const word = words[i];
      const oppositeId = word.sellUser==userId?word.buyUser:word.sellUser;
      //查找是否有对该用户对话
      let dialogue = dialogues.find((dialogue)=>{
        return dialogue.userId == oppositeId
      })
      if(!dialogue){
        //没有，添加
        //获取用户信息
        let res = await request({url:"/app/member/"+oppositeId});
        const user = res.data.data;
        //未读信息数量
        const msgNum = word.buyUser==userId?word.readStatusSell:0;
        //修改时间格式
        let lateTime = "";
        if(new Date().getDay() == new Date(word.createdTime).getDay()){
          lateTime = word.createdTime.substr(11,5);
        }else{
          lateTime = word.createdTime.substr(5)
        }
        dialogue = {userId:oppositeId,user:user,msgNum:msgNum,lateWord:word,lateTime:lateTime};
        dialogues.push(dialogue);
      }else{
        //有,修改
        //未读数更改
        dialogue.msgNum += word.buyUser==userId?word.readStatusSell:0;
        //lateWord
        //新的word更晚,替换word
        if(new Date(word.createdTime)> new Date(dialogue.lateWord.createdTime)){
          dialogue.lateWord = word;
          //修改时间格式
          let lateTime = "";
          if(new Date().getDay() == new Date(word.createdTime).getDay()){
            lateTime = word.createdTime.substr(11,5);
          }else{
            lateTime = word.createdTime.substr(5)
          }
          dialogue.lateTime = lateTime;
        }
      }
    }
    //对会话信息排序
    dialogues = dialogues.sort((dialogueA,dialogueB)=>{
      return new Date(dialogueB.lateWord.createdTime).getTime() - new Date(dialogueA.lateWord.createdTime).getTime();
    })
    that.setData({
      words,dialogues
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  //跳转到某个页面
  skipTo(e){
    wx.navigateTo({
      url: e.currentTarget.dataset.url
    })
  },
})