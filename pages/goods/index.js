// pages/goods/index.js
import{request} from "../../utils/request.js";
const util = require("../../utils/utils");
const wxapi = require("../../utils/wxapi");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //商品
    goods:{},
    sellUser:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
   onLoad: async function (options) {
    let res = await request({url:"/goods/data",data:{pageNo:1,pageSize:999,stateOn:5}});
    const goods = res.data.data.list.find((item)=>{
      return item.goodsId == options.goodsId;
    })
    res = await request({url:"/app/member/"+goods.userId})
    const sellUser = res.data.data;
    this.setData({
      goods,sellUser
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },
  call(){
    var that = this;
    wx.makePhoneCall({
      phoneNumber: that.data.sellUser.userPhone
    })
  },
  //跳转到对话页面
  skipTo(e){
    const oppositeId = this.data.sellUser.userId;
    if(oppositeId == app.globalData.userInfo.userId){
      wxapi.showToast("不能与自己联系")
      return
    }
    wx.navigateTo({
      url: "/pages/chat/index?oppositeId="+oppositeId
    })
  },
})