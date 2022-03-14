import storage from 'store'
import { login, getInfo, logout } from '@/api/login'
import { ACCESS_TOKEN } from '@/store/mutation-types'

const user = {
  state: {
    token: '',
    name: '',
    avatar: '',
    roleId: '',
    roles: [],
    info: {},
    id: null
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
      sessionStorage.setItem('userName', name)
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
      sessionStorage.setItem('avatar', avatar)
    },
    SET_ROLEID: (state, roleId) => {
      state.roleId = roleId
      sessionStorage.setItem('roleId', roleId)
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_INFO: (state, info) => {
      state.info = info
    },
    SET_ID: (state, id) => {
      state.id = id
      sessionStorage.setItem('id', id)
    }
  },

  actions: {
    Login ({ commit }, userInfo) {
      return new Promise((resolve, reject) => {
        login(userInfo).then(response => {
          const result = response.data
          storage.set(ACCESS_TOKEN, result['x-access-token'], result.exp)
          commit('SET_TOKEN', result['x-access-token'])
          commit('SET_ROLEID', result.userInfo.roleId)
          commit('SET_ID', result.id)
          commit('SET_INFO', result.userInfo)
          commit('SET_NAME', result.userInfo.adminName)
          commit('SET_AVATAR', result.avatar)
          storage.set('userId', result.userInfo.adminId)
          storage.set('userName', result.userInfo.adminName)
          storage.set('userNo', result.userInfo.adminNumber)
          storage.set('avatar', result.avatar)
          resolve(result)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo ({ commit }, roleId) {
      return new Promise((resolve, reject) => {
        getInfo().then(response => {
          const data = response.data
          const result = {
            role: {
              permissions: data
            }
          }
          if (result.role && result.role.permissions.length > 0) {
            const role = result.role
            role.permissions = result.role.permissions
            role.permissions.map(per => {
              if (per.actionEntitySet != null && per.actionEntitySet.length > 0) {
                const action = per.actionEntitySet.map(action => { return action.action })
                per.actionList = action
              }
            })
            role.permissionList = role.permissions.map(permission => { return permission.permissionId })
            commit('SET_ROLES', result.role)
          } else {
            reject(new Error('该角色暂无权限'))
          }
          resolve(result)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    Logout ({ commit, state }) {
      return new Promise((resolve) => {
        logout().then(() => {
          resolve()
        }).catch(() => {
          resolve()
        }).finally(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('SET_ROUTERS', [])
          storage.remove(ACCESS_TOKEN)
        })
      })
    }

  }
}

export default user
