<template>
    <div>
      <p>{{ list }}</p>
      <el-button type="primary" icon="el-icon-search" @click="setList">设置storelist</el-button>
      <el-button type="primary" icon="el-icon-search" @click="setList2">设置storelist2</el-button>
      <el-button @click="go1">跳转</el-button>
      <el-button @click="show">显示</el-button>
      <el-button @click="setToken">setToken</el-button>
      <el-button @click="getTotalScore">test</el-button>
    </div>
</template>

<script type="text/ecmascript-6">
export default{
  layout: 'empty',
  name: 'store',
  data() {
    return {
      list: []
    }
  },
  methods: {
    setList(){
      sessionStorage.buttons = ['SCORE_ADD','SCORE_DELETE','SCORE_MODIFY']
    },
    setList2(){
      sessionStorage.buttons = ['ACTION_ADD','ACTION_DELETE','ACTION_MODIFY']
    },
    show() {
      let self = this
      self.list = sessionStorage.getItem('buttons')
    },
    go1() {
      this.$router.push('/button')
    },
    setToken() {
      sessionStorage.setItem('MILIBANKING_MANAGE','123iouasoiudaskbfsauiy214124')
    },
    getTotalScore(){
      this.$axios.$get('/v2/scores/totalScore')
        .then((res) =>{

    })
    },
  }
}
</script>
