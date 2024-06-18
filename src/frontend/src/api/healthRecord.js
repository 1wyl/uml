import request from '@/utils/request'

export function getHealthRecord() {
  return request({
    url: `/healthRecord/getHealthRecord`,
    method: 'get',
  })
}

export function getRecord(username) {
    return request({
      url: `/healthRecord/getRecord`,
      method: 'get',
      data: username
    })
}

export function updateHealthRecord(uid, data) {
  return request({
    url: `/healthRecord/updateRecord/${uid}`,
    method: 'post',
    data: data
  })
}


export function getHealthRecordbyUid(uid) {
  return request({
    url: `/healthRecord/${uid}`,
    method: 'get'
  });
}
