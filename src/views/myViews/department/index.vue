<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="4" :sm="24">
              <a-form-item label="">
                <a-input v-model="queryParam.keyword" placeholder="ID/机构名称/管理员" allowClear/>
              </a-form-item>
            </a-col>
            <a-col :md="4" :sm="24">
              <a-form-item label="">
                <a-select v-model="queryParam.status" placeholder="请选择机构状态" allowClear>
                  <a-select-option value="1">停用</a-select-option>
                  <a-select-option value="0">启用</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-range-picker
                  v-model="date"
                  :defaultPickerValue="[moment(new Date(), 'YYYY-MM-DD'), moment(new Date(), 'YYYY-MM-DD')]"
                  :placeholder="['到期开始时间', '到期结束时间']"
                  format="YYYY-MM-DD"
                  allowClear
                  style="width: 100%"/>
              </a-form-item>
            </a-col>
            <a-col :md="4" :sm="24">
              <a-form-item label="">
                <a-select v-model="queryParam.resourceDesignStatus" placeholder="是否开通资源设计" allowClear>
                  <a-select-option value="0">否</a-select-option>
                  <a-select-option value="1">是</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="!advanced && 6 || 24" :sm="24">
              <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
                <a-button style="margin-left: 8px" @click="resetSearchForm">重置</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <a-alert show-icon style="margin-bottom: 16px">
        <template slot="message">
          <span style="margin-right: 40px">已选择: <a style="font-weight: 600">{{ selectedRows.length }}</a>项</span>
          <a style="margin-right: 20px" @click="handle(0, null)">启用</a>
          <a style="margin-right: 20px" @click="handle(1, null)">停用</a>
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
        <span slot="resourceDesignStatus" slot-scope="text, record">
          <span v-if="record.resourceDesignStatus===0">否</span>
          <span v-else-if="record.resourceDesignStatus===1">是</span>
        </span>
        <span slot="deviceCurrent" slot-scope="text, record">
          <span>{{ record.deviceCurrent }}/{{ record.deviceMax }}</span>
        </span>
        <span slot="resourceMax" slot-scope="text, record">
          <span>{{ record.resourceMax }}G</span>
        </span>
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-action:edit @click="handleEdit(record)">机构权益</a>
            <a v-if="record.status===1" v-action:startEnd style="margin-left: 10px" @click="handle(0, record)">启用</a>
            <a v-else-if="record.status===0" v-action:startEnd style="margin-left: 10px" @click="handle(1, record)">停用</a>
            <a v-action:edit style="margin-left: 10px" @click="releaseAction">释放</a>
          </template>
        </span>
      </s-table>
      <!--表单区域-调整-->
      <a-modal
        v-model="handleService"
        :maskClosable="false"
        :confirmLoading="serviceLoading"
        :title="'调整机构权益'"
        ok-text="确认"
        cancel-text="取消"
        width="600px"
        @ok="submitForm">
        <a-form-model
          ref="serviceForm"
          :model="serviceForm"
          :rules="serviceRules"
          :label-col="labelCol"
          :wrapper-col="wrapperCol">
          <a-alert show-icon style="margin-bottom: 16px">
            <template slot="message">
              <span style="margin-right: 20px">{{ strForm.orgName }}</span>
              <span style="margin-right: 20px">设备容量：<span style="color: #1890ff">{{ strForm.deviceMax }}</span>台</span>
              <span style="margin-right: 20px">已使用：<span style="color: #52c41a">{{ strForm.deviceCurrent }}</span>台</span>
              <span style="margin-right: 20px">存储容量：<span style="color: #1890ff">{{ strForm.resourceMax }}</span>G</span>
              <span style="margin-right: 20px">已使用：<span style="color: #52c41a">{{ strForm.resourceCurrent }}</span>G</span>
              <span v-if="strForm.resourceDesignStatus===0" style="margin-right: 20px">未开通资源设计</span>
              <span v-else-if="strForm.resourceDesignStatus===1" style="margin-right: 20px">已开通资源设计</span>
              <span style="margin-right: 20px">到期时间：{{ strForm.endDate }}</span>
            </template>
          </a-alert>
          <a-form-model-item :labelAlign="'right'" label="设备容量：" prop="deviceMax">
            <a-input-number
              v-model="serviceForm.deviceMax"
              :precision="0"
              :min="strForm.deviceCurrent"
              allowClear
              placeholder="请输入设备容量"
              style="width: 100%" />
          </a-form-model-item>
          <!--:min="strForm.resourceCurrent"-->
          <a-form-model-item :labelAlign="'right'" label="存储容量：" prop="resourceMax">
            <a-input-number
              v-model="serviceForm.resourceMax"
              :precision="0"
              :min="0"
              allowClear
              placeholder="请输入存储容量"
              style="width: 96%" />G
            <span style="line-height: 0">当存储量达上限后，不可上传新内容，可删除不用资源。</span>
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="资源设计：" prop="resourceDesignStatus">
            <a-select v-model="serviceForm.resourceDesignStatus" placeholder="是否开通资源设计" style="width: 100%" allowClear>
              <a-select-option :value="0">否</a-select-option>
              <a-select-option :value="1">是</a-select-option>
            </a-select>
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="到期时间：" prop="endDate">
            <a-date-picker
              v-model="serviceForm.endDate"
              :defaultPickerValue="moment(new Date(), 'YYYY-MM-DD')"
              placeholder="请选择到期时间"
              format="YYYY-MM-DD"
              allowClear
              style="width: 100%"/>
          </a-form-model-item>
        </a-form-model>
      </a-modal>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import moment from 'moment'
