import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/views/HomePage'
import Login from '@/views/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    }
  ]
})
