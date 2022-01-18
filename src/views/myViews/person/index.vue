<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-input v-model="queryParam.keyword" placeholder="ID/用户账号/联系方式" allowClear/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-select
                  v-model="queryParam.orgId"
                  show-search
                  :default-active-first-option="false"
                  :show-arrow="false"
                  :filter-option="false"
                  :not-found-content="null"
                  placeholder="请输入机构名称"
                  allowClear
                  @search="handleSearch">
                  <a-select-option v-for="(item, index) in orgList" :key="'org' + index" :value="item.id">{{ item.orgName }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-select v-model="queryParam.status" placeholder="请选择用户状态" allowClear>
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
      <a-alert show-icon style="margin-bottom: 16px">
        <template slot="message">
          <span style="margin-right: 40px">已选择: <a style="font-weight: 600">{{ selectedRows.length }}</a>项</span>
          <a style="margin-right: 20px" @click="handle(0, null)">启用</a>
          <a style="margin-right: 20px" @click="handle(2, null)">停用</a>
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
        <span slot="orgName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="isAdmin" slot-scope="text, record">
          <span v-if="record.isAdmin===0">否</span>
          <span v-else-if="record.isAdmin===1">是</span>
        </span>
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-if="record.status===0" v-action:startEnd @click="handle(2, record)">停用</a>
            <a v-else-if="record.status===2" v-action:startEnd @click="handle(0, record)">启用</a>
          </template>
        </span>
      </s-table>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { STable, Ellipsis } from '@/components'
import { getAllUser, updatePersonStatus } from '@/api/manage'
import { getOrgList } from '@/api/device'
import { Modal } from 'ant-design-vue'
const columns = [
  {
    title: '用户ID',
    dataIndex: 'id'
  },
  {
    title: '用户账号',
    dataIndex: 'account'
  },
  {
    title: '用户名称',
    dataIndex: 'realName'
  },
  {
    title: '联系方式',
    dataIndex: 'phone'
  },
  {
    title: '状态',
    dataIndex: 'status',
    sorter: true,
    scopedSlots: { customRender: 'status' }
  },
  {
    title: '所属机构',
    dataIndex: 'orgName',
    scopedSlots: { customRender: 'orgName' }
  },
  {
    title: '是否是机构管理员',
    dataIndex: 'isAdmin',
    scopedSlots: { customRender: 'isAdmin' }
  },
  {
    title: '操作',
    dataIndex: 'action',
    width: '90px',
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
  name: 'PersonIndex',
  components: {
    STable,
    Ellipsis
  },
  data () {
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
        return getAllUser(requestParameters)
          .then(res => {
            return res.data
          })
      },
      selectedRowKeys: [],
      selectedRows: [],
      orgList: []
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
    // getOrgList({
    //   page: 0,
    //   rows: 0
    // }).then(res => {
    //   this.orgList = res.data.list
    // })
  },
  methods: {
    onSelectChange (selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
    },
    handle (type, obj) {
      // type 0：启用；2：停用
      let ids = []
      if (obj) {
        ids.push(obj.id)
      } else {
        if (this.selectedRowKeys.length === 0) {
          this.$message.warning('请选择要停用/启用的用户')
          return
        }
        ids = Object.assign([], this.selectedRowKeys)
      }
      let title = ''
      let content = ''
      if (type === 0) {
        title = '确定要启用？'
        content = '启用该账号后，该账号可以正常使用'
      } else if (type === 2) {
        title = '确定要停用？'
        content = '停用该账号后，账号将无法登录，原有历史操作将保留'
      }
      Modal.confirm({
        title: title,
        content: content,
        onOk: () => {
          updatePersonStatus({
            userIds: ids.join(','),
            status: type
          }).then(() => {
            this.$refs.table.refresh(true)
          })
        }
      })
    },
    clear () {
      this.selectedRowKeys = []
      this.selectedRows = []
    },
    handleSearch (value) {
      getOrgList({
        page: 0,
        rows: 0,
        keyword: value
      }).then(res => {
        this.orgList = res.data.list
      })
    }
  }
}
</script>

<style scoped>

</style>
