// api/user.js
import request from '@/utils/request';

export function getUsers() {
  return request({
    url: '/user/list',
    method: 'get'
  });
}
