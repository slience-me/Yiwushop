import request from '@/utils/request'

const api = {
  deviceList: '/broadcast-platform-service/device',
  updateDev: '/broadcast-platform-service/device/stop-start',
  orgList: '/broadcast-platform-service/org',
  updateOrg: '/broadcast-platform-service/org/stop-start',
  updateRight: '/broadcast-platform-service/org/service'
}

export default api

export function getDevList (parameter) {
  return request({
    url: api.deviceList,
    method: 'get',
    params: parameter
  })
}

export function updateDev (parameter) {
  return request({
    url: api.updateDev,
    method: 'put',
    params: parameter
  })
}

export function getOrgList (parameter) {
  return request({
    url: api.orgList,
    method: 'get',
    params: parameter
  })
}

export function updateOrg (parameter) {
  return request({
    url: api.updateOrg,
    method: 'put',
    params: parameter
  })
}

export function changeRight (parameter) {
  return request({
    url: api.updateRight,
    method: 'put',
    data: parameter
  })
}
