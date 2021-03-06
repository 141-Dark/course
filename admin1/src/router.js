import Vue from 'vue'
import Router from "vue-router"
import Login from './views/login.vue'
import Admin from './views/admin'
import Welcome from './views/admin/welcome'
import Chapter from './views/admin/chapter'
import Section from './views/admin/section'
import Course from './views/admin/course'
import Category from './views/admin/category'
import CourseContent from './views/admin/courseContent'
Vue.use(Router)
 export default new Router({
   mode:'history',
   base:process.env.BABEL_ENV,
   routes:[{
     //不管在地址栏中输入什么都会对应login
     path:'/',
     redirect:"/login"
   },{
        //配置路由，对应加载组件Login
        path: '/login',
        component:Login
   },{
       //配置路由，加载对应的组件admin
       path:'/',
       //为路由添加名称
       name:'admin',
       component:Admin,
       //添加admin的子路由,子路由中没有/
       children:[{
           path:'welcome',
           name:'welcome',
           component:Welcome
       },{
           path:'business/chapter',
           name:'business/chapter',
           component: Chapter
       },{
           path:'business/section',
           name:'business/section',
           component: Section
       },{
           path:'business/course',
           name:'business/course',
           component: Course
       },{
           path:'business/category',
           name:'business/category',
           component: Category
       },{
           path:'business/courseContent',
           name:'business/courseContent',
           component: CourseContent
       }]
   }]
 })