const baseUrl="https://ihchina.slienceme.xyz";//根地址

const request=(params)=>{
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