import { Modal } from 'ant-design-vue'
import { STable, Ellipsis } from '@/components'
import { getOrgList, updateOrg, changeRight } from '@/api/device'
const columns = [
  {
    title: '机构ID',
    dataIndex: 'id'
  },
  {
    title: '机构名称',
    dataIndex: 'orgName',
    scopedSlots: { customRender: 'orgName' }
  },
  {
    title: '设备用量',
    dataIndex: 'deviceCurrent',
    scopedSlots: { customRender: 'deviceCurrent' }
  },
  {
    title: '存储容量',
    dataIndex: 'resourceMax',
    scopedSlots: { customRender: 'resourceMax' }
  },
  {
    title: '是否开通资源设计',
    dataIndex: 'resourceDesignStatus',
    scopedSlots: { customRender: 'resourceDesignStatus' }
  },
  {
    title: '状态',
    dataIndex: 'status',
    sorter: true,
    scopedSlots: { customRender: 'status' }
  },
  {
    title: '管理员',
    dataIndex: 'adminName'
  },
  {
    title: '联系方式',
    dataIndex: 'phone'
  },
  {
    title: '到期时间',
    dataIndex: 'endDate'
  },
  {
    title: '创建时间',
    dataIndex: 'createTime'
  },
  {
    title: '操作',
    dataIndex: 'action',
    width: '180px',
    scopedSlots: { customRender: 'action' }
  }
]

const statusMap = {
  1: {
    status: 'default',
    text: '停用'
  },
  0: {
    status: 'processing',
    text: '启用'
  }
}

export default {
  name: 'DepartmentIndex',
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
      date: [],
      // 查询参数
      queryParam: {},
      // 加载数据方法 必须为 Promise 对象
      loadData: parameter => {
        this.queryParam.startTime = this.date.length > 0 ? moment(this.date[0]).format('YYYY-MM-DD') : null
        this.queryParam.endTime = this.date.length > 0 ? moment(this.date[1]).format('YYYY-MM-DD') : null
        const requestParameters = Object.assign({}, parameter, this.queryParam)
        return getOrgList(requestParameters)
          .then(res => {
            return res.data
          })
      },
      selectedRowKeys: [],
      selectedRows: [],
      handleService: false,
      serviceLoading: false,
      serviceForm: {
        id: null,
        deviceMax: 0,
        resourceMax: 0,
        endDate: '',
        resourceDesignStatus: null
      },
      strForm: {
        resourceCurrent: 0,
        deviceCurrent: 0,
        orgName: '',
        endDate: '',
        deviceMax: 0,
        resourceMax: 0,
        resourceDesignStatus: null
      },
      serviceRules: {
        deviceMax: [
          { required: true, message: '请输入设备容量', trigger: 'blur' }
        ],
        resourceMax: [
          { required: true, message: '请输入资源容量', trigger: 'blur' }
        ],
        endDate: [
          { required: true, message: '请选择到期时间', trigger: 'change' }
        ],
        resourceDesignStatus: [
          { required: true, message: '请选择是否开通资源设计', trigger: 'change' }
        ]
      },
      labelCol: { span: 6 },
      wrapperCol: { span: 16 }
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
  methods: {
    moment,
    onSelectChange (selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
    },
    resetSearchForm () {
      this.queryParam = {}
      this.date = []
    },
    handle (type, obj) {
      let ids = []
      if (obj) { // 单选
        ids.push(obj.id)
      } else { // 多选
        if (this.selectedRowKeys.length === 0) {
          this.$message.warning('请选择要停用/启用的机构')
          return
        }
        ids = Object.assign([], this.selectedRowKeys)
      }
      let title = ''
      let content = ''
      if (type === 0) {
        title = '确定启用该机构？'
        content = '启用该机构后，该机构包含的用户账号将恢复使用，请确保该机构使用授权仍在有效期内'
      } else if (type === 1) {
        title = '确定停用该机构？'
        content = '停用该机构后，该机构包含的用户账号将无法使用，使用中的数据也将被停止，但剩余时长将持续扣除'
      }
      Modal.confirm({
        title: title,
        content: content,
        onOk: () => {
          updateOrg({
            orgIds: ids.join(','),
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
    handleEdit (obj) {
      this.handleService = true
      this.$nextTick(() => {
        this.$nextTick(() => {
          this.$refs.serviceForm.resetFields()
          this.serviceForm = {
            id: obj.id,
            deviceMax: obj.deviceMax,
            resourceMax: obj.resourceMax,
            endDate: moment(obj.endDate, 'YYYY-MM-DD'),
            resourceDesignStatus: parseInt(obj.resourceDesignStatus)
          }
          this.strForm = {
            deviceMax: obj.deviceMax,
            deviceCurrent: obj.deviceCurrent,
            resourceMax: obj.resourceMax,
            resourceCurrent: obj.resourceCurrent,
            endDate: obj.endDate,
            resourceDesignStatus: obj.resourceDesignStatus,
            orgName: obj.orgName
          }
        })
      })
    },
    submitForm () {
      this.serviceLoading = true
      this.$refs.serviceForm.validate(valid => {
        if (valid) {
          this.serviceForm.endDate = moment(this.serviceForm.endDate).format('YYYY-MM-DD')
          changeRight(this.serviceForm).then(() => {
            this.$refs.table.refresh(true)
            this.handleService = false
            this.serviceLoading = false
          }).catch(() => {
            this.serviceLoading = false
          })
        } else {
          this.serviceLoading = false
        }
      })
    },
    releaseAction () {
      Modal.info({
        content: '开发中，敬请期待...'
      })
    }
  }
}
</script>

<style scoped>

</style>
