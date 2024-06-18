import request from '@/utils/request'

export function getUserMenu() {
  return request({
    url: `/user/menu`,
    method: 'get',
  })
}
