<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
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
            <a-col :md="4" :sm="24">
              <a-form-item label="">
                <a-select v-model="queryParam.opUserId" placeholder="操作人" allowClear>
                  <a-select-option v-for="(item, index) in userList" :key="'user' + index" :value="item.id">{{ item.realName }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="4" :sm="24">
              <a-form-item label="">
                <a-input v-model="queryParam.keyword" placeholder="操作内容关键字" allowClear/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-range-picker
                  v-model="date"
                  :defaultPickerValue="[moment(new Date(), 'YYYY-MM-DD'), moment(new Date(), 'YYYY-MM-DD')]"
                  format="YYYY-MM-DD"
                  allowClear
                  style="width: 100%">
                </a-range-picker>
              </a-form-item>
            </a-col>
            <a-col :md="!advanced && 4 || 24" :sm="24">
              <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
                <a-button style="margin-left: 8px" @click="resetSearchForm">重置</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <s-table
        ref="table"
        size="default"
        rowKey="key"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <span slot="opModule" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="opDesc" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="platform" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
      </s-table>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import moment from 'moment'
import { STable, Ellipsis } from '@/components'
import { getUserLogList } from '@/api/logs'
import { getAllUser } from '@/api/manage'
import { getOrgList } from '@/api/device'
const columns = [
  {
    title: '序号',
    dataIndex: 'key'
  },
  {
    title: '操作时间',
    dataIndex: 'opTime'
  },
  {
    title: '操作人',
    dataIndex: 'opUserName'
  },
  {
    title: '操作人ID',
    dataIndex: 'opUserId'
  },
  {
    title: '所属机构',
    dataIndex: 'orgName'
  },
  {
    title: '所属机构ID',
    dataIndex: 'orgId'
  },
  {
    title: '操作模块',
    dataIndex: 'opModule'
  },
  {
    title: '操作',
    dataIndex: 'opType'
  },
  {
    title: '内容',
    dataIndex: 'opDesc'
  },
  {
    title: '操作平台',
    dataIndex: 'opPlatform'
  },
  {
    title: 'IP地址',
    dataIndex: 'opIp'
  }
]
export default {
  name: 'UserLog',
  components: {
    STable,
    Ellipsis
  },
  data () {
    this.columns = columns
    return {
      // 高级搜索 展开/关闭
      advanced: false,
      // 查询参数
      queryParam: {},
      date: [],
      // 加载数据方法 必须为 Promise 对象
      loadData: parameter => {
        this.queryParam.startTime = this.date.length > 0 ? moment(this.date[0]).format('YYYY-MM-DD') : null
        this.queryParam.endTime = this.date.length > 0 ? moment(this.date[1]).format('YYYY-MM-DD') : null
        const requestParameters = Object.assign({}, parameter, this.queryParam)
        return getUserLogList(requestParameters)
          .then(res => {
            return res.data
          })
      },
      orgList: [],
      userList: []
    }
  },
  created () {
    getAllUser({
      page: 0,
      rows: 0
    }).then(res => {
      this.userList = res.data.list
    })
    // getOrgList({
    //   page: 0,
    //   rows: 0
    // }).then(res => {
    //   this.orgList = res.data.list
    // })
  },
  methods: {
    moment,
    resetSearchForm () {
      this.queryParam = {}
      this.date = []
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
