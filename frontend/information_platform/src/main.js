// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import iView from 'iview'
import 'iview/dist/styles/iview.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import store from './../store/store'

Vue.config.productionTip = false
Vue.use(iView)
Vue.use(VueAxios,axios)


require('./../mock/login')

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

router.beforeEach((to, from, next) => {
  if(to.meta.requireAuth) {
    if(store.state.token) {
      next();
    } else{
      next({
        path: '/Login',
        query: {redirect: to.fullPath}
      })
    }
  }
  else if(to.meta.requireManage) {
    axios.post("/ifmanage", {
      token: store.state.token,
      userid: store.state.userId,
    }).then((response) => {
      let res = response.data;
      if(res.status === "success") {
        if(res.ifmanage){
          next();
        }else {
          next({
            path: '/HomePage',
          });
        }
      } else {
        this.$Message.info('失败');
      }
    })
  } else {
    next();
  }
})
