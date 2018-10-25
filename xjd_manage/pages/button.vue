<template>
    <div>
      <p>{{ list }}</p>
      <el-button @click="show">显示</el-button>
      <el-button v-show="this.common.isHaveButton('SCORE_ADD',$store.state.rules)" type="success">SCORE_ADD3</el-button>
      <el-button v-show="this.common.isHaveButton('SCORE_DELETE',$store.state.rules)" type="danger">SCORE_DELETE3</el-button>
      <el-button v-show="this.common.isHaveButton('SCORE_MODIFY',$store.state.rules)" type="warning">SCORE_MODIFY3</el-button>
      <el-button v-show="this.common.isHaveButton('ACTION_ADD', $store.state.rules)" type="success">ACTION_ADD3</el-button>
      <el-button v-show="this.common.isHaveButton('ACTION_DELETE',$store.state.rules)" type="warning">ACTION_DELETE3</el-button>
      <el-button v-show="this.common.isHaveButton('ACTION_MODIFY',$store.state.rules)" type="info">ACTION_MODIFY3</el-button>
      <p>{{ $store.state.buttons }}</p>
    </div>
</template>

<script>
export default{
  layout: 'empty',
  name: 'testButton',
  data() {
    return {
      list: []
    }
  },
  mounted() {
    this.$store.commit('setRules', sessionStorage.MANAGE_RULES)
  },
  methods: {
    show() {
      let self = this
      self.list = sessionStorage.MANAGE_RULES
    },
    go1() {
      this.$router.push('/test')
    }
  }
}
</script>
