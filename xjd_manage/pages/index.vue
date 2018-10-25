<template>
  <div class="wrapper">
    <div class="container">
      <h1>欢迎登录</h1>
      <form method="post">
        <input name="adminName" id="adminName" type="text" placeholder="用户名" v-model="admin.adminName">
        <input name="adminPwd" id="adminPwd" type="password" placeholder="密码" v-model="password">
        <el-button type="primary" id="login-button" @click="login()" style="background-color:#96D6C4;border-color:#C9E9E0;font-size:18px">登录</el-button>
      </form>
    </div>
    <ul class="bg-bubbles">
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
    </ul>
  </div>
</template>

<script>
import '~/static/css/styles.css'
import { MLE_POST_OK } from '~/assets/constsUtil.js'
export default {
  layout: 'empty',
  data() {
    return {
      admin:{
        adminName: "",
        adminPwd: ""
      },
      password: ""
    }
  },
  methods: {
      login() {
        let self = this
        if(self.admin.adminName == ""){
          self.common.showErrorMessage("用户名不得为空！")
          return
        }
        if(self.password == ""){
          self.common.showErrorMessage("密码不得为空！")
          return
        }
        self.admin.adminPwd = this.$md5(self.password)
        this.$axios.$post('/manage/login', self.admin, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
          .then(function (response) {
            if( MLE_POST_OK ==  response.status) {
              sessionStorage.MILIBANKING_MANAGE = response.data.accessToken
              sessionStorage.MANAGE_RULES = response.data.rules
              location.href = '/home'
            }
          })
      }
    }
}
</script>
