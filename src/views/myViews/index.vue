<template>
  <a-card :bordered="false">
    <div style="font-size: 16px"><a style="font-weight: 600">{{ userName }}</a>，欢迎使用常宏多媒体设计播控平台</div>
    <div style="font-size: 16px; margin-top: 10px">您可以通过上方菜单进入对应功能操作您需要的内容，也可通过下方快捷入口进入对应功能</div>
    <a-row style="margin-top: 60px">
      <a-col v-if="DepartmentIndex !== -1" :span="4">
        <a-card-grid class="customGrid" @click="$router.push({ name: 'DepartmentIndex' })">
          <a style="font-weight: 600">机构管理</a>
        </a-card-grid>
      </a-col>
      <a-col v-if="PersonIndex !== -1" :span="4">
        <a-card-grid class="customGrid" @click="$router.push({ name: 'PersonIndex' })">
          <a style="font-weight: 600">用户管理</a>
        </a-card-grid>
      </a-col>
      <a-col v-if="DeviceIndex !== -1" :span="4">
        <a-card-grid class="customGrid" @click="$router.push({ name: 'DeviceIndex' })">
          <a style="font-weight: 600">设备管理</a>
        </a-card-grid>
      </a-col>
      <a-col v-if="AccountIndex !== -1" :span="4">
        <a-card-grid class="customGrid" @click="$router.push({ name: 'AccountIndex' })">
          <a style="font-weight: 600">平台账号管理</a>
        </a-card-grid>
      </a-col>
      <a-col v-if="RoleIndex !== -1" :span="4">
        <a-card-grid class="customGrid" @click="$router.push({ name: 'RoleIndex' })">
          <a style="font-weight: 600">角色管理</a>
        </a-card-grid>
      </a-col>
      <a-col v-if="DepartmentData !== -1" :span="4">
        <a-card-grid class="customGrid" @click="$router.push({ name: 'DepartmentData' })">
          <a style="font-weight: 600">机构数据</a>
        </a-card-grid>
      </a-col>
      <a-col v-if="DeviceData !== -1" :span="4">
        <a-card-grid class="customGrid" @click="$router.push({ name: 'DeviceData' })">
          <a style="font-weight: 600">设备数据</a>
        </a-card-grid>
      </a-col>
      <a-col v-if="InfoData !== -1" :span="4">
        <a-card-grid class="customGrid" @click="$router.push({ name: 'InfoData' })">
          <a style="font-weight: 600">资源数据</a>
        </a-card-grid>
      </a-col>
      <a-col v-if="PlatformLog !== -1" :span="4">
        <a-card-grid class="customGrid" @click="$router.push({ name: 'PlatformLog' })">
          <a style="font-weight: 600">平台操作日志</a>
        </a-card-grid>
      </a-col>
      <a-col v-if="UserLog !== -1" :span="4">
        <a-card-grid class="customGrid" @click="$router.push({ name: 'UserLog' })">
          <a style="font-weight: 600">用户操作日志</a>
        </a-card-grid>
      </a-col>
      <a-col v-if="SystemLog !== -1" :span="4">
        <a-card-grid class="customGrid" @click="$router.push({ name: 'SystemLog' })">
          <a style="font-weight: 600">系统版本日志</a>
        </a-card-grid>
      </a-col>
    </a-row>
  </a-card>
</template>

<script>
import { getInfo } from '@/api/login'
import _ from 'lodash'
export default {
  name: 'PlatIndex',
  data () {
    return {
      userName: sessionStorage.getItem('userName'),
      menuList: [],
      DepartmentIndex: -1,
      PersonIndex: -1,
      DeviceIndex: -1,
      AccountIndex: -1,
      RoleIndex: -1,
      DepartmentData: -1,
      DeviceData: -1,
      InfoData: -1,
      PlatformLog: -1,
      UserLog: -1,
      SystemLog: -1
    }
  },
  created () {
    getInfo().then(res => {
      this.menuList = res.data
      this.DepartmentIndex = _.findIndex(this.menuList, { permissionId: 'departmentIndex' })
      this.PersonIndex = _.findIndex(this.menuList, { permissionId: 'personIndex' })
      this.DeviceIndex = _.findIndex(this.menuList, { permissionId: 'deviceIndex' })
      this.AccountIndex = _.findIndex(this.menuList, { permissionId: 'accountIndex' })
      this.RoleIndex = _.findIndex(this.menuList, { permissionId: 'roleIndex' })
      this.DepartmentData = _.findIndex(this.menuList, { permissionId: 'departmentData' })
      this.DeviceData = _.findIndex(this.menuList, { permissionId: 'deviceData' })
      this.InfoData = _.findIndex(this.menuList, { permissionId: 'infoData' })
      this.PlatformLog = _.findIndex(this.menuList, { permissionId: 'platformLog' })
      this.UserLog = _.findIndex(this.menuList, { permissionId: 'userLog' })
      this.SystemLog = _.findIndex(this.menuList, { permissionId: 'systemLog' })
    })
  }
}
</script>

<style scoped>
  .customGrid {
    width: 80%;
    text-align: center;
    margin-left: 10%;
    cursor: pointer;
    margin-bottom: 20px
  }
</style>
