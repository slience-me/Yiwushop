// pages/auction/bidding/index.js
import {request,post,put} from "../../../utils/request.js";
const wxapi = require("../../../utils/wxapi");
const util = require("../../../utils/utils");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods:{},
    auctions:{},
    state:"",
    auctionsId:0,
    quote:null,
    //定时器
    timer:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad:async function (options) {
    this.setData({
      auctionsId:options.auctionsId
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
  onShow: async function () {
    var that = this;
    //获取拍卖场次
    let res = await request({url:"/auctions/"+that.data.auctionsId});
    const auctions = res.data.data;
    //获取拍卖商品
    res = await request({url:"/goods",data:{pageNo:1,pageSize:999,stateOn:2}})
    const goods = res.data.data.list.find((item)=>{
      return item.goodsId == auctions.goodsId
    });
    //添加拍卖状态
    const now = new Date();
    const startTime = util.strToDate(auctions.start);
    const state = now>startTime?"正在拍卖":"即将开拍"
    //修改日期格式
    let date = "";  
    if(now<startTime){
      date = auctions.start.substr(8,2)+"日"+auctions.start.substr(11,5)+"开拍";
    }else{
      date = auctions.end.substr(8,2)+"日"+auctions.end.substr(11,5)+"结束";
    }
    //添加刷新任务
    var timer = setInterval(this.updateData,1000)
    that.setData({
      goods,auctions,state,date,timer
    }) 

  },
  //更新数据
  async updateData(){
    var that = this;
    //获取拍卖场次
    let res = await request({url:"/auctions/"+that.data.auctionsId});
    const auctions = res.data.data;
    that.setData({
      auctions
    })
    if(auctions.isDelete == 1){
      wxapi.showToast("该商品已被其他用户拍卖")
      //去除定时器
      clearInterval(that.data.timer)
      return
    }
  },
  //更新报价
  changeQuote(e){
    this.setData({
      quote: e.detail.value
    })
  },
  //报价
  async quote(){
    var that = this;
    const quote = that.data.quote;
    const auctions = that.data.auctions;
    const goods = that.data.goods;
    const startingPrice = auctions.startingPrice?auctions.startingPrice:0;
    const presentPrice = auctions.presentPrice?auctions.presentPrice:0;
    if(new Date()<util.strToDate(auctions.start)){
      wxapi.showToast("当前拍卖未开始")
      return
    }
    if(auctions.isDelete == 1){
      wxapi.showToast("当前拍卖已结束")
      return
    }
    if(quote==null){
      wxapi.showToast("报价不能为空")
      return
    }else if(!util.isZhengze(/^[+-]?(0|([1-9]\d*))(\.\d+)?$/,quote)){
      wxapi.showToast("报价必须为数字")
      return
    }else if(quote<=startingPrice){
      wxapi.showToast("报价低于起拍价")
      return
    }
    // else if(quote>=fixedPrice){
    //   //一口价成交
    //   //修改拍卖信息并删除该拍卖场
    //   let res = await put({url:"/auctions/auctionsList",data:{auctionsId:auctions.auctionsId,presentPrice:quote,presentPerson:app.globalData.userInfo.userId,isDelete:1}});
    //   //添加拍卖报价信息
    //   res = await post({url:"/auction/auctionScheduleList",data:{auctionsId:auctions.auctionsId,userId:app.globalData.userInfo.userId,auctionSchedulePrice:quote}});
    //   //添加订单信息
    //   res = await post({url:"/orders/ordersList",data:{buyPrice:quote,buyUsersId:app.globalData.userInfo.userId,goodsId:goods.goodsId,sellUsersId:goods.userId}});
    //   //修改商品信息
    //   res = await put({url:"/goods/goodsList",data:{goodsId:goods.goodsId,stateOn:3}});
    //   if(res.data.status == 0){
    //     //修改当前最高报价
    //     auctions.presentPrice = quote;
    //     auctions.isDelete = 1;
    //     that.setData({
    //       auctions
    //     })
    //     //拍卖成功
    //     wx.showToast({
    //       title:"恭喜你拍下该商品，请查询订单联系并商家",
    //       icon:"none",
    //       mask: true,
    //       duration:2500,
    //     })
    //     //去除定时器
    //     clearInterval(that.data.timer)
    //   }
    // }
    else if(quote<=presentPrice){
      wxapi.showToast("报价不高于当前最高报价")
      return
    }else{
      //修改拍卖场次信息
      let res = await put({url:"/auctions",data:{auctionsId:auctions.auctionsId,presentPrice:quote,presentPerson:app.globalData.userInfo.userId}});
      //添加信息
      res = await post({url:"/process",data:{auctionsId:auctions.auctionsId,userId:app.globalData.userInfo.userId,auctionSchedulePrice:quote}});
      if(res.data.status == 0){
        //修改当前最高报价
        auctions.presentPrice = quote;
        that.setData({
          auctions
        })
        wxapi.showToast("报价成功")
      }
    }
  },
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    clearInterval(this.data.timer);
  }, 
  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    clearInterval(this.data.timer);
  },
})