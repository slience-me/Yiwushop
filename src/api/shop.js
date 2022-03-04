import request from '@/utils/request'

const api = {
  upload: '/upload/img',
  roleUrl: '/admin/rolelist',
  menu: '/admin/menu',
  adminUrl: '/admin/adminList',
  adminPwd: '/admin/adminPwd',
  userUrl: '/app/member',
  category: '/category/categoryList',
  goods: '/goods/goodsList',
  goodsdata: '/goods/data',
  goods1: '/goods/goodsDoneList',
  goods2: '/goods/goodsOnList',
  goods3: '/goods/stateOn',
  goods4: '/goods/goodsNoList',
  goods5: '/goods/goodsPawnList',
  goods6: '/goods/stateOnToPawn',
  auctions: '/auctions/auctionsList',
  schedule: '/auction/auctionScheduleList',
  orders: '/orders/ordersList',
  complaint: '/complaint/complaintList',
  pawn: '/pawn/pawnList',
  pawndo: '/pawn/do',
  community: '/community'
}

export default api

export function getCommunityList (parameter) {
  return request({
    url: api.community,
    method: 'get',
    params: parameter
  })
}

export function getGoodsData (parameter) {
  return request({
    url: api.goodsdata,
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

export function getPawnList (parameter) {
  return request({
    url: api.pawn,
    method: 'get',
    params: parameter
  })
}

export function delPawn (parameter) {
  return request({
    url: api.pawn,
    method: 'delete',
    params: parameter
  })
}

export function addPawn (parameter) {
  return request({
    url: api.pawn,
    method: 'post',
    data: parameter
  })
}

export function editPawn (parameter) {
  return request({
    url: api.pawn,
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

export function getGoodsDoneList (parameter) {
  return request({
    url: api.goods1,
    method: 'get',
    params: parameter
  })
}

export function getGoodsPawnList (parameter) {
  return request({
    url: api.goods5,
    method: 'get',
    params: parameter
  })
}

export function getGoodsOnList (parameter) {
  return request({
    url: api.goods2,
    method: 'get',
    params: parameter
  })
}

export function getGoodsNoList (parameter) {
  return request({
    url: api.goods4,
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

export function addStateOn (parameter) {
  return request({
    url: api.goods3,
    method: 'post',
    data: parameter
  })
}

export function addStateOnToPawn (parameter) {
  return request({
    url: api.goods6,
    method: 'post',
    data: parameter
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
