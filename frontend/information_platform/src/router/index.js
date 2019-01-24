import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      redirect: '/HomePage'
    },
    {
      path: '/HomePage',
      name: 'HomePage',
      component: () => import('@/views/HomePage')
    },
    {
      path: '/Login',
      name: 'Login',
      component: () => import('@/views/Login')
    },
    {
      path: '/Message',
      name: 'Message',
      component: () => import('@/views/Message')
    },
    {
      path: '/Appeal',
      name: 'Appeal',
      component:() => import('@/views/Appeal')
    },
    {
      path: '/Announcement',
      name: 'Announcement',
      component:() => import('@/views/Announcement'),
    },
    {
      path: '/Announcement/date/:id',
      name: 'AnnouncementDate',
      component:() => import('@/views/AnnouncementDate'),
    }
  ]
})
