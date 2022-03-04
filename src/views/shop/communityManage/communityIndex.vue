<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-operator">
        <a-button v-action:add type="primary" icon="plus" @click="handleAccount(0, null)">新增公益展示</a-button>
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
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-action:delete style="margin-left: 10px" @click="del(record)">删除</a>
          </template>
        </span>
      </s-table>
      <a-modal
        v-model="handleGoods"
        :maskClosable="false"
        :confirmLoading="handleLoading"
        :title="modalTitle"
        ok-text="确认"
        cancel-text="取消"
        width="600px"
        @ok="submitForm">
        <a-form-model
          ref="communityForm"
          v-if="modalTitle!=='查看'"
          :model="communityForm"
          :rules="goodsRules"
          :label-col="labelCol"
          :wrapper-col="wrapperCol">
          <a-form-model-item :labelAlign="'right'" label="图片：" prop="showImgId">
            <a-upload
              :show-upload-list="false"
              :action="''"
              :beforeUpload="beforeUpload"
              :disabled="formDisabled"
              accept=".jpeg,.gif,.png,.jpg"
              name="file"
              list-type="picture-card"
              class="avatar-uploader">
              <img v-if="communityForm.showImgId&&communityForm.imageUrl.length > 0" :src="communityForm.imageUrl" style="width: 80px; height: 80px" alt="avatar" />
              <div v-else>
                <a-icon :type="imgLoading ? 'loading' : 'plus'" />
                <div class="ant-upload-text">点击上传</div>
              </div>
            </a-upload>
            <span>建议上传240*293尺寸的png、jpg图片</span>
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" label="物品描述：" prop="showName">
            <a-textarea v-model="communityForm.showName" :disabled="formDisabled" placeholder="物品描述" :auto-size="{ minRows: 3, maxRows: 8 }"/>
          </a-form-model-item>
        </a-form-model>
      </a-modal>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { Modal } from 'ant-design-vue'
import { Ellipsis, STable } from '@/components'
import { addCommunity, delCommunity, getCommunityList, uploadFile } from '@/api/shop'

const columns = [
    {
      title: '物品照片',
      dataIndex: 'imageUrl',
      align: 'center',
      scopedSlots: { customRender: 'imageUrl' }
    },
    {
      title: '物品介绍',
      dataIndex: 'showName',
      align: 'center',
      scopedSlots: { customRender: 'showName' }
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
    0: {
      color: '#FA541C',
      text: '未上架'
    },
    1: {
      color: '#FAAD14',
      text: '已上架'
    },
    2: {
      color: '#52C41A',
      text: '已售'
    }
  }
  export default {
    name: 'CommunityIndex',
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
        queryParam: {
          keyword: null
        },
        // 加载数据方法 必须为 Promise 对象
        loadData: parameter => {
          const requestParameters = Object.assign({}, parameter, this.queryParam)
          return getCommunityList(requestParameters)
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
        communityForm: {
          showId: null,
          showImgId: null,
          imageUrl: '',
          showName: ''
        },
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
      reloadData () {
        this.$refs.table.refresh(true)
      },
      handleAccount (type, obj) {
        this.handleGoods = true
        this.$nextTick(() => {
          this.$nextTick(() => {
            this.$refs.communityForm.resetFields()
            if (type === 0) { // 新增
              this.modalTitle = '新增'
              this.communityForm = {
                showId: null,
                imageUrl: '',
                showImgId: '',
                showName: ''
              }
            }
          })
        })
      },
      submitForm () {
        this.handleLoading = true
        this.$refs.communityForm.validate(valid => {
          if (valid) {
            if (this.modalTitle === '新增') {
              addCommunity(this.communityForm).then(() => {
                this.reloadData()
                this.handleGoods = false
                this.handleLoading = false
              }).catch(() => {
                this.handleLoading = false
              })
              this.handleLoading = false
            } else {
              this.reloadData()
              this.handleGoods = false
              this.handleLoading = false
            }
          } else {
            this.handleLoading = false
          }
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
      beforeUpload (file, fileList) {
        this.imgLoading = true
        const param = new FormData()
        param.append('multipartFile', file)
        uploadFile(param).then(response => {
          this.communityForm.imageUrl = response.data.url
          this.communityForm.showImgId = response.data.imageId
          this.imgLoading = false
        })
      },
      del (obj) {
        Modal.confirm({
          title: '删除',
          content: '确定删除？',
          onOk: () => {
            delCommunity({
              communityId: obj.goodsId
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
