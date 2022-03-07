// pages/mine/info/index.js
import {request,post,put} from "../../../utils/request.js";
const wxapi = require("../../../utils/wxapi");
const utils = require("../../../utils/utils");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{},
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
     userInfo:app.globalData.userInfo
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
    this.data.userInfo[e.target.dataset.name] = e.detail.value
  },
  changeGender(e){
    console.log(e)
    this.data.userInfo["userGender"] = e.currentTarget.dataset.gender
  },
  //提交
  async submit(){
    var that = this;
    var userInfo = that.data.userInfo;
    var user = {};
    user.userId = userInfo.userId;
    if(userInfo.userName!=null){
      user.userName = userInfo.userName;
    }
    if(userInfo.userNumber!=null){
      user.userNumber = userInfo.userNumber;
    }
    if(userInfo.userGender!=null){
      user.userGender = userInfo.userGender;
    }
    if(userInfo.userPhone!=null){
      user.userPhone = userInfo.userPhone;
    }
    if(userInfo.userAddress!=null){
      user.userAddress = userInfo.userAddress;
    }
    if(userInfo.idCard!=null){
      user.idCard = userInfo.idCard;
    }
    if(!utils.isZhengze( /^([1-6][1-9]|50)\d{4}(18|19|20)\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/ ,userInfo.idCard)){
      wxapi.showToast("请输入正确格式的身份证号")
      return false;
    }
    console.log(user)
    let res = await put({url:"/app/member",data:{...user}})
    if(res.data.status == 0){
      wxapi.showToast("提交成功")
    }
  }
})