<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <a-form-model
        ref="userForm"
        style="width: 500px"
        :model="userForm"
        :rules="userRules"
        :label-col="labelCol"
        :wrapper-col="wrapperCol">
        <a-form-model-item :labelAlign="'right'" label="姓名：">
          <a-input v-model="userForm.adminName" allowClear disabled placeholder="请输入用户姓名" style="width: 100%" />
        </a-form-model-item>
        <a-form-model-item :labelAlign="'right'" label="账号：">
          <a-input v-model="userForm.adminNumber" allowClear disabled placeholder="请输入联系方式" style="width: 100%" />
        </a-form-model-item>
        <a-form-model-item :labelAlign="'right'" label="旧密码：" prop="oldPwd">
          <a-input v-model="userForm.oldPwd" type="password" allowClear placeholder="请输入原密码" style="width: 100%" />
        </a-form-model-item>
        <a-form-model-item :labelAlign="'right'" label="新密码：" prop="newPwd1">
          <a-input v-model="userForm.newPwd1" type="password" allowClear placeholder="请输入新密码" style="width: 100%" />
        </a-form-model-item>
        <a-form-model-item :labelAlign="'right'" allowClear label="密码确认：" prop="newPwd2">
          <a-input v-model="userForm.newPwd2" type="password" placeholder="请再次输入新密码" style="width: 100%" />
        </a-form-model-item>
        <a-form-model-item style="text-align: center">
          <a-button type="" style="margin-right: 20px" @click="$router.go(-1)">取消</a-button>
          <a-button :loading="submitLoading" htmlType="submit" type="primary" @click="handleSubmit">确定</a-button>
        </a-form-model-item>
      </a-form-model>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { editAdminPwd } from '@/api/shop'
import { Modal } from 'ant-design-vue'
import storage from 'store'
export default {
  name: 'PersonalIndex',
  data () {
    return {
      roleList: [],
      submitLoading: false,
      userForm: {
        adminName: null,
        adminNumber: '',
        oldPwd: '',
        newPwd1: '',
        newPwd2: ''
      },
      userRules: {
        oldPwd: [
          { required: true, message: '请输入原密码', trigger: 'blur' },
          { min: 6, max: 16, message: '密码长度为6-16位' }
        ],
        newPwd1: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 6, max: 16, message: '密码长度为6-16位' }
        ],
        newPwd2: [
          { required: true, message: '请再次输入新密码', trigger: 'blur' },
          { min: 6, max: 16, message: '密码长度为6-16位' }
        ]
      },
      labelCol: { span: 6 },
      wrapperCol: { span: 18 }
    }
  },
  created () {
    this.userForm.adminName = storage.get('userName')
    this.userForm.adminNumber = storage.get('userNo')
  },
  methods: {
    handleSubmit () {
      this.submitLoading = true
      this.$refs.userForm.validate(valid => {
        if (valid) {
          if (this.userForm.newPwd1 !== this.userForm.newPwd2) {
            this.$message.warning('两次输入的新密码不一致')
            this.submitLoading = false
            return
          }
          editAdminPwd({
            oldPwd: this.userForm.oldPwd,
            newPwd: this.userForm.newPwd1
          }).then(res => {
            this.submitLoading = false
            if (res.status === 0) {
              Modal.info({
                title: '提示',
                content: '个人信息修改成功，请重新登录',
                onOk: () => {
                  return this.$store.dispatch('Logout').then(() => {
                    this.$router.push({ path: '/user/Login' })
                  })
                },
                onCancel () {
                  return this.$store.dispatch('Logout').then(() => {
                    this.$router.push({ path: '/user/Login' })
                  })
                }
              })
            }
          }).catch(() => {
            this.submitLoading = false
          })
        } else {
          this.submitLoading = false
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
