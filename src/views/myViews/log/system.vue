<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-select v-model="queryParam.devType" placeholder="选择系统" allowClear>
                  <a-select-option :value="0">平台系统</a-select-option>
                  <a-select-option :value="1">用户系统（PC客户端）</a-select-option>
                  <a-select-option :value="2">播控设备端APP</a-select-option>
                  <a-select-option :value="3">用户系统（APP）</a-select-option>
                  <a-select-option :value="4">用户系统（WEB）</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <!--            <a-col :md="6" :sm="24">-->
            <!--              <a-form-item label="">-->
            <!--                <a-range-picker-->
            <!--                  v-model="date"-->
            <!--                  :defaultPickerValue="[moment(new Date(), 'YYYY-MM-DD'), moment(new Date(), 'YYYY-MM-DD')]"-->
            <!--                  format="YYYY-MM-DD"-->
            <!--                  allowClear-->
            <!--                  style="width: 100%">-->
            <!--                </a-range-picker>-->
            <!--              </a-form-item>-->
            <!--            </a-col>-->
            <a-col :md="!advanced && 6 || 24" :sm="24">
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
        <span slot="fileUrl" slot-scope="text">
          <!--<ellipsis :length="20" tooltip>{{ text }}</ellipsis>-->
          <a :href="text">{{ text }}</a>
        </span>
        <span slot="versionInfo" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="devType" slot-scope="text, record">
          <span v-if="record.devType===0">平台系统</span>
          <span v-else-if="record.devType===1">用户系统（PC客户端）</span>
          <span v-else-if="record.devType===2">播控设备端APP</span>
          <span v-else-if="record.devType===3">用户系统（APP）</span>
          <span v-else-if="record.devType===4">用户系统（WEB）</span>
        </span>
      </s-table>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import moment from 'moment'
import { STable, Ellipsis } from '@/components'
import { getSystemLogList } from '@/api/logs'
const columns = [
  {
    title: '序号',
    dataIndex: 'key'
  },
  {
    title: '系统',
    dataIndex: 'devType',
    scopedSlots: { customRender: 'devType' }
  },
  {
    title: '版本号',
    dataIndex: 'version'
  },
  {
    title: '更新介绍',
    dataIndex: 'versionInfo',
    scopedSlots: { customRender: 'versionInfo' }
  },
  {
    title: '附件内容',
    dataIndex: 'fileUrl',
    scopedSlots: { customRender: 'fileUrl' }
  },
  {
    title: '操作时间',
    dataIndex: 'updateTime'
  }
]
export default {
  name: 'SystemLog',
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
        return getSystemLogList(requestParameters)
          .then(res => {
            return res.data
          })
      }
    }
  },
  methods: {
    moment,
    resetSearchForm () {
      this.queryParam = {}
      this.date = []
    }
  }
}
</script>

<style scoped>

</style>
