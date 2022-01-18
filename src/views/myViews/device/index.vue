<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-input v-model="queryParam.keyword" placeholder="设备系统ID/设备名称" allowClear/>
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
                  <a-select-option v-for="(item, index) in orgList" :key="'depart' + index" :value="item.id">{{ item.orgName }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <!--            <a-col :md="4" :sm="24">-->
            <!--              <a-form-item label="">-->
            <!--                <a-select v-model="queryParam.enabled" placeholder="请选择设备状态" allowClear>-->
            <!--                  <a-select-option value="0">停用</a-select-option>-->
            <!--                  <a-select-option value="1">启用</a-select-option>-->
            <!--                </a-select>-->
            <!--              </a-form-item>-->
            <!--            </a-col>-->
            <a-col :md="4" :sm="24">
              <a-form-item label="">
                <a-select v-model="queryParam.onlineStatus" placeholder="请选择在线状态" allowClear>
                  <a-select-option value="1">在线</a-select-option>
                  <a-select-option value="0">离线</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="!advanced && 4 || 24" :sm="24">
              <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
                <a-button style="margin-left: 8px" @click="() => this.queryParam = {}">重置</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <!--      <a-alert show-icon style="margin-bottom: 16px">-->
      <!--        <template slot="message">-->
      <!--          <span style="margin-right: 40px">已选择: <a style="font-weight: 600">{{ selectedRows.length }}</a>项</span>-->
      <!--          <a style="margin-right: 20px" @click="handle(1, null)">启用</a>-->
      <!--          <a style="margin-right: 20px" @click="handle(0, null)">停用</a>-->
      <!--          <a style="margin-right: 20px" @click="clear()">清空选择</a>-->
      <!--        </template>-->
      <!--      </a-alert>-->
      <s-table
        ref="table"
        size="default"
        rowKey="id"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <span slot="enabled" slot-scope="text">
          <a-badge :status="text | statusTypeFilter" :text="text | statusFilter" />
        </span>
        <span slot="onlineStatus" slot-scope="text">
          <a-badge :status="text | statusTypeFilterOnline" :text="text | statusFilterOnline" />
        </span>
        <span slot="orgName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="storeName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-if="parseInt(record.enabled)===0" v-action:startEnd @click="handle(1, record)">启用</a>
            <a v-else-if="parseInt(record.enabled)===1" v-action:startEnd @click="handle(0, record)">停用</a>
          </template>
        </span>
      </s-table>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import moment from 'moment'
import { Modal } from 'ant-design-vue'
import { STable, Ellipsis } from '@/components'
import { getDevList, updateDev, getOrgList } from '@/api/device'
const columns = [
  {
    title: '设备系统ID',
    dataIndex: 'devCode'
  },
  // {
  //   title: '设备账号ID',
  //   dataIndex: 'devCode'
  // },
  {
    title: '设备主机ID',
    dataIndex: 'macId'
  },
  {
    title: '设备名称',
    dataIndex: 'devName'
  },
  // {
  //   title: '是否启用',
  //   dataIndex: 'enabled',
  //   sorter: true,
  //   scopedSlots: { customRender: 'enabled' }
  // },
  {
    title: '在线状态',
    dataIndex: 'onlineStatus',
    sorter: true,
    scopedSlots: { customRender: 'onlineStatus' }
  },
  {
    title: '所属机构',
    dataIndex: 'orgName',
    scopedSlots: { customRender: 'orgName' }
  },
  {
    title: '所属门店',
    dataIndex: 'storeName',
    scopedSlots: { customRender: 'storeName' }
  }
  // {
  //   title: '操作',
  //   dataIndex: 'action',
  //   width: '90px',
  //   scopedSlots: { customRender: 'action' }
  // }
]

const statusMap = {
  0: {
    status: 'default',
    text: '停用'
  },
  1: {
    status: 'processing',
    text: '启用'
  }
}
const onlineStatusMap = {
  1: {
    status: 'processing',
    text: '在线'
  },
  0: {
    status: 'error',
    text: '离线'
  }
}
export default {
  name: 'DeviceIndex',
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
        return getDevList(requestParameters)
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
    },
    statusFilterOnline (type) {
      return onlineStatusMap[type].text
    },
    statusTypeFilterOnline (type) {
      return onlineStatusMap[type].status
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
  computed: {
    rowSelection () {
      return {
        selectedRowKeys: this.selectedRowKeys,
        onChange: this.onSelectChange
      }
    }
  },
  methods: {
    onSelectChange (selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
    },
    resetSearchForm () {
      this.queryParam = {
        date: moment(new Date())
      }
    },
    handle (type, obj) {
      let ids = []
      if (obj) { // 单选
        ids.push(obj.id)
      } else { // 多选
        if (this.selectedRowKeys.length === 0) {
          this.$message.warning('请选择要停用/启用的设备')
          return
        }
        ids = Object.assign([], this.selectedRowKeys)
      }
      let title = ''
      let content = ''
      if (type === 1) {
        title = '启用'
        content = '确定启用该设备？'
      } else if (type === 0) {
        title = '停用'
        content = '确定停用该设备？'
      }
      Modal.confirm({
        title: title,
        content: content,
        onOk: () => {
          updateDev({
            deviceIds: ids.join(','),
            enabled: type
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
