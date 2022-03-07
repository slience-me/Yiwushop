// pages/mine/index/index.js
const app = getApp();
Page({
  data: {
    userInfo:{},
  },
    /**
    * 生命周期函数--监听页面加载
    */
 onLoad:async function (options) {
   var that = this;
   that.setData({
    userInfo:app.globalData.userInfo
   })
 },
  //跳转到某个页面
  skipTo(e){
    wx.navigateTo({
      url: e.currentTarget.dataset.url
    })
  },
})