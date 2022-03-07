import {request,post,DELETE,uploadFile,baseUrl} from "../utils/request.js";
const wxapi = require("../utils/wxapi.js");
const app = getApp();

export const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return `${[year, month, day].map(formatNumber).join('/')} ${[hour, minute, second].map(formatNumber).join(':')}`
}
//2022-2-4 00:00:00 转 2022年2月4日 00:00
export const chineseDate = dateString =>{
  if (dateString) {
    var arr1; 
    if(dateString[10]=="T"){
      arr1 = dateString.split("T"); 
    } else{
      arr1 = dateString.split(" "); 
    }
    var sdate = arr1[0].split('-'); 
    var time = arr1[1].split(':')
    return sdate[0]+"年"+sdate[1]+"月"+sdate[2]+"日 "+time[0]+":"+time[1];
  }
}
export const strToDate = dateString =>{
  if (dateString) {
    if(dateString[10]=="T"){
      var arr1 = dateString.split("T"); 
      var sdate = arr1[0].split('-'); 
      var time = arr1[1].split(':')
      var date = new Date(sdate[0], sdate[1]-1, sdate[2], time[0], time[1], time[2]); 
      return date;
    } else{
      var arr1 = dateString.split(" "); 
      var sdate = arr1[0].split('-'); 
      var time = arr1[1].split(':')
      var date = new Date(sdate[0], sdate[1]-1, sdate[2], time[0], time[1], time[2]); 
      return date;
    }
  }
}
export const numberTime = date => {
  return Date.parse(date);
}
export const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : `0${n}`
}
export const getUuid = ()=>{
  return 'xxxxxxxxxxxxxxxxyxxxxxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    var r = Math.random() * 16 | 0,
      v = c == 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}
export const arabicToChinese = (num)=>{
  const arabicToChineseArray = ["一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五","十六","十七","十八","十九","二十","二十一","二十二","二十三","二十四","二十五","二十六","二十七","二十八","二十九","三十","三十一","三十二","三十三","三十四","三十五","三十六","三十七","三十八","三十九","四十","四十一","四十二","四十三","四十四","四十五","四十六","四十七","四十八","四十九","五十","五十一","五十二","五十三","五十四","五十五","五十六","五十七","五十八","五十九","六十","六十一","六十二","六十三","六十四","六十五","六十六","六十七","六十八","六十九","七十","七十一","七十二","七十三","七十四","七十五","七十六","七十七","七十八","七十九","八十","八十一","八十二","八十三","八十四","八十五","八十六","八十七","八十八","八十九","九十","九十一","九十二","九十三","九十四","九十五","九十六","九十七","九十八","九十九","一百"];
  return arabicToChineseArray[num-1];
}
export const secondToTime = (num)=>{
  var chu = Math.floor(num/60);
  var quyu = num%60;
  if(chu<10){
    chu = "0"+chu
  }else{
    chu = ""+chu
  }
  if(quyu<10){
    quyu = "0"+ quyu
  }else{
    quyu = ""+ quyu
  }
  return chu+":"+quyu
}
export const isZhengze = (match,str)=>{
  if(str==null){
    return false;
  }
  return str.match(match)!=null;
}

