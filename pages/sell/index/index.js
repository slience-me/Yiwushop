// pages/sell/index/index.js
import {request,post} from "../../../utils/request.js";
import {isGetOutOfLine} from "../../../utils/getOutOfLine.js";
const wxapi = require("../../../utils/wxapi");
const util = require("../../../utils/utils");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goodsMsg:{
      goodsName:null,
      goodsPrice:null,
      categoryId:null,
      goodsImgId:null,
      goodsInfo:null
    },
    imgUrl:"",
    picker: [],
    imgList: [],
    index:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad:async function (options) {
    var that = this;
    let res = await request({url:"/category",data:{pageNo:1,pageSize:999}})
    const picker = res.data.data.list.map((value)=>{
      return value.categoryName
    })
    that.setData({
      picker,
      category:res.data.data.list
    })
    that.data.goodsMsg.categoryId = this.data.category[0].categoryId;
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },
  PickerChange(e) {
    console.log(e);
    this.data.goodsMsg.categoryId = this.data.category[e.detail.value].categoryId;
    this.setData({
      index:e.detail.value
    })
  },
  async ChooseImage() {
    let res = await util.uploadImage();
    console.log(res)
    this.data.goodsMsg.goodsImgId = res.imageId;
    this.setData({
      imgUrl : res.url
    })
  },
  //更新信息
  changeMsg(e){
    this.data.goodsMsg[e.target.dataset.name] = e.detail.value
  },
  //添加商品并上架出售
  async addAndUp(){
    this.up(5);
  },
  //添加商品并上架典当
  async addAndDiandang(){
    this.up(4);
  },
  //添加商品
  async up(stateOn){
    var that = this;
    //添加商品
    const goods = that.data.goodsMsg;
    console.log(goods)
    const userInfo = app.globalData.userInfo;
    if(userInfo.userPhone == null ||userInfo.idCard == null ){
      wxapi.showToast("请完善个人身份证、电话等信息");
      return;
    }
    if(goods.goodsName && isGetOutOfLine(goods.goodsName)){
      wx.showToast({
        title:"该商品涉及敏感词汇，请重新输入",
        icon:"none",
        mask: true,
        duration:2000,
      })
      return
    }
    if(goods.goodsName!=null&&goods.goodsPrice!=null&&goods.categoryId!=null&&goods.goodsImgId!=null&&goods.goodsInfo!=null){
      let res = await post({url:"/goods",data:{goodsName:goods.goodsName,goodsPrice:goods.goodsPrice,categoryId:goods.categoryId,imageIds:[goods.goodsImgId],goodsInfo:goods.goodsInfo,stateOn:stateOn,userId:app.globalData.userInfo.userId}})
      if(res.data.status==0){
        console.log(res)
        wx.showToast({
          title:"商品已成功上架，如需修改商品状态，请在《我的》里修改",
          icon:"none",
          mask: true,
          duration:3000,
        })
        //清除商品
        const categoryId = that.data.goodsMsg.categoryId;
        that.setData({
          goodsMsg:{
            goodsName:null,
            goodsPrice:null,
            goodsImgId:null,
            goodsInfo:null,
            categoryId:categoryId,
          },
          imgUrl:""
        })
      }
    }else{
      wxapi.showToast("请完善商品信息");
      return;
    }
  }
})