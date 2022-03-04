<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <s-table
        ref="table"
        size="default"
        rowKey="goodsId"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <span slot="imageUrl" slot-scope="text">
          <img :src="text" width="40px">
        </span>
        <span slot="goodsName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="goodsPrice" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="priceNow" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="priceUserName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="goodsInfo" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="stateOn" slot-scope="text">
          <a-badge :color="text | statusTypeFilter" :text="text | statusFilter" />
        </span>
        <span slot="categoryName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="userName" slot-scope="text">
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
import { delGoods, getGoodsPawnList } from '@/api/shop'
import { checkChinese } from '@/utils/checkStr'

const columns = [
    {
      title: '物品照片',
      dataIndex: 'imageUrl',
      align: 'center',
      scopedSlots: { customRender: 'imageUrl' }
    },
    {
      title: '物品名称',
      dataIndex: 'goodsName',
      align: 'center',
      scopedSlots: { customRender: 'goodsName' }
    },
    {
      title: '物品价格',
      dataIndex: 'goodsPrice',
      align: 'center',
      scopedSlots: { customRender: 'goodsPrice' }
    },
    // {
    //   title: '当前出价',
    //   dataIndex: 'priceNow',
    //   align: 'center',
    //   scopedSlots: { customRender: 'priceNow' }
    // },
    // {
    //   title: '当前出价人',
    //   dataIndex: 'priceUserName',
    //   align: 'center',
    //   scopedSlots: { customRender: 'priceUserName' }
    // },
    {
      title: '物品描述',
      dataIndex: 'goodsInfo',
      align: 'center',
      scopedSlots: { customRender: 'goodsInfo' }
    },
    {
      title: '上架状态',
      dataIndex: 'stateOn',
      align: 'center',
      scopedSlots: { customRender: 'stateOn' }
    },
    {
      title: '物品类型',
      dataIndex: 'categoryName',
      align: 'center',
      scopedSlots: { customRender: 'categoryName' }
    },
    {
      title: '拥有者',
      dataIndex: 'userName',
      align: 'center',
      scopedSlots: { customRender: 'userName' }
    },
    {
      title: '操作',
      dataIndex: 'action',
      align: 'center',
      width: '180px',
      scopedSlots: { customRender: 'action' }
    }
  ]
  const goodsStatusMap = {
    1: {
      color: '#FA541C',
      text: '未上架'
    },
    2: {
      color: '#FAAD14',
      text: '已上架'
    },
    3: {
      color: '#52C41A',
      text: '已售'
    },
    4: {
      color: '#52C41A',
      text: '待典当'
    }
  }
  export default {
    name: 'GoodsPawnIndex',
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
          return getGoodsPawnList(requestParameters)
            .then(res => {
              return res.data
            })
        },
        selectedRowKeys: [],
        selectedRows: [],
        imgLoading: false,
        modalTitle: '新增',
        handleGoods: false,
        formDisabled: false,
        handleLoading: false,
        goodsForm: {
          goodsId: null,
          goodsName: '',
          goodsImgId: undefined,
          imageUrl: '',
          goodsPrice: '',
          priceNow: '',
          priceUserId: undefined,
          priceUserName: '',
          goodsInfo: '',
          stateOn: '',
          categoryId: undefined,
          categoryName: '',
          userId: undefined,
          userName: ''
        },
        goodsRules: {
          goodsName: [
            { required: true, message: '请输入物品名称', trigger: 'blur' },
            { min: 1, max: 20, message: '物品名称长度为1-20位', trigger: 'blur' },
            { validator: validateAccount, trigger: 'blur' }
          ]
        },
        labelCol: { span: 6 },
        wrapperCol: { span: 12 },
        queryGoodsList: [],
        queryGoodsTypeList: []
      }
    },
    filters: {
      statusFilter (type) {
        return goodsStatusMap[type].text
      },
      statusTypeFilter (type) {
        return goodsStatusMap[type].color
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
            delGoods({
              goodsId: obj.goodsId
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
