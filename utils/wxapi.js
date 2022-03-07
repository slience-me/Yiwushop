/**
 * 用户登录接口
 */
export const login =()=>{
  return new Promise((resolve,reject)=>{
    wx.login({
      timeout:10000,
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * 获取用户信息接口
 */
export const getUserProfile =(desc)=>{
  return new Promise((resolve,reject)=>{
    wx.getUserProfile({
      desc:desc,
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * 获取用户权限信息接口
 */
export const getSetting =()=>{
  return new Promise((resolve,reject)=>{
    wx.getSetting({
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * 让用户授权信息接口
 */
export const authorize =(scope)=>{
  return new Promise((resolve,reject)=>{
    wx.authorize({
      scope:scope,
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * 获取用户位置接口
 */
export const getLocation =()=>{
  return new Promise((resolve,reject)=>{
    wx.getLocation({
      type:'gcj02',
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * 导航
 */
export const openLocation =(params)=>{
  return new Promise((resolve,reject)=>{
    wx.openLocation({
      ...params,
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * showToast
 */
export const showToast = (msg)=>{
  return new Promise((resolve,reject)=>{
    wx.showToast({
      title:msg,
      icon:"none",
      mask: true,
      duration:1000,
    })
  })
}
/**
 * 微信调用云函数接口
 */
export const cloud_callFunction =(params)=>{
  return new Promise((resolve,reject)=>{
    wx.cloud.callFunction({
      ...params,
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * 微信支付api
 */
export const requestPayment =(params)=>{
  return new Promise((resolve,reject)=>{
    wx.requestPayment({
      ...params,
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * 选择图片
 */
export const chooseImage =()=>{
  return new Promise((resolve,reject)=>{
    wx.chooseImage({  //从本地相册选择图片或使用相机拍照
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * 选择地址
 */
export const chooseLocation =()=>{
  return new Promise((resolve,reject)=>{
    wx.chooseLocation({
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * 请求用户授权订阅消息
 */
export const requestSubscribeMessage =(tmplIds)=>{
  return new Promise((resolve,reject)=>{
    wx.requestSubscribeMessage({
      tmplIds : tmplIds,
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * showModal
 */
export const showModal =(content)=>{
  return new Promise((resolve,reject)=>{
    wx.showModal({
      content : content,
      confirmColor: '#07c160',
      success:(result)=>{
        resolve( result);
      },
      fail:(err)=>{
        reject (err);
      }
    })
  })
}
/**
 * pageScrollTo
 */
export const pageScrollTo =(scrollTop,duration)=>{
  return new Promise((resolve,reject)=>{
    wx.pageScrollTo({
      scrollTop: 0,
      duration: 300
    })
  })
}