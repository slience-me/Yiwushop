<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="5" :sm="24">
              <a-form-item label="">
                <a-select v-model="queryParam.adminId" placeholder="请选择管理员" allowClear>
                  <a-select-option v-for="(item, index) in queryAdminLogList" :key="'queryAdmin' + index" :value="item.adminId">{{ item.adminName }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="!advanced && 4 || 24" :sm="24">
              <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
                <a-button style="margin-left: 8px" @click="() => this.queryParam = { adminId: undefined }">重置</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <s-table
        ref="table"
        size="default"
        rowKey="adminLogsId"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-action:delete style="margin-left: 10px" @click="delAction(record)">删除</a>
          </template>
        </span>
      </s-table>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { Ellipsis, STable } from '@/components'
import { getAdminLogsList, delAdminLogs, getAdminList } from '@/api/shop'
import { Modal } from 'ant-design-vue'

const columns = [
  {
    title: '姓名',
    dataIndex: 'adminName'
  },
  {
    title: '记录',
    align: 'center',
    dataIndex: 'adminLogsOperate'
  },
  {
    title: '操作时间',
    align: 'center',
    dataIndex: 'createdTime'
  },
  {
    title: '操作',
    dataIndex: 'action',
    width: '180px',
    scopedSlots: { customRender: 'action' }
  }
]
export default {
  name: 'AdminLogIndex',
  components: {
    STable,
    Ellipsis
  },
  data () {
    this.columns = columns
    return {
      columns,
      // create model
      visible: false,
      confirmLoading: false,
      // 高级搜索 展开/关闭
      advanced: false,
      modalTitle: '新增',
      // 查询参数
      queryParam: {
        adminId: undefined
      },
      // 加载数据方法 必须为 Promise 对象
      loadData: parameter => {
        const requestParameters = Object.assign({}, parameter, this.queryParam)
        return getAdminLogsList(requestParameters)
          .then(res => {
            return res.data
          })
      },
      selectedRowKeys: [],
      selectedRows: [],
      handleFeedback: false,
      handleLoading: false,
      logForm: {
        adminLogId: undefined,
        adminLogOperate: '',
        adminId: undefined,
        createTime: undefined
      },
      feedbackForm: {
        feedbackContent: ''
      },
      labelCol: { span: 6 },
      wrapperCol: { span: 12 },
      queryAdminLogList: []
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
    getAdminList({
      pageNo: 1,
      pageSize: 20
    }).then(res => {
      this.queryAdminLogList = res.data.list
    })
  },
  methods: {
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
    delAction (obj) {
      Modal.confirm({
        title: '删除确认',
        content: '确定要进行删除?',
        onOk: () => {
          delAdminLogs({
            adminLogsId: obj.adminLogsId
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
