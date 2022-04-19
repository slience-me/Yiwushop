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
    startingPrice:null,
    goods:{},
    auctionsName:"上午场"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad:async function (options) {
    var that = this;
    that.setData({
      goodsId:options.goodsId
    })
    let res = await request({url:"/goods",data:{pageNo:1,pageSize:999}});
    const goods = res.data.data.list.find((item)=>{
      return item.goodsId == options.goodsId;
    })
    this.setData({
      goods: goods,
      startingPrice: goods.goodsPrice*0.8
    })
    //次日
    const ciri = new Date();
    ciri.setDate(ciri.getDate()+1);
    //次次日零点
    const ciciri = new Date();
    ciciri.setDate(ciciri.getDate()+2);
    //设置时间
    this.setData({
      startDate:`${ciri.getFullYear()}-${ciri.getMonth()+1<10?"0"+(ciri.getMonth()+1):ciri.getMonth()+1}-${ciri.getDate()<10?"0"+ciri.getDate():ciri.getDate()}`,
      endDate:`${ciciri.getFullYear()}-${ciciri.getMonth()+1<10?"0"+(ciciri.getMonth()+1):ciciri.getMonth()+1}-${ciciri.getDate()<10?"0"+ciciri.getDate():ciciri.getDate()}`,
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
  onShow:function () {
  },
  //更新信息
  changeMsg(e){
    this.data[e.target.dataset.name] = e.detail.value
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
  //提交拍卖信息
  async paimai(){
    var that = this;
    const startTime = that.data.startDate+" "+that.data.startTime+":00";
    const endTime = that.data.endDate+" "+that.data.endTime+":00";
    const goodsId = that.data.goodsId;
    const startingPrice = that.data.startingPrice;
    const auctionsName = that.data.auctionsName;
    let res = await request({url:"/auctions",data:{goodsId:goodsId,pageNo:1,pageSize:1}});
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
    if(new Date(endTime)<new Date()){
      wx.showToast({
        title:"拍卖结束时间应大于当前时间",
        icon:"none",
        mask: true,
        duration:2000,
      })
      return;
    }
    if((new Date(endTime).getTime()-new Date(startTime).getTime())<60*60*1000 || (new Date(endTime).getTime()-new Date(startTime).getTime())>24*60*60*1000){
      wx.showToast({
        title:"拍卖时间应该在1-24小时之间",
        icon:"none",
        mask: true,
        duration:2000,
      })
      return;
    }
    if(startingPrice==null){
      wx.showToast({
        title:"商品未成功上架拍卖，请检查商品信息是否正确",
        icon:"none",
        mask: true,
        duration:3000,
      })
      return;
    }else if(startingPrice>that.data.goods.goodsPrice*0.8){
      wx.showToast({
        title:"起拍价应不大于商品价格的80%",
        icon:"none",
        mask: true,
        duration:2000,
      })
      return;
    }
    //商品状态改为拍卖
    res = await put({url:"/goods",data:{goodsId:goodsId,stateOn:2}});
    //添加拍卖场次
    res = await post({url:"/auctions",data:{auctionsName:auctionsName,startingPrice:startingPrice,startTime:startTime,endTime:endTime,goodsId:goodsId}});
    if(res.data.status == 0){
      wx.showToast({
        title:"商品已成功上架拍卖",
        icon:"none",
        mask: true,
        duration:2000,
      })
    }
  },
  //修改场次
  changeChangCi(e){
    console.log(e)
    this.setData({
      auctionsName:e.currentTarget.dataset.chang
    })
  },
})