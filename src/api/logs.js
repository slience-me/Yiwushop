import request from '@/utils/request'

const logApi = {
  platformLog: '/broadcast-msg-log-service/log/platform',
  userLog: '/broadcast-msg-log-service/log/org',
  systemLog: '/broadcast-resource-service/version'
}

export function getPlatformLogList (parameter) {
  return request({
    url: logApi.platformLog,
    method: 'get',
    params: parameter
  })
}

export function getUserLogList (parameter) {
  return request({
    url: logApi.userLog,
    method: 'get',
    params: parameter
  })
}

export function getSystemLogList (parameter) {
  return request({
    url: logApi.systemLog,
    method: 'get',
    params: parameter
  })
}
