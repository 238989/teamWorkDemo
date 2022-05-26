import Vue from 'vue'
import Router from 'vue-router'

/* Layout */
import Layout from '@/layout'

Vue.use(Router)

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: '爱心公社',
      component: () => import('@/views/home/index'),
      meta: { title: '爱心公社', icon: 'dashboard' }
    }]
  },

  {
    path: '/menu1',
    component: Layout,
    redirect: '/menu1/menu1-1',
    name: '紧急救助信息管理',
    meta: { title: '紧急救助信息管理', icon: 'dashboard2' },
    children: [
      {
        path: 'menu1-1',
        name: '发布审核',
        component: () => import('@/views/menu1/menu1-1/index'),
        meta: { title: '发布审核', icon: 'map' }
      },
      {
        path: 'menu1-2',
        name: '内容管理',
        component: () => import('@/views/menu1/menu1-2/index'),
        meta: { title: '内容管理', icon: 'graph' }
      },
      {
        path: 'menu1-3',
        name: '数据统计',
        component: () => import('@/views/menu1/menu1-3/index'),
        meta: { title: '数据统计', icon: 'chart' },
        children: [
          {
            path: 'menu1-3-1',
            component: () => import('@/views/menu1/menu1-3/menu1-3-1/index'),
            name: 'Mue1-3-1',
            meta: { title: 'Mue1-3-1', icon: 'table' }
          },
          {
            path: 'menu1-3-2',
            component: () => import('@/views/menu1/menu1-3/menu1-3-2/index'),
            name: 'Mue1-3-2',
            meta: { title: 'Mue1-3-2', icon: 'dynamic' }
          }
        ]
      }
    ]
  },
  {
    path: '/menu2',
    component: Layout,
    redirect: '/menu2/menu2-1',
    name: '日常公益信息管理',
    meta: { title: '日常公益信息管理', icon: 'dashboard2' },
    children: [
      {
        path: 'menu2-1',
        name: '发布审核',
        component: () => import('@/views/menu2/menu2-1/index'),
        meta: { title: '发布审核', icon: 'map' }
      },
      {
        path: 'menu2-2',
        name: '内容管理',
        component: () => import('@/views/menu2/menu2-2/index'),
        meta: { title: '内容管理', icon: 'car' }
      },
      {
        path: 'menu2-3',
        name: '数据统计',
        component: () => import('@/views/menu2/menu2-3/index'),
        meta: { title: '数据统计', icon: 'chart' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
