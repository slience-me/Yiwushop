// pages/mine/help/index.js
const wxapi = require("../../../utils/wxapi.js");
const app =getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //客服电话
    phone:[
      "17391876334",
      "19827060613",
      "19522555850"
    ]
  },
  call(e){
    //拨打电话
    console.log(e)
    wx.makePhoneCall({
      phoneNumber: e.currentTarget.dataset.phone
    })
  }
})