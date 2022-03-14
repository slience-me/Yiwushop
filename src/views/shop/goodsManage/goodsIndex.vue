<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-select v-model="queryParam.stateOn" placeholder="商品状态类型" allowClear>
                  <a-select-option v-for="(item, index) in goodsStatusList" :key="'queryGoodsType' + index" :value="item.id">{{ item.name }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-input v-model="queryParam.goodsName" placeholder="商品精确查询" allowClear/>
              </a-form-item>
            </a-col>
            <a-col :md="!advanced && 4 || 24" :sm="24">
              <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
                <a-button style="margin-left: 8px" @click="() => this.queryParam = { stateOn: undefined, goodsName: null }">重置</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
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
          <ellipsis :length="40" tooltip>{{ text }}</ellipsis>
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
            <a v-action:select style="margin-left: 10px" @click="AboutImage(record)">图片</a>
            <a v-action:delete style="margin-left: 10px" @click="del(record)">删除</a>
          </template>
        </span>
      </s-table>
      <a-modal
        v-model="detailShow"
        :maskClosable="false"
        :title="'查看 '"
        :footer="null"
        width="800px">
        <div style="width: 700px; margin: 0 auto; height: 600px; overflow: auto">
          <div style="width: 100%; display: flex; margin-top: 50px">
            <div style="width: 10px; height: 20px; background-color: rgb(13, 104, 255); margin-top: 3px; float: left" />
            <div style="height: 25px; line-height: 25px; color: #000; margin-left: 10px; font-size: 28px; font-weight: bold; float: left; flex-shrink: 0">商品图片</div>
            <div style="height: 1px; background-color: rgb(199, 199, 199); float: left; flex: 0 0 1; width: 100%; margin-left: 30px; margin-top: 12px" />
          </div>
          <div style="width: 600px; margin: 0 auto; text-indent: 2em; line-height: 6em; margin-top: 40px; color: #000" >
            <a-list item-layout="horizontal" :data-source="imageList">
              <a-list-item slot="renderItem" slot-scope="item">
                <img :src="item" width="100%">
              </a-list-item>
            </a-list>
          </div>
        </div>
      </a-modal>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { Modal } from 'ant-design-vue'
import { Ellipsis, STable } from '@/components'
import { delGoods, getGoodsList } from '@/api/shop'
import { checkChinese } from '@/utils/checkStr'

const columns = [
    {
      title: '物品名称',
      dataIndex: 'goodsName',
      align: 'left',
      scopedSlots: { customRender: 'goodsName' }
    },
    {
      title: '物品价格',
      dataIndex: 'goodsPrice',
      align: 'center',
      scopedSlots: { customRender: 'goodsPrice' }
    },
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
  const goodsStatusList = [
    {
      id: 1,
      name: '未上架'
    },
    {
      id: 2,
      name: '已上架'
    },
    {
      id: 3,
      name: '已售'
    },
    {
      id: 4,
      name: '待典当'
    },
    {
      id: 5,
      name: '待出售'
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
    },
    5: {
      color: '#FAAD14',
      text: '待出售'
    }
  }
  export default {
    name: 'GoodsIndex',
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
      return {
        columns,
        goodsStatusList,
        // create model
        visible: false,
        confirmLoading: false,
        // 高级搜索 展开/关闭
        advanced: false,
        // 查询参数
        queryParam: {
          goodsName: null,
          stateOn: undefined
        },
        // 加载数据方法 必须为 Promise 对象
        loadData: parameter => {
          const requestParameters = Object.assign({}, parameter, this.queryParam)
          return getGoodsList(requestParameters)
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
        detailShow: false,
        goodsForm: {
          goodsId: null,
          goodsName: '',
          goodsImgId: undefined,
          images: [],
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
        imageList: [],
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
      InitImages (obj) {
        this.imageList = obj.images
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
      AboutImage (obj) {
        this.InitImages(obj)
        this.detailShow = true
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
