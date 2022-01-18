<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-input v-model="queryParam.keyword" placeholder="姓名/账号" allowClear/>
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
        <a-button v-action:add type="primary" icon="plus" @click="handleAccount(0, null)">新增</a-button>
      </div>
      <s-table
        ref="table"
        size="default"
        rowKey="adminId"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <span slot="status" slot-scope="text">
          <a-badge :status="text | statusTypeFilter" :text="text | statusFilter" />
        </span>
        <span slot="roleName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-action:reset @click="resetPwd(record)">重置密码</a>
            <a v-action:edit style="margin-left: 10px" @click="handleAccount(1, record)">编辑</a>
            <a v-action:delete style="margin-left: 10px" @click="delAction(record)">删除</a>
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
          <a-form-model-item :labelAlign="'right'" label="姓名：" prop="adminName">
            <a-input v-model="userForm.adminName" allowClear placeholder="请输入姓名" style="width: 100%" />
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="账号：" prop="adminNumber">
            <a-input v-model="userForm.adminNumber" allowClear placeholder="请输入账号" style="width: 100%" />
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
  import { getAdminList, delAdmin, addAdmin, editAdmin, resetAdmin, getRoleList } from '@/api/shop'
  import { checkPhone } from '@/utils/checkStr' // checkChinese
  import md5 from 'md5'
  const columns = [
    {
      title: '姓名',
      dataIndex: 'adminName'
    },
    {
      title: '账号',
      dataIndex: 'adminNumber'
    },
    {
      title: '角色',
      dataIndex: 'roleName',
      scopedSlots: { customRender: 'roleName' }
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
    name: 'AdminIndex',
    components: {
      STable,
      Ellipsis
    },
    data () {
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
          this.queryParam.page = 0
          this.queryParam.limit = 0
          const requestParameters = Object.assign({}, parameter, this.queryParam)
          return getAdminList(requestParameters)
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
          adminId: null,
          adminName: '',
          password: '',
          adminNumber: '',
          roleId: undefined
        },
        userRules: {
          adminNumber: [
            { required: true, message: '请输入账号', trigger: 'blur' },
            { validator: validatePhone, trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入账号密码', trigger: 'blur' },
            { min: 6, max: 16, message: '账号密码长度为6-16位' }
          ],
          adminName: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            { min: 1, max: 20, message: '姓名长度为1-20位' }
          ],
          roleId: [
            { required: true, message: '请选择角色权限', trigger: 'change' }
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
      }).then(res => {
        this.roleList = res.data
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
                adminId: null,
                adminName: '',
                password: '',
                adminNumber: '',
                roleId: undefined
              }
            } else if (type === 1) { // 编辑
              this.modalTitle = '编辑'
              this.userForm = {
                adminId: obj.adminId,
                adminName: obj.adminName,
                password: obj.password,
                adminNumber: obj.adminNumber,
                realName: obj.realName,
                roleId: obj.roleId
              }
            }
          })
        })
      },
      delAction (obj) {
        Modal.confirm({
          title: '删除确认',
          content: '确定要进行删除?',
          onOk: () => {
            delAdmin({
              adminId: obj.adminId
            }).then(() => {
              this.$refs.table.refresh(true)
            })
          }
        })
      },
      onSelectChange (selectedRowKeys, selectedRows) {
        this.selectedRowKeys = selectedRowKeys
        this.selectedRows = selectedRows
      },
      resetSearchForm () {
        this.queryParam = {}
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
              addAdmin(this.userForm).then(() => {
                Modal.success({
                  content: '新增管理员成功，登录账号：' + this.userForm.adminNumber + '，登录密码：000000'
                })
                this.$refs.table.refresh(true)
                this.handleUser = false
                this.handleLoading = false
              }).catch(() => {
                this.handleLoading = false
              })
            } else if (this.modalTitle === '编辑') {
              editAdmin(this.userForm).then(() => {
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
            resetAdmin({
              adminId: obj.adminId
            }).then(() => {
              this.$refs.table.refresh(true)
              Modal.success({
                content: '重置后密码为：000000'
              })
              if (obj.adminId === parseInt(sessionStorage.getItem('userId'))) {
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
