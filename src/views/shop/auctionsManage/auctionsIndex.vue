<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <s-table
        ref="table"
        size="default"
        rowKey="auctionsId"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <span slot="auctionsName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-action:add style="margin-left: 10px" @click="detailAction(record)">查看</a>
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
            <div style="height: 25px; line-height: 25px; color: #000; margin-left: 10px; font-size: 28px; font-weight: bold; float: left; flex-shrink: 0">拍卖过程</div>
            <div style="height: 1px; background-color: rgb(199, 199, 199); float: left; flex: 0 0 1; width: 100%; margin-left: 30px; margin-top: 12px" />
          </div>
          <div style="width: 600px; margin: 0 auto; text-indent: 2em; line-height: 6em; margin-top: 40px; color: #000" >
            <a-list item-layout="horizontal" :data-source="queryScheduleList">
              <a-list-item slot="renderItem" slot-scope="item, index">
                <a-list-item-meta :description="(index+1) + '.     ' +item.userName + ' 出价 ' + item.auctionSchedulePrice + '元'">
                  <a-avatar
                    slot="avatar"
                    :src="item.userAvatarUrl"
                  />
                </a-list-item-meta>
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
import { delAuctions, getAuctionsList, getScheduleList } from '@/api/shop'
import { checkChinese } from '@/utils/checkStr'
const columns = [
    {
      title: '竞拍场次名称',
      dataIndex: 'auctionsName',
      scopedSlots: { customRender: 'auctionsName' }
    },
    {
      title: '商品名称',
      dataIndex: 'goodName',
      align: 'center',
      scopedSlots: { customRender: 'goodName' }
    },
    {
      title: '开始时间',
      dataIndex: 'startTime',
      align: 'center',
      scopedSlots: { customRender: 'startTime' }
    },
    {
      title: '结束时间',
      dataIndex: 'endTime',
      align: 'center',
      scopedSlots: { customRender: 'endTime' }
    },
    {
      title: '起拍价',
      dataIndex: 'startingPrice',
      align: 'center',
      scopedSlots: { customRender: 'startingPrice' }
    },
    {
      title: '一口价',
      dataIndex: 'fixedPrice',
      align: 'center',
      scopedSlots: { customRender: 'fixedPrice' }
    },
    {
      title: '当前价格',
      dataIndex: 'presentPrice',
      align: 'center',
      scopedSlots: { customRender: 'presentPrice' }
    },
    {
      title: '当前出价人',
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
  export default {
    name: 'AuctionsIndex',
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
          return getAuctionsList(requestParameters)
            .then(res => {
              return res.data
            })
        },
        selectedRowKeys: [],
        selectedRows: [],
        modalTitle: '新增',
        handleAuctions: false,
        handleLoading: false,
        goodsList: [],
        auctionsForm: {
          auctionsId: undefined,
          auctionsName: '',
          goodsId: undefined,
          goodName: '',
          startTime: '',
          endTime: ''
        },
        userAvatarUrl: '',
        auctionSchedulePrice: '',
        localTime: null,
        auctionsRules: {
          auctionsName: [
            { required: true, message: '请输入竞拍场次名称', trigger: 'blur' },
            { min: 1, max: 20, message: '竞拍场次名称长度为1-20位', trigger: 'blur' },
            { validator: validateAccount, trigger: 'blur' }
          ]
        },
        labelCol: { span: 6 },
        wrapperCol: { span: 12 },
        queryAuctionsList: [],
        queryAuctionsTypeList: [],
        queryGoodsList: [],
        queryScheduleList: [],
        detailShow: false,
        infoObj: null
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
      InitAuctionSchedule (goodsId) {
        getScheduleList({
          pageNo: 1,
          pageSize: 8888,
          goodsId: goodsId
        }).then(res => {
          this.queryScheduleList = res.data.list
        })
      },
      detailAction (obj) {
        this.InitAuctionSchedule(obj.goodsId)
        this.infoObj = obj
        this.detailShow = true
      },
      handleAccount (type, obj) {
        this.handleAuctions = true
        this.$nextTick(() => {
          this.$nextTick(() => {
            if (type === 2) { // 编辑
              this.modalTitle = '查看拍卖情况'
              this.auctionsForm = {
                auctionsId: obj.auctionsId,
                auctionsName: obj.auctionsName,
                goodsId: obj.goodsId,
                goodName: obj.goodName,
                startTime: obj.startTime,
                endTime: obj.endTime
              }
            }
          })
        })
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
      del (obj) {
        Modal.confirm({
          title: '删除',
          content: '确定删除？',
          onOk: () => {
            delAuctions({
              auctionsId: obj.auctionsId
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
