import Vue from 'vue';
import VueRouter from 'vue-router';
import App from './App';
import router from './router';
import ViewUI from 'view-design';
import store from './store'
import 'view-design/dist/styles/iview.css';
import './theme/index.less'


Vue.use(VueRouter);
Vue.use(ViewUI);


new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
});
