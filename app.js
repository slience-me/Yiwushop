// app.js
import {request} from "./utils/request.js";
const wxapi = require("./utils/wxapi.js");

App({
  callBack:null,
  async onLaunch() {
    var that = this;
    //初始化数据num==0正常，1未登陆过，2小程序维护
    await this.init().then((num)=>{
      that.globalData.num = num;
    });
    //如果onload先执行，那么执行回调
    if(that.callBack){
      that.callBack();
    }
    //输出一下
    console.log(that.globalData);
  },
  //获取用户等信息
  async init(){
    var app = this;
    var res,openid;
    //登录获取code
    res = await wxapi.login();
    //通过code获取openid
    try {
      res = await request({url:"/app/getCode",data:{code:res.code}});
      console.log(res)
      app.globalData.userInfo.openid=res.data.data.openid;
      app.globalData.token=res.data.data["x-access-token"];
      openid = res.data.data.openid;
    }catch (err){
      wxapi.showToast("小程序正在维护中")
      return 2;
    }
    console.log(app.globalData.token)
    //通过openid获取用户信息
    res = await request({url:"/app/member",data:{pageNo:1,pageSize:1,openid:openid}});
    console.log(res)
    if(res.data.data.total != 1){
      // 没有该用户信息,当他点击进入的时候给它添加
      return 1;
    }else{
      // 有该用户信息,把信息存起来
      app.globalData.userInfo=res.data.data.list[0];
      return 0;
    }
  },
  globalData: {
    //colorUI
    ColorList: [{
      title: '嫣红',
      name: 'red',
      color: '#e54d42'
    },
    {
      title: '桔橙',
      name: 'orange',
      color: '#f37b1d'
    },
    {
      title: '明黄',
      name: 'yellow',
      color: '#fbbd08'
    },
    {
      title: '橄榄',
      name: 'olive',
      color: '#8dc63f'
    },
    {
      title: '森绿',
      name: 'green',
      color: '#39b54a'
    },
    {
      title: '天青',
      name: 'cyan',
      color: '#1cbbb4'
    },
    {
      title: '海蓝',
      name: 'blue',
      color: '#0081ff'
    },
    {
      title: '姹紫',
      name: 'purple',
      color: '#6739b6'
    },
    {
      title: '木槿',
      name: 'mauve',
      color: '#9c26b0'
    },
    {
      title: '桃粉',
      name: 'pink',
      color: '#e03997'
    },
    {
      title: '棕褐',
      name: 'brown',
      color: '#a5673f'
    },
    {
      title: '玄灰',
      name: 'grey',
      color: '#8799a3'
    },
    {
      title: '草灰',
      name: 'gray',
      color: '#aaaaaa'
    },
    {
      title: '墨黑',
      name: 'black',
      color: '#333333'
    },
    {
      title: '雅白',
      name: 'white',
      color: '#ffffff'
    },
    ],
    num:-1,//程序状态
    token:"",//秘钥验证
    userInfo: {},//登录用户信息

    AppSecret: "7a7d9957f8dc04534ed71664aa56bb2c",//小程序id
    AppID: "wxa6095c87cb44915a",//小程序秘钥
  }
})