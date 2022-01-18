<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="">
                <a-input v-model="queryParam.keyword" placeholder="角色名称" allowClear/>
              </a-form-item>
            </a-col>
            <a-col :md="!advanced && 6 || 24" :sm="24">
              <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
                <a-button style="margin-left: 8px" @click="() => this.queryParam = {}">重置</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <div class="table-operator">
        <a-button v-action:add type="primary" icon="plus" @click="handleAction(0, null)">新增</a-button>
      </div>
      <s-table
        ref="table"
        size="default"
        rowKey="roleId"
        :columns="columns"
        :data="loadData"
        :alert="false"
        showPagination="auto">
        <span slot="roleName" slot-scope="text">
          <ellipsis :length="20" tooltip>{{ text }}</ellipsis>
        </span>
        <span slot="info" slot-scope="text, record">
          <!--<ellipsis :length="100" tooltip>{{ text }}</ellipsis>-->
          <a-tree
            :replaceFields="{ title: 'menuTitle', key: 'menuId' }"
            :auto-expand-parent="true"
            :defaultExpandAll="false"
            :tree-data="record.menus"/>
        </span>
        <span slot="action" slot-scope="text, record">
          <template>
            <a v-action:edit @click="handleAction(1, record)">编辑</a>
            <a v-action:delete style="margin-left: 10px" @click="del(1, record)">删除</a>
          </template>
        </span>
      </s-table>
      <a-modal
        v-model="handleRole"
        :maskClosable="false"
        :confirmLoading="roleLoading"
        :title="modalTitle"
        ok-text="确认"
        cancel-text="取消"
        width="600px"
        @ok="submitForm">
        <a-form-model
          ref="roleForm"
          :model="roleForm"
          :rules="roleRules"
          :label-col="labelCol"
          :wrapper-col="wrapperCol">
          <a-form-model-item :labelAlign="'right'" label="角色名称：" prop="roleName">
            <a-input v-model="roleForm.roleName" allowClear placeholder="请输入角色名称" style="width: 100%" />
          </a-form-model-item>
          <a-form-model-item :labelAlign="'right'" allowClear label="角色权限：" prop="menuIds">
            <a-tree
              v-model="roleForm.menuIds"
              checkable
              :replaceFields="{ title: 'menuTitle', key: 'menuId' }"
              :auto-expand-parent="true"
              :defaultExpandAll="true"
              :selected-keys="roleForm.menuIds"
              :tree-data="menuList"/>
          </a-form-model-item>
        </a-form-model>
      </a-modal>
    </a-card>
  </page-header-wrapper>
</template>

