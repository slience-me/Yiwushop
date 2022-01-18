<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <s-table
        ref="table"
        size="default"
        rowKey="complaintId"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <span slot="complaintStatus" slot-scope="text">
          <a-badge :color="text | statusTypeFilter" :text="text | statusFilter" />
        </span>
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-action:delete style="margin-left: 10px" @click="del(record)">删除</a>
          </template>
        </span>
      </s-table>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { Modal } from 'ant-design-vue'
import { Ellipsis, STable } from '@/components'
import { delComplaint, getComplaintList } from '@/api/shop'
import { checkChinese } from '@/utils/checkStr'

const complainTypeMap = {
  1: {
    color: 'red',
    text: '待受理'
  },
  2: {
    color: '#52C41A',
    text: '已受理'
  },
  3: {
    color: '#2F54EB',
    text: '待商家处理'
  },
  4: {
    color: '#FAAD14',
    text: '商家已处理'
  },
  5: {
    color: '#FA541C',
    text: '待买家同意协商'
  },
  6: {
    color: 'cyan',
    text: '已完成'
  }
}

const columns = [
    {
      title: '投诉订单号',
      dataIndex: 'serialNum',
      scopedSlots: { customRender: 'serialNum' }
    },
    {
      title: '用户名称',
      dataIndex: 'userName',
      align: 'center',
      scopedSlots: { customRender: 'userName' }
    },
    {
      title: '投诉状态',
      align: 'center',
      dataIndex: 'complaintStatus',
      scopedSlots: { customRender: 'complaintStatus' }
    },
    {
      title: '问题描述',
      align: 'center',
      dataIndex: 'remark',
      scopedSlots: { customRender: 'remark' }
    },
    {
      title: '投诉时间',
      align: 'center',
      dataIndex: 'createdTime',
      scopedSlots: { customRender: 'createdTime' }
    },
    {
      title: '操作',
      dataIndex: 'action',
      align: 'center',
      width: '180px',
      scopedSlots: { customRender: 'action' }
    }
  ]

  export default {
    name: 'ComplaintIndex',
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
            callback(new Error('请输入正确的名称'))
          }
        } else {
          callback(new Error('请输入名称'))
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
        queryParam: {
          keyword: null
        },
        // 加载数据方法 必须为 Promise 对象
        loadData: parameter => {
          const requestParameters = Object.assign({}, parameter, this.queryParam)
          return getComplaintList(requestParameters)
            .then(res => {
              return res.data
            })
        },
        selectedRowKeys: [],
        selectedRows: [],
        modalTitle: '新增',
        handleComplaint: false,
        handleLoading: false,
        complaintForm: {
          complaintId: undefined,
          complaintName: '',
          complaintStatus: '',
          createdTime: '',
          ordersId: '',
          remark: '',
          serialNum: '',
          userId: '',
          userName: ''
        },
        complaintRules: {
          complaintName: [
            { required: true, message: '请输入物品名称', trigger: 'blur' },
            { min: 1, max: 20, message: '物品名称长度为1-20位', trigger: 'blur' },
            { validator: validateAccount, trigger: 'blur' }
          ]
        },
        labelCol: { span: 6 },
        wrapperCol: { span: 12 }
      }
    },
    filters: {
      statusFilter (type) {
        return complainTypeMap[type].text
      },
      statusTypeFilter (type) {
        return complainTypeMap[type].color
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
      del (obj) {
        Modal.confirm({
          title: '删除',
          content: '确定删除？',
          onOk: () => {
            delComplaint({
              complaintId: obj.complaintId
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
