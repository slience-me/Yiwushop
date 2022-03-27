// pages/mine/order/index.js
import {request,post,put} from "../../../utils/request.js";
const wxapi = require("../../../utils/wxapi");
const util = require("../../../utils/utils");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    buyOrder:{},
    sellOrder:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad:async function (options) {
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: async function () {
    var that = this;
    const userId = app.globalData.userInfo.userId;
    //获取商品信息
    let res = await request({url:"/goods",data:{pageNo:1,pageSize:999,stateOn:3}})
    const goods = res.data.data.list;
    //获取用户信息
    res = await request({url:"/app/member",data:{pageNo:1,pageSize:999}})
    const users = res.data.data.list;
    //获取拍卖场次
    res = await request({url:"/orders",data:{pageNo:1,pageSize:999}});
    const orders = res.data.data.list;
    let buyOrder = orders.filter((item)=>{
      return item.buyUsersId == userId;
    })
    buyOrder = buyOrder.map((order)=>{
      const good = goods.find((good)=>{
        return order.goodsId == good.goodsId
      })
      const sellUser = users.find((user)=>{
        return order.sellUsersId == user.userId
      })
      order.good = good;
      order.sellUser = sellUser;
      return order;
    })
    let sellOrder = orders.filter((item)=>{
      return item.sellUsersId == userId;
    })
    sellOrder = sellOrder.map((order)=>{
      const good = goods.find((good)=>{
        return order.goodsId == good.goodsId
      })
      const buyUser = users.find((user)=>{
        return order.buyUsersId == user.userId
      })
      order.good = good;
      order.buyUser = buyUser;
      return order;
    })
    that.setData({
      buyOrder,sellOrder
    })
  },
  //跳转到某个页面
  skipTo(e){
    wx.navigateTo({
      url: e.currentTarget.dataset.url
    })
  },
  call(e){
    //拨打电话
    if(e.currentTarget.dataset.phone==null||e.currentTarget.dataset.phone==""){
      wxapi.showToast("对方未设置电话号码")
      return
    }
    wx.makePhoneCall({
      phoneNumber: e.currentTarget.dataset.phone
    })
  },
  //与卖家对话
  chatToSell(e){
    const index = e.currentTarget.dataset.index;
    const oppositeId = this.data.sellOrder[index].sellUsersId;
    if(oppositeId == app.globalData.userInfo.userId){
      wxapi.showToast("不能与自己联系")
      return
    }
    wx.navigateTo({
      url: "/pages/chat/index?oppositeId="+oppositeId
    })
  },
  //与买对话
  chatToBuy(e){
    const index = e.currentTarget.dataset.index;
    const oppositeId = this.data.buyOrder[index].buyUsersId;
    if(oppositeId == app.globalData.userInfo.userId){
      wxapi.showToast("不能与自己联系")
      return
    }
    wx.navigateTo({
      url: "/pages/chat/index?oppositeId="+oppositeId
    })
  },
})