import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


const store = new Vuex.Store({
  state: {
    token: "" || localStorage.getItem("token"),
    // token: "dsaf",
    userNickname: "" || localStorage.getItem("userNickname"),
    // userNickname: "li",
    userId: "" || localStorage.getItem("userId")
    // userId: "16122075"
  },
  mutations: {
    isLogin(state,msg){
      state.token = msg.token;
      state.userNickname = msg.userNickname;
      state.userId = msg.userId
      localStorage.setItem("token",msg.token);
      localStorage.setItem("userNickname",msg.userNickname);
      localStorage.setItem("userId",msg.userId);
    },
    isLogout(state){
      state.token = "";
      state.userNickname = "";
      state.userId = "";
      localStorage.removeItem("token");
      localStorage.removeItem("userNickname");
      localStorage.removeItem("userId");
    }
  },
  getters: {
    getToken(state){
      return state.token
    },
    getUserNickname(state){
      return state.userNickname
    }
  }
})

export default store
