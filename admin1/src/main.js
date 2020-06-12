import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import filter from './filter/filter'

Vue.config.productionTip = false
Vue.prototype.ajax=axios
//增加全局的过滤器
Object.keys(filter).forEach(key=>{
    Vue.filter(key,filter[key])
})

new Vue({
    router,
  render: h => h(App),
}).$mount('#app')
//这是固定写法
console.log("当前环境是：",process.env.NODE_ENV)