// pages/auction/index/index.js
import {request,post} from "../../../utils/request.js";
const wxapi = require("../../../utils/wxapi");
const util = require("../../../utils/utils");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    auctions:{}
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
  onShow:async function () {
    var that = this;
    //获取拍卖场次
    let res = await request({url:"/auctions",data:{pageNo:1,pageSize:999}});
    const auctions = res.data.data.list;
    //获取拍卖商品
    res = await request({url:"/goods",data:{pageNo:1,pageSize:999,stateOn:2}})
    const goods = res.data.data.list;
    //拍卖场次与商品对应,并添加拍卖状态
    const newAuctions = auctions.map((auction)=>{
      const good = goods.find((good)=>{
        return auction.goodsId == good.goodsId
      })
      auction.good = good;
      //添加拍卖状态
      const now = new Date();
      const startTime = util.strToDate(auction.startTime);
      auction.state = now>startTime?"正在拍卖":"即将开拍"
      return auction;
    })
    that.setData({
      auctions:newAuctions
    })
  },
  //跳转到某个页面
  skipTo(e){
    const index = e.currentTarget.dataset.index;
    const auction = this.data.auctions[index];
    wx.navigateTo({
      url: "/pages/auction/bidding/index?auctionsId="+auction.auctionsId
    })
  },
})