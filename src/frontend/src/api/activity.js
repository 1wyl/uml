import request from '@/utils/request';

export function fetchActivities() {
  return request({
    url: '/activities/get',
    method: 'get'
  });
}

export function deleteActivity(activity) {
  return request({
    url: '/activities/delete',
    method: 'post',
    data: activity
  });
}

export function addActivity(activity) {
  return request({
    url: '/activities/add',
    method: 'post',
    data: activity
  });
}