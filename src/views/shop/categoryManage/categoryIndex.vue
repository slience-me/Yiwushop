<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <div class="table-operator">
          <a-button v-action:add type="primary" icon="plus" @click="handleAccount(0, null)">新增</a-button>
        </div>
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="4" :sm="24">
              <a-form-item label="">
                <a-input v-model="queryParam.keyword" placeholder="名称模糊查询" allowClear/>
              </a-form-item>
            </a-col>
            <a-col :md="!advanced && 4 || 24" :sm="24">
              <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
                <a-button style="margin-left: 8px" @click="() => this.queryParam = { categoryName: undefined }">重置</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <s-table
        ref="table"
        size="default"
        rowKey="categoryId"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <div style="background:#ECECEC; padding:30px">
          <a-card title="" :bordered="false" style="width: 300px">
            <p>Card content</p>
          </a-card>
        </div>
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-action:edit style="margin-left: 10px" @click="handleAccount(1, record)">编辑</a>
            <a v-action:delete style="margin-left: 10px" @click="del(record)">删除</a>
          </template>
        </span>
      </s-table>
      <!--表单区域-调整-->
      <a-modal
        v-model="handleCategory"
        :maskClosable="false"
        :confirmLoading="handleLoading"
        :title="modalTitle"
        ok-text="确认"
        cancel-text="取消"
        width="600px"
        @ok="submitForm">
        <a-form-model
          ref="categoryForm"
          :model="categoryForm"
          :rules="categoryRules"
          :label-col="labelCol"
          :wrapper-col="wrapperCol">
          <a-form-model-item :labelAlign="'right'" label="物品类型：" prop="categoryName">
            <a-input v-model="categoryForm.categoryName" placeholder="请输入物品类型" style="width: 100%" allowClear />
          </a-form-model-item>
        </a-form-model>
      </a-modal>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { Modal } from 'ant-design-vue'
import { Ellipsis, STable } from '@/components'
import { addCategory, delCategory, editCategory, getCategoryList } from '@/api/shop'
import { checkChinese } from '@/utils/checkStr'

const columns = [
    {
      title: '物品类型名称',
      dataIndex: 'categoryName',
      scopedSlots: { customRender: 'categoryName' }
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
    name: 'CategoryIndex',
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
      // this.columns = columns
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
          return getCategoryList(requestParameters)
            .then(res => {
              return res.data
            })
        },
        selectedRowKeys: [],
        selectedRows: [],
        modalTitle: '新增',
        handleCategory: false,
        handleLoading: false,
        categoryForm: {
          categoryId: undefined,
          categoryName: ''
        },
        categoryRules: {
          categoryName: [
            { required: true, message: '请输入物品名称', trigger: 'blur' },
            { min: 1, max: 20, message: '物品名称长度为1-20位', trigger: 'blur' },
            { validator: validateAccount, trigger: 'blur' }
          ]
        },
        labelCol: { span: 6 },
        wrapperCol: { span: 12 },
        queryCategoryList: [],
        queryCategoryTypeList: []
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
      handleAccount (type, obj) {
        this.handleCategory = true
        this.$nextTick(() => {
          this.$nextTick(() => {
            this.$refs.categoryForm.resetFields()
            if (type === 0) { // 新增
              this.modalTitle = '新增'
              this.categoryForm = {
                categoryName: ''
              }
            } else if (type === 1) { // 编辑
              this.modalTitle = '编辑'
              this.categoryForm = {
                categoryId: obj.categoryId,
                categoryName: obj.categoryName
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
        this.$refs.categoryForm.validate(valid => {
          if (valid) {
            if (this.modalTitle === '新增') {
              addCategory(this.categoryForm).then(() => {
                Modal.success({
                  content: '新增物品类型成功，类型名：' + this.categoryForm.categoryName
                })
                this.$refs.table.refresh(true)
                this.handleCategory = false
                this.handleLoading = false
              }).catch(() => {
                this.handleLoading = false
              })
              this.handleLoading = false
            } else if (this.modalTitle === '编辑') {
              editCategory(this.categoryForm).then(() => {
                this.$refs.table.refresh(true)
                this.handleCategory = false
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
            delCategory({
              categoryId: obj.categoryId
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
