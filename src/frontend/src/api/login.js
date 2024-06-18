import request from '@/utils/request'

export function login(loginForm) {
    return request({
      url: '/user/login',
      method: 'post',
      data: loginForm
    })
}

export function register(userData) {
  return request({
    url: '/user/add',
    method: 'post',
    data: userData
  });
}