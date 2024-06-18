// import axios from 'axios';
// import { getAccessToken } from '@/utils/auth'

// const service = axios.create({
//   // baseURL: 'https://mock.apifox.cn/m1/2428381-0-default/admin-api',
//   baseURL: '/api',
//   timeout: 5000
// });

// service.interceptors.request.use(
//   config => {
//     // 在请求发送之前对请求数据进行处理
//     if (getAccessToken()) {
//       config.headers['Authorization'] = 'Bearer ' + getAccessToken() // 携带token
//     }

//     return config;
//   },
//   error => {
//     // 对请求错误做些什么
//     console.log(error);
//     return Promise.reject(error);
//   }
// );

// service.interceptors.response.use(
//   response => {
//     // 对响应数据进行处理
//     // ...

//     return response.data;
//   },
//   error => {
//     // 对响应错误做些什么
//     console.log(error);
//     return Promise.reject(error);
//   }
// );

// export default service;


import axios from 'axios';
import { getAccessToken } from '@/utils/auth'

const service = axios.create({
  baseURL: '/api',
  timeout: 5000,
  headers: {
    // 'Content-Type': 'multipart/form-data'
    'Content-Type': 'application/json; charset=UTF-8'  // Set the content type to JSON with UTF-8 charset
  }
});

service.interceptors.request.use(
  config => {
    if (getAccessToken()) {
      config.headers['Authorization'] = 'Bearer ' + getAccessToken()
    }

    return config;
  },
  error => {
    console.log(error);
    return Promise.reject(error);
  }
);

service.interceptors.response.use(
  response => {
    return response.data;
  },
  error => {
    console.log(error);
    return Promise.reject(error);
  }
);

export default service;
