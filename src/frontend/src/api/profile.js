import request from '@/utils/request'

export function getInfo() {
  return request({
    url: `/user/info`,
    method: 'get',
})
}
// export function save(userForm){
//     const token = getAccessToken();
//     return axios.post('/api/user/update', userForm, {
//         headers: {
//           'Authorization': token
//         }
//       })
// }

export function save(userForm){
    return request({
      url: `/user/update`,
      method: 'post',
      data: userForm,
  })
}
