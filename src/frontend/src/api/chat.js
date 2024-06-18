import request from '@/utils/request'

export function fetchhistory(){
    return request({
      url: `/chat/history`,
      method: 'get'
  })
}

export async function fetchchat(sessionid){
    return await request({
      url: `/chat/`+String(sessionid),
      method: 'get',
  })
}

export function sendquestion(question,sessionid){
  return request({
    url: `/chat/conchat`,
    method: 'post',
    data: {
      question: question,
      sessionid: sessionid
    }
})
}

export function newchat(question){
  return request({
    url: `/chat/newchat`,
    method: 'post',
    data: question
})
}