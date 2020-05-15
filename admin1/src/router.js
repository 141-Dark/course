import Vue from 'vue'
import Router from "vue-router"
import Login from './views/login.vue'
import Admin from './views/admin'
Vue.use(Router)
 export default new Router({
   mode:'hash',
   base:process.env.BABEL_ENV,
   routes:[{
     //不管在地址栏中输入什么都会对应login
     path:'*',
     redirect:"/login"
   },{
        //配置路由，对应加载组件Login
        path: '/login',
        component:Login
   },{
       //配置路由，加载对应的组件admin
       path:'/admin',
       component:Admin
   }]
 })