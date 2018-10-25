<template>
  <div id="error">
    <div  v-if="error.statusCode==404">
      <img src="/images/404.png" class="content">
    </div>
    <h2 v-else>Nuxt错误</h2>
    <br>
    <div style="text-align: center;font-size: 30px">
      <el-button type="info" @click="goHistory">返回上一页</el-button>&nbsp;
      <nuxt-link to="/"><el-button type="info">&nbsp;返回首页&nbsp;</el-button></nuxt-link>
    </div>
  </div>
</template>

<script>
  export default {
    layout: 'empty',
    props:['error'],
    methods: {
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
</style>
