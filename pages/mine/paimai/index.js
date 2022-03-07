import {request,post,put} from "../../../utils/request.js";
const wxapi = require("../../../utils/wxapi");
const util = require("../../../utils/utils");
const app = getApp();
// pages/mine/paimai/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //开始结束日期
    startDate:"2022-02-01",
    startTime:"00:00", 
    endDate:"2022-02-01", 
    endTime:"00:00",
    goodsId:"",
    //拍卖信息
    auctions:{
      auctionsName:null,
      startingPrice:null,
      fixedPrice:null,
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
      goodsId:options.goodsId
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
  //更新信息
  changeMsg(e){
    this.data.auctions[e.target.dataset.name] = e.detail.value
  },
  //开始日期
  StartDateChange(e) {
    this.setData({
      startDate:e.detail.value
    })
  },
  //开始时间
  StartTimeChange(e) {
    this.setData({
      startTime:e.detail.value
    })
  },
  //结束日期
  EndDateChange(e) {
    this.setData({
      endDate:e.detail.value
    })
  },
  //结束时间
  EndTimeChange(e) {
    this.setData({
      endTime:e.detail.value
    })
  },
  async paimai(){
    var that = this;
    const startTime = that.data.startDate+" "+that.data.startTime+":00";
    const endTime = that.data.endDate+" "+that.data.endTime+":00";
    const auctions = that.data.auctions;
    const goodsId = that.data.goodsId;
    let res = await request({url:"/auctions/data",data:{goodsId:goodsId,pageNo:1,pageSize:1}});
    if(res.data.data.total > 0){
      //已经有了
      wx.showToast({
        title:"该商品正在拍卖",
        icon:"none",
        mask: true,
        duration:1500,
      })
      return
    }
    if(auctions.auctionsName==null||auctions.fixedPrice==null||auctions.startingPrice==null){
      wx.showToast({
        title:"商品未成功上架拍卖，请检查商品信息是否正确",
        icon:"none",
        mask: true,
        duration:3000,
      })
      return;
    }
    //商品状态改为拍卖
    res = await put({url:"/goods/goodsList",data:{goodsId:goodsId,stateOn:2}});
    //添加拍卖场次
    res = await post({url:"/auctions/auctionsList",data:{...auctions,startTime:startTime,endTime:endTime,goodsId:goodsId}});
    if(res.data.status == 0){
      wx.showToast({
        title:"商品已成功上架拍卖",
        icon:"none",
        mask: true,
        duration:2000,
      })
    }
  }
})