<script>
  import { STable, Ellipsis } from '@/components'
  import { getRoleList, delRole, getMenu, addRole, editRole } from '@/api/shop'
  import { Modal } from 'ant-design-vue'
  const columns = [
    {
      title: '角色名称',
      dataIndex: 'roleName',
      scopedSlots: { customRender: 'roleName' }
    },
    {
      title: '拥有权限',
      dataIndex: 'info',
      scopedSlots: { customRender: 'info' }
    },
    {
      title: '操作',
      dataIndex: 'action',
      width: '120px',
      scopedSlots: { customRender: 'action' }
    }
  ]
  export default {
    name: 'RoleIndex',
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
        queryParam: {},
        // 加载数据方法 必须为 Promise 对象
        loadData: parameter => {
          const requestParameters = Object.assign({}, parameter, this.queryParam)
          return getRoleList(requestParameters)
            .then(res => {
              res.data.list.map(item => {
                item.info = ''
                item.menus.map(menuItem => {
                  item.info += '[' + menuItem.menuTitle + '] '
                  menuItem.children.map(actionItem => {
                    if (actionItem.children && actionItem.children.length > 0) {
                      item.info += '[' + menuItem.menuTitle + '-' + actionItem.menuTitle + '] '
                      actionItem.children.map(realAction => {
                        item.info += realAction.menuTitle + ' '
                      })
                    } else {
                      item.info += actionItem.menuTitle + ' '
                    }
                  })
                })
              })
              return res.data
            })
        },
        selectedRowKeys: [],
        selectedRows: [],
        handleRole: false,
        roleLoading: false,
        modalTitle: '新增',
        roleForm: {
          roleId: null,
          roleName: '',
          menuIds: []
        },
        roleRules: {
          roleName: [
            { required: true, message: '请输入角色名称', trigger: 'blur' },
            { min: 1, max: 20, message: '角色名称长度为1-20位' }
          ],
          menuIds: [
            { required: true, message: '请选择角色权限', trigger: 'change' }
          ]
        },
        labelCol: { span: 6 },
        wrapperCol: { span: 12 },
        menuList: [],
        menuIds: [],
        selectedMenuIds: []
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
      getMenu().then(res => {
        this.menuList = res.data
      })
    },
    methods: {
      handleAction (type, obj) {
        this.handleRole = true
        this.$nextTick(() => {
          this.$nextTick(() => {
            this.$refs.roleForm.resetFields()
            if (type === 0) {
              this.modalTitle = '新增'
              this.roleForm = {
                roleId: null,
                roleName: '',
                menuIds: []
              }
            } else if (type === 1) {
              this.modalTitle = '编辑'
              this.selectedMenuIds = []
              obj.menus.map(item => {
                this.handleMenuId(item)
              })
              this.roleForm = {
                roleId: obj.roleId,
                roleName: obj.roleName,
                menuIds: this.selectedMenuIds
              }
            }
          })
        })
      },
      handleMenuId (item) {
        if (item.children && item.children.length > 0) {
          item.children.map(menu => {
            this.handleMenuId(menu)
          })
        } else {
          this.selectedMenuIds.push(item.menuId)
        }
      },
      onSelectChange (selectedRowKeys, selectedRows) {
        this.selectedRowKeys = selectedRowKeys
        this.selectedRows = selectedRows
      },
      resetSearchForm () {
        this.queryParam = {}
      },
      del (type, obj) {
        let delIds = ''
        if (type === 0) { // 多选删除
          if (this.selectedRowKeys.length === 0) {
            this.$message.warning('请选择要删除的角色')
            return
          }
          delIds = this.selectedRowKeys.join(',')
        } else if (type === 1) {
          delIds = obj.roleId
        }
        Modal.confirm({
          title: '删除确认',
          content: '删除后相关角色管理员将无权限，需重新分配',
          onOk: () => {
            delRole({
              roleId: delIds
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
      submitForm () {
        this.menuIds = []
        this.roleLoading = true
        this.$refs.roleForm.validate(valid => {
          if (valid) {
            this.menuIds = Object.assign([], this.roleForm.menuIds)
            this.roleForm.menuIds.map(menuId => {
              this.handleAllMenus(menuId, this.menuList)
            })
            const realMenuIds = Object.assign([], this.menuIds)
            realMenuIds.map(menuId => {
              this.handleAllMenus(menuId, this.menuList)
            })
            if (this.modalTitle === '新增') {
              addRole({
                roleName: this.roleForm.roleName,
                menuIds: this.menuIds
              }).then(() => {
                this.$refs.table.refresh(true)
                this.handleRole = false
                this.roleLoading = false
              }).catch(() => {
                this.roleLoading = false
              })
            } else if (this.modalTitle === '编辑') {
              editRole({
                roleId: this.roleForm.roleId,
                roleName: this.roleForm.roleName,
                menuIds: this.menuIds
              }).then(res => {
                this.$refs.table.refresh(true)
                this.handleRole = false
                this.roleLoading = false
                if (res.status === 0) {
                  if (this.roleForm.roleId === parseInt(sessionStorage.getItem('roleId'))) {
                    window.location.reload()
                  }
                }
              }).catch(() => {
                this.roleLoading = false
              })
            }
          } else {
            this.roleLoading = false
          }
        })
      },
      handleAllMenus (menuId, allMenus) { // 单独勾选一个子级需要把父级push进去
        allMenus.map(menuItem => {
          this.handleRealMenuId(menuId, menuItem)
          if (menuItem.children && menuItem.children.length > 0) {
            this.handleAllMenus(menuId, menuItem.children)
          }
        })
      },
      handleRealMenuId (menuId, menuItem) {
        if (menuItem.menuId === menuId) {
          if (menuItem.menuParentId) {
            if (this.menuIds.indexOf(menuItem.menuParentId) <= -1) {
              this.menuIds.push(menuItem.menuParentId)
            }
          }
        }
      }
    }
  }
</script>

<style scoped>

</style>
