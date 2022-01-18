import request from '@/utils/request'

const api = {
  user: '/broadcast-platform-service/user',
  updateUserStatus: '/broadcast-platform-service/user/status',
  addUser: '/broadcast-platform-service/user',
  editUser: '/broadcast-platform-service/user',
  resetPwd: '/broadcast-platform-service/user/password/reset',
  userInfo: '/broadcast-platform-service/user',
  upload: '/broadcast-resource-service/files/upload',
  roleUrl: '/broadcast-auth-service/platform/role',
  menu: '/broadcast-auth-service/platform/menu',
  personList: '/broadcast-platform-service/org/user/all',
  updatePersonStatus: '/broadcast-platform-service/org/user/stop-start'
}

export default api

export function getUserList (parameter) {
  return request({
    url: api.user,
    method: 'get',
    params: parameter
  })
}

export function updateUserStatus (parameter) {
  return request({
    url: api.updateUserStatus,
    method: 'put',
    data: parameter
  })
}

export function addUser (parameter) {
  return request({
    url: api.addUser,
    method: 'post',
    data: parameter
  })
}

export function editUser (parameter) {
  return request({
    url: api.editUser,
    method: 'put',
    data: parameter
  })
}

export function resetPwd (parameter) {
  return request({
    url: api.resetPwd,
    method: 'put',
    params: parameter
  })
}

export function getUserInfo (parameter) {
  return request({
    url: api.userInfo + '/' + parameter,
    method: 'get'
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

export function getAllUser (parameter) {
  return request({
    url: api.personList,
    method: 'get',
    params: parameter
  })
}

export function updatePersonStatus (parameter) {
  return request({
    url: api.updatePersonStatus,
    method: 'put',
    params: parameter
  })
}
