import request from '@/utils/request'

export function fetchhistory(form){
    return request({
      url: `/source/emer`,
      method: 'post',
      data:form
  })
}

export function fetchemer(){
    return request({
      url: `/source/fetchemer`,
      method: 'get',
  })
}