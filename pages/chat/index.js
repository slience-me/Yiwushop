import {request,post,put} from "../../utils/request.js";
const wxapi = require("../../utils/wxapi");
const util = require("../../utils/utils");
const app = getApp();
// pages/chat/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    oppositeId:"",
    me:{},
    opposite:{},
    words:{},
    timerToUnder:null,
    timerUpdate:null,
    msg:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      oppositeId: options.oppositeId
    })
  },


  /**
   * 生命周期函数--监听页面显示
   */
  onShow:async function () {
    var that = this;
    const oppositeId = that.data.oppositeId;
    const userId = app.globalData.userInfo.userId;
    //获取对方用户消息
    let res = await request({url:"/app/member/"+oppositeId});
    const opposite = res.data.data;
    //获取消息信息
    res = await request({url:"/chat",data:{pageNo:1,pageSize:999,sellUser:oppositeId,buyUser:userId}});
    let words1 = res.data.data.list;
    res = await request({url:"/chat",data:{pageNo:1,pageSize:999,sellUser:userId,buyUser:oppositeId}});
    let words2 = res.data.data.list; 
    //拼接数组
    let words = words1.concat(words2);
    //排序
    words = words.sort((a,b)=>{
      return new Date(a.createdTime).getTime() - new Date(b.createdTime).getTime();
    })
    //修改时间
    const now = new Date();
    words.forEach((item)=>{
      const date = new Date(item.createdTime);
      if(now.getDay() == date.getDay()){
        item.time = item.createdTime.substr(11,5);
      }else{
        item.time = util.chineseDate(item.createdTime)
      }
    })
    //添加刷新任务,页面跳转到底端
    var timerToUnder = setInterval(that.toUnder,200)
    var timerUpdate = setInterval(that.updateData,800)
    //添加数据
    that.setData({
      me:app.globalData.userInfo,
      opposite,words,timerToUnder,timerUpdate
    })
  },
  //去底端
  toUnder(){
    wx.pageScrollTo({
      scrollTop: 10000,
      duration: 200
    })
    clearInterval(this.data.timerToUnder);
  },
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    clearInterval(this.data.timerUpdate);
  },
  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    clearInterval(this.data.timerUpdate);
  },
  //更新消息
  msgChange(e){
    this.setData({
      msg:e.detail.value
    })
  },
  //发送消息
  async send(){
    var that = this;
    const msg = that.data.msg;
    if(msg == null){
      wxapi.showToast("请输入您要发送的内容")
      return
    }
    let res = await post({url:"/chat",data:{sellUser: that.data.me.userId,msgContent: msg,msgType: 0,buyUser: that.data.opposite.userId}})
    if(res.data.status == 0){
      that.setData({
        msg:null
      })
      //更新数据
    }else{
      wxapi.showToast("发送失败")
    }
  },
  //更新数据
  async updateData(){
    var that = this;
    const oppositeId = that.data.oppositeId;
    const userId = app.globalData.userInfo.userId;
    //获取消息信息
    let res = await request({url:"/chat",data:{pageNo:1,pageSize:999,sellUser:oppositeId,buyUser:userId}});
    let words1 = res.data.data.list;
    res = await request({url:"/chat",data:{pageNo:1,pageSize:999,sellUser:userId,buyUser:oppositeId}});
    let words2 = res.data.data.list; 
    //拼接数组
    let words = words1.concat(words2);
    //排序
    words = words.sort((a,b)=>{
      return new Date(a.createdTime).getTime() - new Date(b.createdTime).getTime();
    })
    //修改时间格式,并消除未读
    const now = new Date();
    for (let index = 0; index < words.length; index++) {
      const item = words[index];
      const date = new Date(item.createdTime);
      //修改时间格式
      if(now.getDay() == date.getDay()){
        item.time = item.createdTime.substr(11,5);
      }else{
        item.time = util.chineseDate(item.createdTime)
      }
      //消除未读
      if(item.sellUser != userId && item.readStatusSell == 1){
        let res = await put({url:"/chat",data:{chatId:item.chatId,readStatusSell:0}})
        // console.log(item.chatId)
      }
    }
    //如果数据更新
    if(that.data.words.length < words.length){
      //添加刷新任务,页面跳转到底端
      var timerToUnder = setInterval(that.toUnder,1000)
      that.setData({
        timerToUnder
      })
    }
    //添加数据
    that.setData({
      words
    })
  },
  //发送图片
  async sendImage(){
    var that = this;
    let res = await util.uploadImage();
    const url = res.url;
    if(url == null){
      wxapi.showToast("发送失败")
      return
    }
    res = await post({url:"/chat",data:{sellUser: that.data.me.userId,msgContent: url,msgType: 1,buyUser: that.data.opposite.userId}})
    if(!res.data.status == 0){
      wxapi.showToast("发送失败")
    }
  }
})