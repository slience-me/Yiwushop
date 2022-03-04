<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <div class="table-operator">
          <a-button v-action:add type="primary" icon="plus" @click="handleAccount(0, null)">新增上架商品</a-button>
        </div>
      </div>
      <s-table
        ref="table"
        size="default"
        rowKey="pawnId"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <span slot="pawnName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-action:delete style="margin-left: 10px" @click="del(record)">删除</a>
          </template>
        </span>
      </s-table>
      <!--表单区域-调整-->
      <a-modal
        v-model="handlePawn"
        :maskClosable="false"
        :confirmLoading="handleLoading"
        :title="modalTitle"
        ok-text="确认"
        cancel-text="取消"
        width="600px"
        @ok="submitForm">
        <a-form-model
          ref="pawnForm"
          :model="pawnForm"
          :rules="pawnRules"
          :label-col="labelCol"
          :wrapper-col="wrapperCol">
          <a-form-model-item :labelAlign="'right'" label="典当场次名：" prop="pawnName">
            <a-input v-model="pawnForm.pawnName" placeholder="请输入典当场次名" style="width: 100%" allowClear />
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="商品id：" prop="goodsId">
            <a-select v-model="pawnForm.goodsId" placeholder="商品id" allowClear>
              <a-select-option v-for="(item, index) in queryGoodsList" :key="'queryGoodsList' + index" :value="item.goodsId">{{ item.goodsName }}</a-select-option>
            </a-select>
          </a-form-model-item>
          <a-form-model-item
            :labelAlign="'right'"
            label="开始时间："
            prop="startTime"
            allowClear
            style="width: 100%">
            <a-input v-model="pawnForm.startTime" placeholder="开始时间" />
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="结束时间：" prop="endTime" style="width: 100%" allowClear>
            <a-input v-model="pawnForm.endTime" placeholder="结束时间"/>
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="获取当前时间：" prop="endTime" style="width: 100%" allowClear>
            <a-input v-model="localTime" placeholder="获取当前时间"/><a-button @click="getTime">点击获取当前时间</a-button>
          </a-form-model-item>
        </a-form-model>
      </a-modal>
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
          <div style="width: 600px; margin: 0 auto; text-indent: 2em; line-height: 1.5em; margin-top: 20px; color: #000" >
            <a-form-model
              ref="pawnForm"
              :model="pawnForm"
              :rules="pawnRules"
              :label-col="labelCol"
              :wrapper-col="wrapperCol">
              <a-list item-layout="horizontal" :data-source="queryScheduleList">
                <a-list-item slot="renderItem" slot-scope="item, index">
                  <a-list-item-meta>
                    <a slot="title" href="javascript:void(0);" style="font-size: 20px;">序号:{{ index+1 }}&emsp;&emsp;
                      商品名称:{{ item.goodsName }}&emsp;&emsp;出价:{{ item.pawnchedulePrice }}&emsp;&emsp;出价人: {{ item.userName }} </a>
                  </a-list-item-meta>
                </a-list-item>
              </a-list>
            </a-form-model>
          </div>
        </div>
      </a-modal>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { Modal } from 'ant-design-vue'
import { Ellipsis, STable } from '@/components'
import { addStateOnToPawn, delPawn, editPawn, getPawnList } from '@/api/shop'
import { checkChinese } from '@/utils/checkStr'
import moment from 'moment'

const columns = [
    {
      title: '典当场次名称',
      dataIndex: 'pawnName',
      scopedSlots: { customRender: 'pawnName' }
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
    name: 'PawnIndex',
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
          return getPawnList(requestParameters)
            .then(res => {
              return res.data
            })
        },
        selectedRowKeys: [],
        selectedRows: [],
        modalTitle: '新增',
        handlePawn: false,
        handleLoading: false,
        goodsList: [],
        pawnForm: {
          pawnId: undefined,
          pawnName: '',
          goodsId: undefined,
          goodName: '',
          startTime: '',
          endTime: ''
        },
        localTime: null,
        pawnRules: {
          pawnName: [
            { required: true, message: '请输入典当场次名称', trigger: 'blur' },
            { min: 1, max: 20, message: '典当场次名称长度为1-20位', trigger: 'blur' },
            { validator: validateAccount, trigger: 'blur' }
          ]
        },
        labelCol: { span: 6 },
        wrapperCol: { span: 12 },
        queryPawnList: [],
        queryPawnTypeList: [],
        queryGoodsList: [],
        queryScheduleList: [],
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
    created () {
    },
    methods: {
      detailAction (obj) {
        this.infoObj = obj
        this.detailShow = true
      },
      moment,
      getTime () {
        this.localTime = new Date()
        const now = new Date()
        const year = now.getFullYear()
        let month = now.getMonth() + 1
        let date = now.getDate()
        let hour = now.getHours()
        let minute = now.getMinutes()
        let second = now.getSeconds()
        if (month < 10) {
          month = '0' + month
        } if (date < 10) {
          date = '0' + date
        } if (hour < 10) {
          hour = '0' + hour
        } if (minute < 10) {
          minute = '0' + minute
        } if (second < 10) {
          second = '0' + second
        }
        this.localTime = year + '-' + month + '-' + date + ' ' + hour + ':' + minute + ':' + second
      },
      handleAccount (type, obj) {
        this.handlePawn = true
        this.$nextTick(() => {
          this.$nextTick(() => {
            this.$refs.pawnForm.resetFields()
            if (type === 0) { // 新增
              this.InitGoodsList()
              this.modalTitle = '新增'
              this.pawnForm = {
                pawnName: '',
                goodsId: undefined,
                startTime: '',
                endTime: ''
              }
            } else if (type === 1) { // 编辑
              this.modalTitle = '编辑'
              this.pawnForm = {
                pawnId: obj.pawnId,
                pawnName: obj.pawnName,
                goodsId: obj.goodsId,
                goodName: obj.goodName,
                startTime: obj.startTime,
                endTime: obj.endTime
              }
            } else if (type === 2) { // 编辑
              this.modalTitle = '查看拍卖情况'
              this.pawnForm = {
                pawnId: obj.pawnId,
                pawnName: obj.pawnName,
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
      submitForm () {
        this.handleLoading = true
        this.$refs.pawnForm.validate(valid => {
          if (valid) {
            if (this.modalTitle === '新增') {
              addStateOnToPawn(this.pawnForm).then(() => {
                Modal.success({
                  content: '新增物品类型成功，类型名：' + this.pawnForm.pawnName
                })
                this.$refs.table.refresh(true)
                this.handlePawn = false
                this.handleLoading = false
              }).catch(() => {
                this.handleLoading = false
              })
              this.handleLoading = false
            } else if (this.modalTitle === '编辑') {
              editPawn(this.pawnForm).then(() => {
                this.$refs.table.refresh(true)
                this.handlePawn = false
                this.handleLoading = false
              }).catch(() => {
                this.handleLoading = false
              })
              this.handleLoading = false
            }
          } else {
            this.handleLoading = false
          }
        })
      },
      del (obj) {
        Modal.confirm({
          title: '删除',
          content: '确定删除？',
          onOk: () => {
            delPawn({
              pawnId: obj.pawnId
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
