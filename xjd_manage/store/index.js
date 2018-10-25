import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = () => new Vuex.Store({

  state: {
    rules: ''
  },
  mutations: {
    setRules (state, value) {
      state.rules = value
    },
    getRules (state) {
      return state.rules
    }
  }
})

export default store
