<template>
    <div id="404">
      <div >
        <img src="/images/404.png" class="content">
      </div>
      <div class="buttonDiv">
        <el-button @click="goHistory" type="info">返回上一页</el-button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<el-button type="info" @click="goLogin">返回登录页</el-button>
      </div>

    </div>
</template>

<script>
export default {
  name: '404',
  layout: 'empty',
  methods: {
    goLogin() {
      this.$router.push('/')
    },
    goHistory() {
      this.$router.go(-1)
    }
  }
}
</script>

<style>
  .content{
    display: inline-block;
    margin-left: 40%;
    margin-top: 15%;
  }
  .buttonDiv{
    margin-left: 42%;
    margin-top: 3%;
  }
</style>
