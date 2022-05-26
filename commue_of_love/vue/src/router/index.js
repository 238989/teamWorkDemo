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
    path: '/uh&rs',
    component: Layout,
    redirect: '/uh&rs/uh/',
    name: '紧急救助信息管理',
    meta: { title: '紧急救助信息管理', icon: 'dashboard2' },
    children: [
      {
        path: '/uh',
        name: '求助信息',
        component: () => import('@/views/uh&rs/uh/index'),
        meta: { title: '求助信息', icon: 'map' },
        children: [
          {
            path: 'audit',
            component: () => import('@/views/uh&rs/uh/audit'),
            name: '发布审核',
            meta: { title: '发布审核', icon: 'table' }
          },
          {
            path: 'modify',
            component: () => import('@/views/uh&rs/uh/modify'),
            name: '内容管理',
            meta: { title: '内容管理', icon: 'dynamic' }
          },
          {
            path: 'stats',
            component: () => import('@/views/uh&rs/uh/stats'),
            name: '数据统计',
            meta: { title: '数据统计', icon: 'dynamic' }
          }
        ]
      },
      {
        path: 'rs',
        name: '互助信息',
        component: () => import('@/views/uh&rs/rs/index'),
        meta: { title: '互助信息', icon: 'graph' },
        children: [
          {
            path: 'audit',
            component: () => import('@/views/uh&rs/rs/audit'),
            name: '发布审核',
            meta: { title: '发布审核', icon: 'table' }
          },
          {
            path: 'modify',
            component: () => import('@/views/uh&rs/rs/modify'),
            name: '内容管理',
            meta: { title: '内容管理', icon: 'dynamic' }
          },
          {
            path: 'stats',
            component: () => import('@/views/uh&rs/rs/stats'),
            name: '数据统计',
            meta: { title: '数据统计', icon: 'dynamic' }
          }
        ]
      }
    ]
  }
  //,
  // {
  //   path: '/menu2',
  //   component: Layout,
  //   redirect: '/menu2/menu2-1',
  //   name: '日常公益信息管理',
  //   meta: { title: '日常公益信息管理', icon: 'dashboard2' },
  //   children: [
  //     {
  //       path: 'menu2-1',
  //       name: '募捐项目',
  //       component: () => import('@/views/menu2/menu2-1/menu2-1-1'),
  //       meta: { title: '募捐项目', icon: 'map' },
  //       children: [
  //         {
  //           path: 'menu2-1-1',
  //           component: () => import('@/views/menu2/menu2-1/menu2-1-1/index'),
  //           name: '发布审核',
  //           meta: { title: '发布审核', icon: 'table' }
  //         },
  //         {
  //           path: 'menu2-1-2',
  //           component: () => import('@/views/menu2/menu2-1/menu2-1-2/index'),
  //           name: '内容管理',
  //           meta: { title: '内容管理', icon: 'dynamic' }
  //         },
  //         {
  //           path: 'menu2-1-3',
  //           component: () => import('@/views/menu2/menu2-1/menu2-1-3/index'),
  //           name: '数据统计',
  //           meta: { title: '数据统计', icon: 'dynamic' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2-2',
  //       name: '志愿招募',
  //       component: () => import('@/views/menu2/menu2-2/menu2-2-1'),
  //       meta: { title: '志愿招募', icon: 'graph' },
  //       children: [
  //         {
  //           path: 'menu1-2-1',
  //           component: () => import('@/views/menu2/menu2-2/menu2-2-1/index'),
  //           name: '发布审核',
  //           meta: { title: '发布审核', icon: 'table' }
  //         },
  //         {
  //           path: 'menu1-2-2',
  //           component: () => import('@/views/menu2/menu2-2/menu2-2-2/index'),
  //           name: '内容管理',
  //           meta: { title: '内容管理', icon: 'dynamic' }
  //         },
  //         {
  //           path: 'menu2-2-3',
  //           component: () => import('@/views/menu2/menu2-2/menu2-2-3/index'),
  //           name: '数据统计',
  //           meta: { title: '数据统计', icon: 'dynamic' }
  //         }
  //       ]
  //     }
  //   ]
  // },
  // // 404 page must be placed at the end !!!
  // { path: '*', redirect: '/404', hidden: true }
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
