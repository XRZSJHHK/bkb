import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/index'
    },
    {
      path: '/index',
      name: 'index',
      components: {
        content: resolve => require(['../view/index'], resolve),
      },
    },
    {
      path: '/index/account',
      name: 'account',
      components: {
        content: resolve => require(['../view/account'], resolve),
      }
    },
    {
      path: '/index/monitor',
      name: 'monitor',
      components: {
        content: resolve => require(['../view/monitor'], resolve),
      }
    },
    {
      path: '/retrieve',
      name: 'retrieve',
      components: {
        content: resolve => require(['../view/retrieve'], resolve),
      }
    },
    {
      path: '/crawling',
      name: 'crawling',
      components: {
        content: resolve => require(['../view/crawling'], resolve),
      }
    },
    {
      path: '/school',
      name: 'school',
      components: {
        content: resolve => require(['../view/school'], resolve),
      }
    },
    {
      path: '/school/major',
      name: '/school/major',
      components: {
        content: resolve => require(['../view/major'], resolve),
      }
    }
  ]
})
