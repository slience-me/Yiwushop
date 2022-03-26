const baseUrl="https://blog.slienceme.xyz";//根地址

const request=(params)=>{
  //处理接口名修改问题
  if(params.url.substr(0,4)!="/app"){
    // if(params.url.substr(0,9)=="/category"&&!(params.url.substr(params.url.length-4).indexOf("/")>=0)){
    //   params.url = "/category"
    // }
    // if(params.url.substr(0,6)=="/goods"&&!(params.url.substr(params.url.length-4).indexOf("/")>=0)){
    //   params.url = "/goods"
    // }
    // if(params.url.substr(0,7)=="/orders"&&!(params.url.substr(params.url.length-4).indexOf("/")>=0)){
    //   params.url = "/orders"
    // }
    // if(params.url.substr(0,9)=="/auctions"&&!(params.url.substr(params.url.length-4).indexOf("/")>=0)){
    //   params.url = "/auctions"
    // }
    params.url = "/admin" + params.url
  }
  return new Promise((resolve,reject)=>{
    wx.request({
      ...params,
      url:baseUrl+params.url,
      header : {
        ...params.header,
        //token
        "x-access-token":getApp().globalData.token
      },
      success:(result)=>{
        resolve(result);
      },
      fail:(err)=>{
        reject(err);
      },
      complete:(res)=>{
      }
    });
  })
}
const get = (params)=>{
  params.method="GET";
  return request(params);
}
const post = (params)=>{
  params.method="post";
  params.header= {"Content-Type": "application/json"};
  return request(params);
}
const put = (params)=>{
  params.method="put";
  params.header= {"Content-Type": "application/json"};
  return request(params);
}
const DELETE = (params)=>{
  params.method="delete";
  params.url=params.url+jsonToString(params.data);
  return request(params);
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
/**
 * 上传图片
 */
const uploadFile =(url,filePath)=>{
  return new Promise((resolve,reject)=>{
    wx.uploadFile({
      url: baseUrl+url,//上传路径
      filePath: filePath,//本地图片路径
      name: 'multipartFile',
      header: {
        'content-type': 'multipart/form-data',
        "x-access-token":getApp().globalData.token
      },
      formData: null,
      success:(result)=>{
        result.data = JSON.parse(result.data);
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
module.exports = {
  request,
  get,
  post,
  put,
  DELETE,
  uploadFile,
  baseUrl
}