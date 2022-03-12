// pages/mine/goods/index.js
import {request,post,put} from "../../../utils/request.js";
const wxapi = require("../../../utils/wxapi");
const utils = require("../../../utils/utils");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad:async function (options) {
    var that = this;
    let res = await request({url:"/goods/data",data:{userId:app.globalData.userInfo.userId,pageNo:1,pageSize:999}});
    that.setData({
      goods:res.data.data.list
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
  
  //跳转到某个页面
  skipTo(e){
    console.log(e)

  },
  //修改商品状态
  async changeStateOn(e){
    var that = this;
    const index = e.currentTarget.dataset.index;
    const state = e.currentTarget.dataset.state;
    const goods = that.data.goods[index];
    const goodss = that.data.goods;
    goodss[index].stateOn = state;
    that.setData({
      goods:goodss
    })
    let res = await put({url:"/goods/goodsList",data:{goodsId:goods.goodsId,stateOn:state}});
    console.log(res)
  },
  //删除商品
  async deleteGoods(e){
    var that = this;
    const index = e.currentTarget.dataset.index;
    const goods = that.data.goods[index];
    const goodss = that.data.goods;
    let res = await wxapi.showModal("确定要删除该商品吗？");
    if(res.confirm){
      goodss.splice(index,1);
      that.setData({
        goods:goodss
      })
      res = await put({url:"/goods/goodsList",data:{goodsId:goods.goodsId,isDelete:1}});
      wxapi.showToast("商品已删除");
    }
    console.log(res)
  },
  //拍卖
  async paimai(e){
    var that = this;
    const index = e.currentTarget.dataset.index;
    const goods = that.data.goods[index];
    //正在拍卖
    if(goods.stateOn == 2){
      return;
    }
    let res = await request({url:"/auctions/data",data:{pageNo:1,pageSize:999,goodsId:goods.goodsId}});
    //曾经拍卖过
    if(res.data.data.total == 1){
      res = await put({url:"/goods/goodsList",data:{goodsId:goods.goodsId,stateOn:2}});
      that.data.goods[index].stateOn = 2;
      that.setData({
        goods:that.data.goods
      })
    }else{
      //否则跳转页面
      wx.navigateTo({
        url: "/pages/mine/paimai/index?goodsId="+goods.goodsId
      })
    }
  }
})