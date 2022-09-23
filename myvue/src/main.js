// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import Router from 'vue-router'
import router from './router/all-router'
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/index.js'
import axios from 'axios'


Vue.config.productionTip = false
Vue.use(router)
Vue.use(ElementUI)
Vue.prototype.$axios = axios
axios.defaults.baseURL = 'http://localhost:8070';
//axios.defaults.headers.common[contentType]='application/json';


const routerPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error=> error)
}


new Vue({
  el: '#app',
  router,
  render: h=>h(App)
})
