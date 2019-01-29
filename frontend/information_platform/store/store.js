import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


const store = new Vuex.Store({
  state: {
    token: "" || localStorage.getItem("token"),
    userNickname: "" || localStorage.getItem("userNickname")
  },
  mutations: {
    isLogin(state,token,userNickname){
      state.token = token;
      state.userNickname = userNickname;
      localStorage.setItem("token",token);
      localStorage.setItem("userNickname",userNickname);
    },
    isLogout(state){
      state.token = "";
      state.userNickname = "";
      localStorage.removeItem("token");
      localStorage.removeItem("userNickname");
    }
  }
})

export default store