//图片上传
export const uploadImage = async ()=>{
  //获取图片
  var res = await wxapi.chooseImage();
  var tempFilePath = res.tempFilePaths[0];
  //把图片上传到服务器
  res = await uploadFile("/upload/img",tempFilePath);
  return res.data.data;
}
//获取membIdentify
export const getMembIdentify = async (membId)=>{
  var memberMsg = app.globalData.memberMsg;
  for(let i =0;i<memberMsg.length;i++){
    var item = memberMsg[i];
    if(item.membId==membId){
      return item.membIdentify;
    }
  }
}
//修改用户信息格式,用户设为隐私的信息模糊处理
export const changeFormat =async (user)=>{
  //判断登录用户是否为会员
  const isMember = app.globalData.userInfo.membIdentify != 0;
  //姓名
  if(user.userUsername==null||user.userUsername=='null'){
    user.userUsername="";
  }
  //电话
  if(user.userPhone==null||user.userPhone=='null'){
    user.userPhone="";
  }else if((user.userPhonePower!=0 || !isMember)&&user.userPhone!=null){
    user.userPhone=user.userPhone.substr(0,3)+'xxxxx'+user.userPhone.substr(7,4);
  }
  //地址
  if(user.userPlace==null||user.userPlace=='null'){
    user.userPlace="";
  }else if((user.userPlacePower!=0 ||!isMember)&&user.userPlace!=null){
    user.userPlace='xx'+user.userPlace.substr(2,1)+'xxxxxxxxx';
  }
  //主营业务
  if(user.userBusiness==null||user.userBusiness=='null'){
    user.userBusiness="";
  }
  //行业
  if(user.profId != null){
    //获取行业名称
    var res = await request({url:"user-profession-second/getProfById",data:{id:user.profId}});
    if(res.data.code==0){
      user.profName=res.data.data.profName;
    }else{
      user.profName="";
    }
  }else{
    user.profName="";
  }
  //个人介绍
  if(user.userSelfIntroduction==null){
    user.userSelfIntroduction="";
  }else if(user.userSelfPower!=0 && user.userSelfIntroduction.length >10){
    user.userSelfIntroduction=user.userSelfIntroduction.substr(0,10)+'xxxxxxxxx';
  }else if(user.userSelfPower!=0){
    user.userSelfIntroduction=user.userSelfIntroduction.substr(0,5)+'xxxxxxxxx';
  }
  //企业会员才有公司
  var membIdentify =await getMembIdentify(user.membId);
  user.membIdentify=membIdentify;
  if(membIdentify==2){
    //公司介绍
    if(user.userCompanyIntroduce==null){
      user.userCompanyIntroduce="";
    }else if(user.userCompanyPower!=0 && user.userCompanyIntroduce.length >10){
      user.userCompanyIntroduce=user.userCompanyIntroduce.substr(0,10)+'xxxxxxxxx';
    }else if(user.userCompanyPower!=0){
      user.userCompanyIntroduce=user.userCompanyIntroduce.substr(0,5)+'xxxxxxxxx';
    }
  }else{
    user.userCompanyIntroduce="";
  }
  return user;
}
export const changeActiveFormat =async (active)=>{
  if(active==null){
    return null;
  }
  active.forEach(item=>{
    item.baseActivity.actiStarttime = item.baseActivity.actiStarttime.substr(0,item.baseActivity.actiStarttime.indexOf("T"));
    item.baseActivity.actiEndtime = item.baseActivity.actiEndtime.substr(0,item.baseActivity.actiEndtime.indexOf("T"));
  })
  return active;
}
//获取用户信息
export const getUserProfile = async ()=>{
  //获取用户头像信息
  var res = await wxapi.getUserProfile("用于完善会员资料");
  console.log(res)
  var userInfo = res.userInfo;
  //添加用户信息
  const info = {openid:app.globalData.userInfo.openid,userAvatarurl:userInfo.avatarUrl,userGender:userInfo.gender+1,userName:userInfo.nickName};
  var res = await post({url:"/app/member",data:info});
  //获取用户id并存到全局变量中
  res = await request({url:"/app/member",data:{pageSize:1,pageNo:1,openid:app.globalData.userInfo.openid}});
  return res.data.data.list[0];
}
//判断提交信息是否正确
export const canSubmit = (submitMsg)=>{
  for(let i = 0; i<submitMsg.length; i++){
    var item = submitMsg[i];
    if(item.value.length==0){
      if(item.typeType==0){
        wxapi.showToast("请填写"+item.typeName)
      }else{
        wxapi.showToast("请选择"+item.typeName)
      }
      return false;
    }
    if(item.typeName=="身份证号"){
      if(!isZhengze( /^([1-6][1-9]|50)\d{4}(18|19|20)\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/ ,item.value)){
        wxapi.showToast("请输入正确格式的身份证号")
        return false;
      }
    }else if(item.typeName=="统一社会信用代码"){
      if(!isZhengze( /^[A-Za-z0-9]+$/ ,item.value)){  
        wxapi.showToast("请输入正确格式的统一社会信用代码")
        return false;
      }
    }
  }
  return true;
}
let times = 0;
//支付接口
export const pay = async (price,name)=>{
  if(times != 0){
    console.log(times)
    return ""
  }
  console.log("sss")
  times++;
  try {
    //获取微信支付相关参数
    //获取uuid(账单号)
    const uuid = getUuid();
    var res = await wxapi.cloud_callFunction({name:"pay",data:{out_trade_no:uuid,body:name,total_fee:price*100,appid:app.globalData.appid,mchid:app.globalData.mchid,partnerKey:app.globalData.partnerKey}});  
    // wxapi.cloud_callFunction({name:"pay",data:{out_trade_no:uuid,body:name,total_fee:price*100,appid:app.globalData.appid,mchid:app.globalData.mchid,partnerKey:app.globalData.partnerKey}}).catch((res)=>{
    //   console.log(res)
    // })
    const {result} = res;
    //进行支付
    var res = await wxapi.requestPayment({timeStamp:result.timeStamp,nonceStr:result.nonceStr,package:result.package,signType:result.signType,paySign:result.paySign});
    return uuid;
  } catch (error) {
    wxapi.showToast("支付失败")
    return ""
  }finally{
    times--;
  }
}
const jsonToString = (json)=>{
  if(typeof(json) != "object"){
    return "";
  }
  var filedNum = 0;
  var str = "?";
  for (const item in json) {
    if (json.hasOwnProperty(item)) {
      filedNum++;
      str+=item+"="+json[item]+"&";
    }
  }
  str = str.substr(0,str.length-1);
  if(filedNum>0){
    return str
  }else{
    return ""
  }
}