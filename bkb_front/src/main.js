import Vue from 'vue';
import VueRouter from 'vue-router';
import App from './App';
import router from './router';
import ViewUI from 'view-design';
import store from './store'
import 'view-design/dist/styles/iview.css';
import './theme/index.less'
import BaiduMap from 'vue-baidu-map'

Vue.use(VueRouter);
Vue.use(ViewUI);
Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: 'u3RkCNa34vGyLCjW9CnwwG05H5r7xmfC'
});

new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
});
