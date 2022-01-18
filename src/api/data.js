import request from '@/utils/request'

const api = {
  org: '/broadcast-platform-service/stats/org',
  orgChart: '/broadcast-platform-service/stats/org/chart',
  dev: '/broadcast-platform-service/stats/device',
  devChart: '/broadcast-platform-service/stats/device/chart',
  info: '/broadcast-platform-service/stats/resource',
  infoChart: '/broadcast-platform-service/stats/resource/chart'
}

export default api

export function getOrg (parameter) {
  return request({
    url: api.org,
    method: 'get',
    params: parameter
  })
}

export function getOrgChart (parameter) {
  return request({
    url: api.orgChart,
    method: 'get',
    params: parameter
  })
}

export function getDev (parameter) {
  return request({
    url: api.dev,
    method: 'get',
    params: parameter
  })
}

export function getDevChart (parameter) {
  return request({
    url: api.devChart,
    method: 'get',
    params: parameter
  })
}

export function getInfo (parameter) {
  return request({
    url: api.info,
    method: 'get',
    params: parameter
  })
}

export function getInfoChart (parameter) {
  return request({
    url: api.infoChart,
    method: 'get',
    params: parameter
  })
}
