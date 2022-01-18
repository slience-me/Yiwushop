// eslint-disable-next-line
import { BasicLayout, UserLayout } from '@/layouts'

const RouteView = {
  name: 'RouteView',
  render: (h) => h('router-view')
}

export const asyncRouterMap = [

  {
    path: '/',
    name: 'Index',
    component: BasicLayout,
    meta: { title: '大学生易物平台后台管理系统' },
    redirect: '/shop',
    children: [
      {
        path: '/shop',
        name: 'DashboardIndex',
        meta: { title: '首页', keepAlive: false, icon: 'home', permission: [] },
        component: () => import('@/views/shop/index'),
        hideChildrenInMenu: true,
        children: []
      },
      {
        path: '/shop/myCenter',
        name: 'PersonalIndex',
        meta: { title: '个人设置', keepAlive: false, icon: '' },
        component: () => import('@/views/shop/myCenter/index'),
        hideChildrenInMenu: true,
        hidden: true
      },
      {
        path: '/shop/orders',
        name: 'OrdersIndex',
        meta: { title: '订单管理', keepAlive: false, icon: 'profile', permission: ['OrdersIndex'] },
        component: () => import('@/views/shop/ordersManage/ordersIndex'),
        hideChildrenInMenu: true,
        children: []
      },
      {
        path: '/shop/auctions',
        name: 'AuctionsIndex',
        meta: { title: '竞拍场次管理', keepAlive: false, icon: 'profile', permission: ['AuctionsIndex'] },
        component: () => import('@/views/shop/auctionsManage/auctionsIndex'),
        hideChildrenInMenu: true,
        children: []
      },
      {
        path: '/shop/category',
        name: 'CategoryIndex',
        meta: { title: '物品类型管理', keepAlive: false, icon: 'profile', permission: ['CategoryIndex'] },
        component: () => import('@/views/shop/categoryManage/categoryIndex'),
        hideChildrenInMenu: true,
        children: []
      },
      {
        path: '/shop/goodsManage',
        name: 'GoodsManageIndex',
        meta: { title: '商品管理', keepAlive: false, icon: 'tags', permission: ['GoodsIndex', 'GoodsDoneIndex', 'GoodsOnIndex', 'GoodsNoIndex'] },
        component: RouteView,
        hideChildrenInMenu: false,
        redirect: '/shop',
        children: [
          {
            path: '/shop/goodsManage/goodsIndex',
            name: 'GoodsIndex',
            component: () => import('@/views/shop/goodsManage/goodsIndex'),
            meta: { title: '商品一览', keepAlive: false, hidden: true, permission: ['GoodsIndex'] }
          },
          {
            path: '/shop/goodsManage/goodsNoIndex',
            name: 'GoodsNoIndex',
            component: () => import('@/views/shop/goodsManage/goodsNoIndex'),
            meta: { title: '未上架商品', keepAlive: false, hidden: true, permission: ['GoodsNoIndex'] }
          },
          {
            path: '/shop/goodsManage/goodsDoneIndex',
            name: 'GoodsDoneIndex',
            component: () => import('@/views/shop/goodsManage/goodsDoneIndex'),
            meta: { title: '已售商品', keepAlive: false, hidden: true, permission: ['GoodsDoneIndex'] }
          },
          {
            path: '/shop/goodsManage/goodsOnIndex',
            name: 'GoodsOnIndex',
            component: () => import('@/views/shop/goodsManage/goodsOnIndex'),
            meta: { title: '上架商品', keepAlive: false, hidden: true, permission: ['GoodsOnIndex'] }
          }
        ]
      },
      {
        path: '/shop/complaint',
        name: 'ComplaintIndex',
        meta: { title: '投诉管理', keepAlive: false, icon: 'tags', permission: ['ComplaintIndex'] },
        component: () => import('@/views/shop/complaintManage/complaintIndex'),
        hideChildrenInMenu: true,
        children: []
      },
      {
        path: '/shop/member',
        name: 'MemberIndex',
        meta: { title: '成员管理', keepAlive: false, icon: 'team', permission: ['MemberIndex'] },
        component: () => import('@/views/shop/memManage/memIndex'),
        hideChildrenInMenu: true,
        children: []
      },
      {
        path: '/shop/adminManage',
        name: 'ManageIndex',
        meta: { title: '管理设置', keepAlive: false, icon: 'setting', permission: ['AdminIndex', 'RoleIndex'] },
        component: RouteView,
        hideChildrenInMenu: false,
        redirect: '/shop',
        children: [
          {
            path: '/shop/adminManage/adminIndex',
            name: 'AdminIndex',
            component: () => import('@/views/shop/adminManage/adminIndex'),
            meta: { title: '管理员管理', keepAlive: false, hidden: true, permission: ['AdminIndex'] }
          },
          {
            path: '/shop/adminManage/roleIndex',
            name: 'RoleIndex',
            component: () => import('@/views/shop/adminManage/roleIndex'),
            meta: { title: '角色管理', keepAlive: false, hidden: true, permission: ['RoleIndex'] }
          }
        ]
      }
    ]
  },
  {
    path: '*', redirect: '/404', hidden: true
  }
]

/**
 * 基础路由
 * @type { *[] }
 */
export const constantRouterMap = [
  {
    path: '/user',
    component: UserLayout,
    redirect: '/user/login',
    hidden: true,
    children: [
      {
        path: 'login',
        name: 'login',
        component: () => import(/* webpackChunkName: "user" */ '@/views/user/Login')
      },
      {
        path: 'register',
        name: 'register',
        component: () => import(/* webpackChunkName: "user" */ '@/views/user/Register')
      },
      {
        path: 'register-result',
        name: 'registerResult',
        component: () => import(/* webpackChunkName: "user" */ '@/views/user/RegisterResult')
      },
      {
        path: 'recover',
        name: 'recover',
        component: undefined
      }
    ]
  },

  {
    path: '/404',
    component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404')
  }

]
