<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <div class="table-operator">
          <a-button v-action:add type="primary" icon="plus" @click="handleAccount(0, null)">新增</a-button>
        </div>
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="4" :sm="24">
              <a-form-item label="">
                <a-input v-model="queryParam.keyword" placeholder="姓名模糊查询" allowClear/>
              </a-form-item>
            </a-col>
            <a-col :md="!advanced && 4 || 24" :sm="24">
              <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
                <a-button style="margin-left: 8px" @click="() => this.queryParam = { userTypeId: undefined }">重置</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <s-table
        ref="table"
        size="default"
        rowKey="userId"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <span slot="userAvatarurl" slot-scope="text">
          <img :src="text" width="40px">
        </span>
        <span slot="userGender" slot-scope="text">
          <a-badge :color="text | statusTypeFilter" :text="text | statusFilter" />
        </span>
        <span slot="userName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-action:edit style="margin-left: 10px" @click="handleAccount(1, record)">编辑</a>
            <a v-action:delete style="margin-left: 10px" @click="del(record)">删除</a>
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
          <a-form-model-item :labelAlign="'right'" label="姓名：" prop="userName">
            <a-input v-model="userForm.userName" placeholder="请输入姓名" style="width: 100%" allowClear />
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="性别：" prop="userGender">
            <a-select v-model="userForm.userGender" placeholder="请选择性别" allowClear>
              <a-select-option :value="1">男</a-select-option>
              <a-select-option :value="2">女</a-select-option>
            </a-select>
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="手机号：" prop="userPhone">
            <a-input v-model="userForm.userPhone" allowClear placeholder="请输入手机号" style="width: 100%" />
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="身份证：" prop="idCard">
            <a-input v-model="userForm.idCard" allowClear placeholder="请输入身份证" style="width: 100%" />
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="地址：" prop="userAddress">
            <a-input v-model="userForm.userAddress" allowClear placeholder="请输入地址" style="width: 100%" />
          </a-form-model-item>
        </a-form-model>
      </a-modal>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { Modal } from 'ant-design-vue'
import { Ellipsis, STable } from '@/components'
import { addUser, delUser, editUser, getUserList } from '@/api/shop'
import { checkChinese } from '@/utils/checkStr'

const columns = [
    {
      title: '头像',
      dataIndex: 'userAvatarurl',
      scopedSlots: { customRender: 'userAvatarurl' }
    },
    {
      title: '姓名',
      dataIndex: 'userName',
      align: 'center',
      scopedSlots: { customRender: 'userName' }
    },
    {
      title: '性别',
      dataIndex: 'userGender',
      align: 'center',
      scopedSlots: { customRender: 'userGender' }
    },
    {
      title: '手机号',
      align: 'center',
      dataIndex: 'userPhone'
    },
    {
      title: '身份证',
      align: 'center',
      dataIndex: 'idCard'
    },

    {
      title: '地址',
      align: 'center',
      dataIndex: 'userAddress'
    },
    // {
    //   title: '信用分',
    //   align: 'center',
    //   dataIndex: 'userCredit'
    // },
    {
      title: '操作',
      dataIndex: 'action',
      align: 'center',
      width: '180px',
      scopedSlots: { customRender: 'action' }
    }
  ]
  const sexMap = {
    1: {
      color: 'cyan',
      text: '男'
    },
    2: {
      color: 'red',
      text: '女'
    }
  }
  export default {
    name: 'MemIndex',
    components: {
      STable,
      Ellipsis
    },
    data () {
      const validateAccount = (rule, value, callback) => {
        if (value !== '') {
          if (checkChinese(value)) {
            callback()
          } else {
            callback(new Error('请输入正确的姓名'))
          }
        } else {
          callback(new Error('请输入姓名'))
        }
      }
      return {
        columns,
        // create model
        visible: false,
        confirmLoading: false,
        // 高级搜索 展开/关闭
        advanced: false,
        // 查询参数
        queryParam: {
          userType: undefined,
          keyword: null
        },
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
          userId: undefined,
          userName: '',
          userGender: 1,
          userPhone: '',
          idCard: '',
          userNumber: '',
          userAddress: '',
          userCredit: undefined
        },
        userRules: {
          realname: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            { min: 1, max: 20, message: '姓名长度为1-20位', trigger: 'blur' },
            { validator: validateAccount, trigger: 'blur' }
          ],
          userGender: [
            { required: true, message: '请选择性别', trigger: 'change' }
          ],
          userNumber: [
            { required: true, message: '请输入qq号', trigger: 'blur' },
            { min: 1, max: 20, message: 'qq号长度为1-20位' }
          ]
        },
        labelCol: { span: 6 },
        wrapperCol: { span: 12 },
        queryUserList: [],
        queryUserTypeList: []
      }
    },
    filters: {
      statusFilter (type) {
        return sexMap[type].text
      },
      statusTypeFilter (type) {
        return sexMap[type].color
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
    methods: {
      handleAccount (type, obj) {
        this.handleUser = true
        this.$nextTick(() => {
          this.$nextTick(() => {
            this.$refs.userForm.resetFields()
            if (type === 0) { // 新增
              this.modalTitle = '新增'
              this.userForm = {
                userName: '',
                userGender: 1,
                userPhone: '',
                idCard: '',
                userNumber: '',
                userAddress: '',
                userCredit: undefined
              }
            } else if (type === 1) { // 编辑
              this.modalTitle = '编辑'
              this.userForm = {
                userId: obj.userId,
                userName: obj.userName,
                userGender: parseInt(obj.userGender),
                userPhone: obj.userPhone,
                idCard: obj.idCard,
                userNumber: obj.userNumber,
                userAddress: obj.userAddress,
                userCredit: obj.userCredit
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
      clear () {
        this.selectedRowKeys = []
        this.selectedRows = []
      },
      submitForm () {
        this.handleLoading = true
        this.$refs.userForm.validate(valid => {
          if (valid) {
            if (this.modalTitle === '新增') {
              addUser(this.userForm).then(() => {
                Modal.success({
                  content: '新增用户成功，登录名：' + this.userForm.userName
                })
                this.$refs.table.refresh(true)
                this.handleUser = false
                this.handleLoading = false
              }).catch(() => {
                this.handleLoading = false
              })
              this.handleLoading = false
            } else if (this.modalTitle === '编辑') {
              editUser(this.userForm).then(() => {
                this.$refs.table.refresh(true)
                this.handleUser = false
                this.handleLoading = false
              }).catch(() => {
                this.handleLoading = false
              })
              this.handleLoading = false
            }
          } else {
            this.handleLoading = false
          }
        })
      },
      del (obj) {
        Modal.confirm({
          title: '删除',
          content: '确定删除？',
          onOk: () => {
            delUser({
              id: obj.userId
            }).then(() => {
              this.$refs.table.refresh(true)
            })
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
