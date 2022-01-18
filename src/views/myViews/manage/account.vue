<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-input v-model="queryParam.keyword" placeholder="ID/账号/姓名" allowClear/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-select v-model="queryParam.roleId" placeholder="选择角色" allowClear>
                  <a-select-option v-for="(item, index) in roleList" :key="'role' + index" :value="item.roleId">{{ item.roleName }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-select v-model="queryParam.status" placeholder="选择账号状态" allowClear>
                  <a-select-option value="2">停用</a-select-option>
                  <a-select-option value="0">启用</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="!advanced && 6 || 24" :sm="24">
              <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
                <a-button style="margin-left: 8px" @click="() => this.queryParam = {}">重置</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <div class="table-operator">
        <a-button v-action:add type="primary" icon="plus" @click="handleAccount(0, null)">新建账号</a-button>
      </div>
      <a-alert show-icon style="margin-bottom: 16px">
        <template slot="message">
          <span style="margin-right: 40px">已选择: <a style="font-weight: 600">{{ selectedRows.length }}</a>项</span>
          <a v-action:startEnd style="margin-right: 20px" @click="handle(0,0, null)">启用</a>
          <a v-action:startEnd style="margin-right: 20px" @click="handle(0,2, null)">停用</a>
          <a style="margin-right: 20px" @click="clear()">清空选择</a>
        </template>
      </a-alert>
      <s-table
        ref="table"
        size="default"
        rowKey="id"
        :columns="columns"
        :data="loadData"
        :alert="false"
        :rowSelection="rowSelection"
        showPagination="auto">
        <span slot="status" slot-scope="text">
          <a-badge :status="text | statusTypeFilter" :text="text | statusFilter" />
        </span>
        <span slot="account" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-action:edit @click="handleAccount(1, record)">编辑</a>
            <a v-if="record.status===2" v-action:startEnd style="margin-left: 10px" @click="handle(1,0, record)">启用</a>
            <a v-else-if="record.status===0" v-action:startEnd style="margin-left: 10px" @click="handle(1,2, record)">停用</a>
            <a v-action:reset style="margin-left: 10px" @click="resetPwd(record)">重置密码</a>
          </template>
        </span>
      </s-table>
      <!--表单区域-调整-->
      <a-modal
        v-model="handleUser"
        :maskClosable="false"
        :confirmLoading="handleLoading"
        :title="modalTitle"
        ok-text="确认"
        cancel-text="取消"
        width="600px"
        @ok="submitForm">
        <a-form-model
          ref="userForm"
          :model="userForm"
          :rules="userRules"
          :label-col="labelCol"
          :wrapper-col="wrapperCol">
          <a-form-model-item :labelAlign="'right'" label="用户账号：" prop="account">
            <a-input v-model="userForm.account" placeholder="请输入用户账号" style="width: 100%" allowClear />
          </a-form-model-item>
          <a-form-model-item v-if="modalTitle==='新增'" :labelAlign="'right'" label="账号密码：" prop="password">
            <a-input v-model="userForm.password" type="password" placeholder="请输入账号密码" allowClear style="width: 100%" />
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="用户姓名：" prop="realName">
            <a-input v-model="userForm.realName" allowClear placeholder="请输入用户姓名" style="width: 100%" />
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="联系方式：" prop="phone">
            <a-input v-model="userForm.phone" allowClear placeholder="请输入联系方式" style="width: 100%" />
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="角色权限：" prop="roleId">
            <a-select v-model="userForm.roleId" allowClear placeholder="请选择角色权限" style="width: 100%">
              <a-select-option v-for="(item, index) in roleList" :key="'role' + index" :value="item.roleId">{{ item.roleName }}</a-select-option>
            </a-select>
          </a-form-model-item>
        </a-form-model>
      </a-modal>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { Modal } from 'ant-design-vue'
import { STable, Ellipsis } from '@/components'
import { getUserList, updateUserStatus, addUser, editUser, resetPwd, getRoleList } from '@/api/manage'
import { checkPhone, checkChinese } from '@/utils/checkStr'
import md5 from 'md5'
const columns = [
  {
    title: 'ID',
    dataIndex: 'id'
  },
  {
    title: '账号',
    dataIndex: 'account',
    scopedSlots: { customRender: 'account' }
  },
  {
    title: '角色',
    dataIndex: 'roleName',
    scopedSlots: { customRender: 'roleName' }
  },
  {
    title: '姓名',
    dataIndex: 'realName'
  },
  {
    title: '联系方式',
    dataIndex: 'phone'
  },
  {
    title: '账号状态',
    dataIndex: 'status',
    sorter: true,
    scopedSlots: { customRender: 'status' }
  },
  {
    title: '操作',
    dataIndex: 'action',
    width: '180px',
    scopedSlots: { customRender: 'action' }
  }
]

const statusMap = {
  2: {
    status: 'default',
    text: '停用'
  },
  0: {
    status: 'processing',
    text: '启用'
  }
}
export default {
  name: 'AccountIndex',
  components: {
    STable,
    Ellipsis
  },
  data () {
    const validateAccount = (rule, value, callback) => {
      if (value !== '') {
        if (checkChinese(value)) {
          callback(new Error('用户账号不得包含中文'))
        } else {
          callback()
        }
      } else {
        callback(new Error('请输入用户账号'))
      }
    }
    const validatePhone = (rule, value, callback) => {
      if (value !== '') {
        if (!checkPhone(value)) {
          callback(new Error('请输入正确的联系方式'))
        } else {
          callback()
        }
      } else {
        callback()
        // callback(new Error('请输入联系方式'))
      }
    }
    this.columns = columns
    return {
      // create model
      visible: false,
      confirmLoading: false,
      // 高级搜索 展开/关闭
      advanced: false,
      // 查询参数
      queryParam: {},
      // 加载数据方法 必须为 Promise 对象
      loadData: parameter => {
        const requestParameters = Object.assign({}, parameter, this.queryParam)
        return getUserList(requestParameters)
          .then(res => {
            return res.data
          })
      },
      selectedRowKeys: [],
      selectedRows: [],
      modalTitle: '新增',
      handleUser: false,
      handleLoading: false,
      userForm: {
        id: null,
        account: '',
        password: '',
        phone: '',
        realName: '',
        roleId: null
      },
      userRules: {
        account: [
          { required: true, message: '请输入用户账号', trigger: 'blur' },
          { min: 1, max: 20, message: '用户账号长度为1-20位', trigger: 'blur' },
          { validator: validateAccount, trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入账号密码', trigger: 'blur' },
          { min: 6, max: 16, message: '账号密码长度为6-16位' }
        ],
        realName: [
          { required: true, message: '请输入用户姓名', trigger: 'blur' },
          { min: 1, max: 20, message: '用户姓名长度为1-20位' }
        ],
        roleId: [
          { required: true, message: '请选择角色权限', trigger: 'change' }
        ],
        phone: [
          { required: false, message: '请输入联系方式', trigger: 'blur' },
          { validator: validatePhone, trigger: 'blur' }
        ]
      },
      labelCol: { span: 6 },
      wrapperCol: { span: 12 },
      roleList: []
    }
  },
  filters: {
    statusFilter (type) {
      return statusMap[type].text
    },
    statusTypeFilter (type) {
      return statusMap[type].status
    }
  },
  computed: {
    rowSelection () {
      return {
        selectedRowKeys: this.selectedRowKeys,
        onChange: this.onSelectChange
      }
    }
  },
  created () {
    getRoleList({
      page: 0,
      rows: 0
    }).then(res => {
      this.roleList = res.data.list
    })
  },
  methods: {
    handleAccount (type, obj) {
      this.handleUser = true
      this.$nextTick(() => {
        this.$nextTick(() => {
          this.$refs.userForm.resetFields()
          if (type === 0) { // 新增
            this.modalTitle = '新增'
            this.userForm = {
              id: null,
              account: '',
              password: '',
              phone: '',
              realName: '',
              roleId: null
            }
          } else if (type === 1) { // 编辑
            this.modalTitle = '编辑'
            this.userForm = {
              id: obj.id,
              account: obj.account,
              password: obj.password,
              phone: obj.phone,
              realName: obj.realName,
              roleId: obj.roleId
            }
          }
        })
      })
    },
    onSelectChange (selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
    },
    resetSearchForm () {
      this.queryParam = {}
    },
    handle (type, status, obj) {
      // type  0：多选操作；1：单个操作
      // status 0：启用；2：停用
      let ids = []
      if (type === 0) {
        if (this.selectedRowKeys.length === 0) {
          this.$message.warning('请选择要停用/启用的用户')
          return
        }
        ids = Object.assign([], this.selectedRowKeys)
      } else if (type === 1) {
        ids.push(obj.id)
      }
      let title = ''
      let content = ''
      if (status === 0) {
        title = '确定要启用？'
        content = '启用该账号后，该账号可以正常使用'
      } else if (status === 2) {
        title = '确定要停用？'
        content = '停用该账号后，账号将无法登录，原有历史操作将保留'
      }
      Modal.confirm({
        title: title,
        content: content,
        onOk: () => {
          updateUserStatus({
            ids: ids,
            status: status
          }).then(() => {
            this.$refs.table.refresh(true)
            if (ids.indexOf(parseInt(sessionStorage.getItem('id'))) !== -1) {
              Modal.info({
                title: '提示',
                content: '您修改了个人状态，请重新登录',
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
          })
        }
      })
    },
    clear () {
      this.selectedRowKeys = []
      this.selectedRows = []
    },
    submitForm () {
      this.handleLoading = true
      this.$refs.userForm.validate(valid => {
        if (valid) {
          if (this.modalTitle === '新增') {
            this.userForm.password = md5(this.userForm.password).toUpperCase()
            addUser(this.userForm).then(() => {
              this.$refs.table.refresh(true)
              this.handleUser = false
              this.handleLoading = false
            }).catch(() => {
              this.handleLoading = false
            })
          } else if (this.modalTitle === '编辑') {
            editUser(this.userForm).then(() => {
              this.$refs.table.refresh(true)
              this.handleUser = false
              this.handleLoading = false
              if (this.userForm.id === parseInt(sessionStorage.getItem('id'))) {
                Modal.info({
                  title: '提示',
                  content: '您修改了个人信息，请重新登录',
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
              this.handleLoading = false
            })
          }
        } else {
          this.handleLoading = false
        }
      })
    },
    resetPwd (obj) {
      Modal.confirm({
        title: '重置密码',
        content: '确定重置密码？',
        onOk: () => {
          resetPwd({
            id: obj.id
          }).then(() => {
            this.$refs.table.refresh(true)
            Modal.success({
              content: '重置后密码为：123456'
            })
            if (obj.id === parseInt(sessionStorage.getItem('id'))) {
              Modal.info({
                title: '提示',
                content: '您重置了个人密码，请重新登录',
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
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
