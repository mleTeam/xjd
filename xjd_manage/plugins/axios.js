import  { Message }  from 'element-ui'
import { MLE_TOKEN } from '~/assets/constsUtil.js'
export default function ({ $axios, redirect }) {
  $axios.onRequest(config => {
    let token = sessionStorage.getItem(MLE_TOKEN)// 从sessionStorage中拿去token信息
    if(token){
      config.headers['Authorization'] = token // 如果存在token信息,设置请求头Authorization
    }
    // 判断当前请求方法是否为get,并且请求头中是否存在Contrent-Type值,值是否为application/vnd.ms-excel
    if(config.method === 'get' && config.headers['Content-Type'] && config.headers['Content-Type'] === 'application/vnd.ms-excel'){
      let url = config.baseURL + config.url + formatParam(config.params) // 获取请求地址
      let token = sessionStorage.getItem(MLE_TOKEN)// 从sessionStorage中拿去token信息
      if (token) {
        // 因为axios无法正常请求文件流,所以通过创建iframe元素进行下载.
        // iframe元素无法携带请求头Authorization令牌.所以将令牌临时存放在cookie中,3秒后自动消失
        let d = new Date();
        d.setTime(d.getTime() + (3*1000));
        let expires = "expires=" + d.toUTCString();
        document.cookie = "MILIBANKING_MANAGE" + "=" + token + "; path=/;" + expires;
        downloadUrl(url)  // 调用下载方法开启新请求
      } else {
        redirect('/')
        Message({
          message: "令牌已消失",
          type: 'error',
          showClose: true
        });
      }
      config.url = '/' // 将当前请求变为根目录(防止重复请求数据下载两遍)
      return config
    }
  })

  $axios.onError(error => {
    const code = parseInt(error.response && error.response.status) //获取请求错误信息中的请求
    if (code === 401) {// 登录超时或未登录
      redirect('/')
      Message({
        message: "令牌已消失",
        type: 'error',
        showClose: true
      });
    } else if (code === 403) {// 无权限请求当前接口
      redirect('/403')
    } else if (code === 500 && error.response.data) {// 服务端异常或未知异常
      Message({
        message: error.response.data.message,
        type: 'error',
        showClose: true
      });
    }
  })

  $axios.onResponse(res => {
    // 服务端内部调用异常(请求成功,但当前操作失败,如: status=406(参数校验失败) )
    if(res.data && res.data.status && res.data.status != 200 && res.data.status != 201){
      Message({
        message: res.data.message,
        type: 'error',
        showClose: true
      });
    }
  })

}

const formatParam = params => {
  let str = '?'
  for(let i in params){
    if(str != '?'){
      str = str + '&'
    }
    str = str + i + '=' + params[i]
  }
  return str
}

// download url
const downloadUrl = url => {
  let iframe = document.createElement('iframe')
  iframe.style.display = 'none'
  iframe.src = url
  iframe.onload = function () {
    document.body.removeChild(iframe)
  }
  document.body.appendChild(iframe)
}

// Add a response interceptor

