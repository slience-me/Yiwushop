// pages/mine/help/index.js
const wxapi = require("../../../utils/wxapi.js");
const app =getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    phone:[]//客服电话
  },
  call(e){
    //拨打电话
    wx.makePhoneCall({
      phoneNumber: "15932270918" 
    })
  }
})