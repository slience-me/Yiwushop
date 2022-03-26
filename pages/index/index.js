// index.js
import{request} from "../../utils/request.js";
const util = require("../../utils/utils");
const wxapi = require("../../utils/wxapi");
const app = getApp();
Page({
  data: {
    //查找
    search:"",
    //搜索到的商品
    goods:[],
    //左侧大菜单数据
    leftMenuList:[],
    //右侧大菜单数据
    rightContent:[],
    //当前被选项
    currentIndex:0,
    //右侧滚动框刷新后距离顶部位置
    rightScrollTop:0,
    //是否为新用户
    isFirstUser:false,
    //是否展示搜索信息
    displaySearchMsg:false
  },
  onShow: function () {
    let that = this;
    if(app.globalData.num != -1){
      that.getInfo();
    }else{
      app.callBack = that.getInfo;
    }
  },
  async getInfo(){
    let that = this;
    that.setData({
      isFirstUser: app.globalData.num == 1
    })
    this.getCates();
  },
  async getCates(){
    var that = this;
    let res = await request({url:"/category",data:{pageNo:1,pageSize:999}});
    console.log(res)
    let left = res.data.data.list;
    console.log(left)
    res = await request({url:"/goods",data:{pageNo:1,pageSize:999,stateOn:5}});
    let right = res.data.data.list;
    console.log(right)
    //构造右侧大菜单栏数据
    let rightContent = right.filter((value,index)=>{
      return value.categoryId == left[0].categoryId;
    })
    that.setData({
      leftMenuList:left,
      right:right,
      rightContent
    })
  },
  handleItemTap(e){
    var that = this;
    const {operation}=e.currentTarget.dataset;
    let rightContent =that.data.right.filter((value,index)=>{
      return value.categoryId == that.data.leftMenuList[operation].categoryId;
    });
    that.setData({
      currentIndex : operation,
      rightContent,
      rightScrollTop:0
    })
  },
  //获取用户信息
  async getUserProfile(e) {
    var that = this;
    console.log(e)
    that.setData({
      isFirstUser: false
    })
    var userInfo = await util.getUserProfile();
    //存用户信息
    that.setData({
      user:userInfo,
    })
    app.globalData.num = 0;
    app.globalData.userInfo = userInfo;
    //更新数据
    this.getCates();
  },
  //更新查找信息
  changeKeyword(e){
    this.setData({
      search:e.detail.value
    })
  },
  //点击搜索
  async searchGoods(){
    var that = this;
    if(that.data.search == null){
      wxapi.showToast("请输入要搜索的商品");
      return;
    }
    let res = await request({url:"/goods",data:{pageNo:1,pageSize:999,stateOn:5}});
    let list = res.data.data.list.filter(item=>{
      return item.goodsName.indexOf(that.data.search)>=0 || item.goodsInfo.indexOf(that.data.search)>=0
    })
    if(list.length==0){
      wxapi.showToast("没有相关商品");
      return;
    }else{
      that.setData({
        goods:list,
        displaySearchMsg:true
      })
    }
  },
  //取消搜索
  cancel(){
    this.setData({
      goods:[],
      displaySearchMsg: false
    })
  },
  //跳转到某个页面
  skipTo(e){
    console.log(e)
    wx.navigateTo({
      url: e.currentTarget.dataset.url
    })
  },
})