import request from '@/utils/request'



export function updateImage(files,description){
    return request({
        url: '/source/uploadImage',
        method: 'post',
        data: {
            files: files,
            description: description
        },
    })
}


export function requestfood(fid){
    return request({
        url: '/source/requestfood',
        method: 'post',
        data:fid

    })
}
        // params: {
        //     fid: fid
        // }
export function requestmedicine(mid){
    return request({
        url: '/source/requestmedicine',
        method: 'post',
        params: {
            // mid:14
            mid: parseInt(mid, 10) // 将参数转换为整数
        }
    })
}
        