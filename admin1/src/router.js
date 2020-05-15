import Vue from 'vue'
import Router from "vue-router"
import Login from './views/login.vue'

Vue.use(Router)
 export default new Router({
   mode:'hash',
   base:process.env.BABEL_ENV,
   routes:[{
     //不管在地址栏中输入什么都会对应login
     path:'*',
     redirect:"/login"
   },{
     //对应加载组件Login
     path: '/login',
     component:Login
   }]
 })