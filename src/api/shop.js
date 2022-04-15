import request from '@/utils/request'

const api = {
  upload: '/admin/upload',
  roleUrl: '/admin/rolelist',
  menu: '/admin/menu',
  adminUrl: '/admin/adminList',
  adminPwd: '/admin/adminPwd',
  userUrl: '/app/member',
  category: '/admin/category',
  goods: '/admin/goods',
  auctions: '/admin/auctions',
  schedule: '/admin/process',
  orders: '/admin/orders',
  complaint: '/admin/complaint',
  adminLogs: '/adminlogs/log',
  community: '/admin/community',
  image: '/admin/image',
  unlock: '/admin/adminUnlock'
}

export default api

export function getAdminLogsList (parameter) {
  return request({
    url: api.adminLogs,
    method: 'get',
    params: parameter
  })
}

export function delAdminLogs (parameter) {
  return request({
    url: api.adminLogs,
    method: 'delete',
    params: parameter
  })
}

export function getUnlock (parameter) {
  return request({
    url: api.unlock,
    method: 'get',
    params: parameter
  })
}

export function getImage (parameter) {
  return request({
    url: api.image,
    method: 'get',
    params: parameter
  })
}

export function getCommunityList (parameter) {
  return request({
    url: api.community,
    method: 'get',
    params: parameter
  })
}

export function delCommunity (parameter) {
  return request({
    url: api.community,
    method: 'delete',
    params: parameter
  })
}

export function addCommunity (parameter) {
  return request({
    url: api.community,
    method: 'post',
    data: parameter
  })
}

export function editCommunity (parameter) {
  return request({
    url: api.community,
    method: 'put',
    data: parameter
  })
}

export function getComplaintList (parameter) {
  return request({
    url: api.complaint,
    method: 'get',
    params: parameter
  })
}

export function delComplaint (parameter) {
  return request({
    url: api.complaint,
    method: 'delete',
    params: parameter
  })
}

export function getOrdersList (parameter) {
  return request({
    url: api.orders,
    method: 'get',
    params: parameter
  })
}

export function delOrders (parameter) {
  return request({
    url: api.orders,
    method: 'delete',
    params: parameter
  })
}

export function getScheduleList (parameter) {
  return request({
    url: api.schedule,
    method: 'get',
    params: parameter
  })
}

export function getAuctionsList (parameter) {
  return request({
    url: api.auctions,
    method: 'get',
    params: parameter
  })
}

export function delAuctions (parameter) {
  return request({
    url: api.auctions,
    method: 'delete',
    params: parameter
  })
}

export function addAuctions (parameter) {
  return request({
    url: api.auctions,
    method: 'post',
    data: parameter
  })
}

export function editAuctions (parameter) {
  return request({
    url: api.auctions,
    method: 'put',
    data: parameter
  })
}

export function uploadFile (parameter) {
  return request({
    url: api.upload,
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data: parameter
  })
}

export function getGoodsList (parameter) {
  return request({
    url: api.goods,
    method: 'get',
    params: parameter
  })
}

export function addGoods (parameter) {
  return request({
    url: api.goods,
    method: 'post',
    data: parameter
  })
}

export function delGoods (parameter) {
  return request({
    url: api.goods,
    method: 'delete',
    params: parameter
  })
}

export function editGoods (parameter) {
  return request({
    url: api.goods,
    method: 'put',
    data: parameter
  })
}

export function getCategoryList (parameter) {
  return request({
    url: api.category,
    method: 'get',
    params: parameter
  })
}

export function delCategory (parameter) {
  return request({
    url: api.category,
    method: 'delete',
    params: parameter
  })
}

export function addCategory (parameter) {
  return request({
    url: api.category,
    method: 'post',
    params: parameter
  })
}

export function editCategory (parameter) {
  return request({
    url: api.category,
    method: 'put',
    data: parameter
  })
}

export function getRoleList (parameter) {
  return request({
    url: api.roleUrl,
    method: 'get',
    params: parameter
  })
}

export function delRole (parameter) {
  return request({
    url: api.roleUrl,
    method: 'delete',
    params: parameter
  })
}

export function addRole (parameter) {
  return request({
    url: api.roleUrl,
    method: 'post',
    data: parameter
  })
}

export function editRole (parameter) {
  return request({
    url: api.roleUrl,
    method: 'put',
    data: parameter
  })
}

export function getMenu () {
  return request({
    url: api.menu,
    method: 'get'
  })
}

export function getAdminList (parameter) {
  return request({
    url: api.adminUrl,
    method: 'get',
    params: parameter
  })
}

export function delAdmin (parameter) {
  return request({
    url: api.adminUrl,
    method: 'delete',
    params: parameter
  })
}

export function addAdmin (parameter) {
  return request({
    url: api.adminUrl,
    method: 'post',
    data: parameter
  })
}

export function editAdmin (parameter) {
  return request({
    url: api.adminUrl,
    method: 'put',
    data: parameter
  })
}

export function resetAdmin (parameter) {
  return request({
    url: api.adminPwd,
    method: 'get',
    params: parameter
  })
}

export function editAdminPwd (parameter) {
  return request({
    url: api.adminPwd,
    method: 'put',
    data: parameter
  })
}

export function getUserList (parameter) {
  return request({
    url: api.userUrl,
    method: 'get',
    params: parameter
  })
}

export function delUser (parameter) {
  return request({
    url: api.userUrl,
    method: 'delete',
    params: parameter
  })
}

export function addUser (parameter) {
  return request({
    url: api.userUrl,
    method: 'post',
    data: parameter
  })
}

export function editUser (parameter) {
  return request({
    url: api.userUrl,
    method: 'put',
    data: parameter
  })
}
