import request from '@/utils/request'

export function fetchtasks(){
    return request({
        url: '/tasks/mytasks',
        method: 'get'
    })
}

export function addtask(task){
    return request({
        url: '/tasks/add',
        method: 'post',
        data: task
    })
}

export function fetchtask(taskid){
    return request({
        url: '/tasks/' + String(taskid),
        method: 'get',
    })
}

export function edittask(task){
    return request({
        url: 'tasks/update',
        method: 'post',
        data: task
    })
}

export function deletetask(task){
    return request({
        url: 'tasks/delete',
        method: 'post',
        data: task
    })
}

export function toggle(code,rid){
    return request({
        url: '/tasks/toggle',
        method: 'post',
        data: {
            code:code,
            rid,rid
        }
    })
}
