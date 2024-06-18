// import router from './router'
// import { getAccessToken } from '@/utils/auth'
// // import handleInitialPageLoad from './views/与AI聊天/chat'

// router.beforeEach((to, from, next) => {
//   if (getAccessToken()) { // 如果有token
//     if (to.path === '/') {
//       next({ path: '/profile' }) // 如果是去登录页，重定向到首页
//     } else {
//       next() // 正常跳转
//     }
//   }
//    else { // 没有token
//     if (to.path === '/'||to.path === '/register') {
//       next() // 如果是去登录页或注册页，正常跳转
//     } else {
//       next('/') // 否则重定向到登录页
//     }
//   }
// })

// router.afterEach(() => {
//   // 这里可以添加一些在路由跳转后需要执行的代码
//   // handleInitialPageLoad();
// })


import router from './router'
import { getAccessToken } from '@/utils/auth'

router.beforeEach((to, from, next) => {
  const token = getAccessToken();

  if (token) { // 如果有 token
    if (to.path === '/login' || to.path === '/') {
      next({ path: '/profile' }); // 如果是去登录页，重定向到 profile
    } else {
      next(); // 正常跳转
    }
  } else { // 没有 token
    if (to.path === '/' || to.path === '/register' || to.path === '/login') {
      next(); // 如果是去登录页或注册页，正常跳转
    } else {
      next('/login'); // 否则重定向到登录页
    }
  }
});

router.afterEach(() => {
  // 这里可以添加一些在路由跳转后需要执行的代码
  // handleInitialPageLoad();
});