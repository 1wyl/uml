import Vue from 'vue'
import VueRouter from 'vue-router'
// import HomeView from '../views/HomeView.vue'
import Layout from '@/layout'
Vue.use(VueRouter)

const routes = [

  {
    path: '/',
    name: 'Home',
    redirect: '/login'
    // component: () => import( '../views/Cover/Home.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import( '../views/Cover/LogAndReg/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import( '../views/Cover/LogAndReg/Register.vue')
  },

  {
    path:'/profile',
    component:Layout,
    children:[
      {
        path: 'editProfile',
        name: 'editProfile',
        component: () => import( '../views/UserUI/profile/editProfile.vue')
      },
      {
        path: '',
        name: 'profile',
        component: () => import('../views/UserUI/profile/Profile.vue')
      },
    ]
  },
  ///////////////////////////////////////////////////////
  {
    path:'/relative',
    component:Layout,
    children:[
      {
        path: 'info',
        name: 'relative',
        component: () => import( '../views/UserUI/relative/relative.vue')
      },
      {
        path: 'editRelative',
        name: 'editRelative',
        component: () => import( '../views/UserUI/relative/editRelative.vue')
      },
      {
        path: 'addRelative',
        name: 'addRelative',
        component: () => import( '../views/UserUI/relative/addRelative.vue')
      }
    ]
  },
/////////////////////////////////////////////////////////////////
  {
    path:'/service',
    component:Layout,
    children:[
      {
        path: 'food',
        name: 'food',
        component: () => import('../views/UserUI/serviceApply/food.vue')
      },
      {
        path: 'medicine',
        name: 'medicine',
        component: () => import('../views/UserUI/serviceApply/medicine.vue')
      }
    ]
  },
  {
    path:'/emergency',
    component:Layout,
    children:[
      {
        path: '',
        name: 'emer',
        component: () => import('../views/UserUI/emergency/eme.vue')
      },
    ]
  },
{
  path:'/file',
  component:Layout,
  children:[
    {
      path: '',   
      name: 'file',
      component: () => import( '../views/UserUI/healthfile/health.vue')
    }
  ]
},
///////////////////////////////////////////////////////////////////
{
  path:'/activity',
  component:Layout,
  children:[
    {
      path: '',   
      name: 'activity',
      component: () => import( '../views/UserUI/activityCheck/activity.vue')
    }
  ]
},
{
  path:'/worker',
  component:Layout,
  children:[
    {
      path: 'food',
      name: 'food',
      component: () => import( '../views/WorkerUI/serviceApply/food.vue')
    },
    {
      path: 'addFood',
      name: 'addfood',
      component: () => import( '../views/WorkerUI/serviceApply/addPicture.vue')
    },
    {
      path: 'medicine',
      name: 'medicine',
      component: () => import( '../views/WorkerUI/serviceApply/medicine.vue')
    },
    {
      path: 'addMedicine',
      name: 'addmedicine',
      component: () => import( '../views/WorkerUI/serviceApply/addMedicine.vue')
    },
    {
      path: 'editProfile',
      name: 'editProfile',
      component: () => import( '../views/WorkerUI/profile/editProfile.vue')
    },
    {
      path: 'profile',
      name: 'profile2',
      component: () => import('../views/WorkerUI/profile/Profile.vue')
    },
    {
      path: 'todolist',
      name: 'todolist',
      component: () => import( '../views/WorkerUI/todo/todolist.vue')
    },
    {
      path: 'activity',
      name: 'activity',
      component: () => import( '../views/WorkerUI/activities/todolist.vue')
    },
    {
      path: 'activity/add',
      name: 'addActivity',
      component: () => import( '../views/WorkerUI/activities/AddTask.vue')
    },
    {
      path: 'emer',
      name: 'emergency',
      component: () => import( '../views/WorkerUI/emergency/emergency.vue')
    },
    {
      path: 'file',
      name: 'file',
      component: () => import( '../views/WorkerUI/healthfile/file.vue')
    },
    {
      path: 'healthfile',
      name: 'healthfile',
      component: () => import( '../views/WorkerUI/healthfile/health.vue')
    },
  ]
},
]




// 防止连续点击多次路由报错
let routerPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
