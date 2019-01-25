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
    },
    {
      path: '/Forum',
      name: 'Forum',
      component: () => import('@/views/Forum'),
    },
    {
      path: '/ForumCreate',
      name: 'ForumCreate',
      component: () => import('@/views/ForumCreate'),
    },
    {
      path: '/ForumPage',
      name: 'ForumPage',
      component: () => import('@/views/ForumPage'),
    },
    {
      path: '/ForumDetail',
      name: 'ForumDetail',
      component: () => import('@/views/ForumDetail'),
    },
    {
      path: '/Help',
      name: 'Help',
      component: () => import('@/views/Help'),
    },
    {
      path: '/Report',
      name: 'Report',
      component: () => import('@/views/Report'),
    },
    {
      path: '/UserDetail',
      name: 'UserDetail',
      component: () => import('@/views/UserDetail')
    },
    {
      path: '/Find',
      name: 'Find',
      component: () => import('@/views/Find')
    },
    {
      path: '/LostAFound/Board',
      name:'LostAFoundBoard',
      component:() => import('@/views/LostAFound/Board')
    },
    {
      path: '/LostAFound/Publish',
      name:'LostAFoundPublish',
      component:() => import('@/views/LostAFound/Publish')
    },
    {
      path:'/LostAFound/MyBoard',
      name:'LostAFoundMyBoard',
      component:() => import('@/views/LostAFound/MyBoard')
    }
  ]
})
