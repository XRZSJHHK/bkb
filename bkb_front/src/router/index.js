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
    },
    {
      path: '/recommend',
      name: '/recommend',
      components: {
        content: resolve => require(['../view/recommend'], resolve),
      }
    },
    {
      path: '/helper',
      name: '/helper',
      components: {
        content: resolve => require(['../view/helper'], resolve),
      }
    }
  ]
})
