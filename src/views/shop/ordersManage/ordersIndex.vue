<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <s-table
        ref="table"
        size="default"
        rowKey="ordersId"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <span slot="ordersName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
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
import { delOrders, getOrdersList } from '@/api/shop'
import { checkChinese } from '@/utils/checkStr'

const columns = [
    {
      title: '订单号',
      dataIndex: 'serialNum',
      scopedSlots: { customRender: 'serialNum' }
    },
    {
      title: '商品名称',
      align: 'center',
      dataIndex: 'goodsName'
    },
    {
      title: '卖家',
      align: 'center',
      dataIndex: 'sellUserName'
    },
    {
      title: '买家',
      align: 'center',
      dataIndex: 'buyUsersName'
    },
    {
      title: '成交价',
      align: 'center',
      dataIndex: 'buyPrice'
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
    name: 'OrdersIndex',
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
          return getOrdersList(requestParameters)
            .then(res => {
              return res.data
            })
        },
        selectedRowKeys: [],
        selectedRows: [],
        modalTitle: '新增',
        handleOrders: false,
        handleLoading: false,
        ordersForm: {
          ordersId: undefined,
          ordersName: ''
        },
        ordersRules: {
          ordersName: [
            { required: true, message: '请输入物品名称', trigger: 'blur' },
            { min: 1, max: 20, message: '物品名称长度为1-20位', trigger: 'blur' },
            { validator: validateAccount, trigger: 'blur' }
          ]
        },
        labelCol: { span: 6 },
        wrapperCol: { span: 12 },
        queryOrdersList: [],
        queryOrdersTypeList: []
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
            delOrders({
              ordersId: obj.ordersId
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
