// pages/mine/complaint/index.js
import {request,post,put} from "../../../utils/request.js";
const wxapi = require("../../../utils/wxapi");
const util = require("../../../utils/utils");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    picker: ['质量问题', '服务态度差', '商品与实物不符','其他'],
    remark:null,
    ordersId:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      ordersId:options.ordersId
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
  PickerChange(e) {
    console.log(e);
    this.setData({
      index: e.detail.value
    })
  },
  textareaBInput(e) {
    this.setData({
      remark: e.detail.value
    })
  },
  //投诉
  async complaint(){
    var that = this;
    const remark = that.data.remark;
    if(remark==null){
      wxapi.showToast("请填写您遇到的问题")
      return
    }
    //提交
    let res = await post({url:"/complaint/complaintList",data:{ordersId:that.data.ordersId,remark:remark}})
    console.log(res)
    if(res.data.status == 0){
      wxapi.showToast("提交成功")
    }
  }
})