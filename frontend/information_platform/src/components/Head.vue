<template>
  <div class="head-content">
    <div id="word">
      <img id="pic" src="./../assets/logo.jpg" height="17%" width="17%"/>
      <img src="./../assets/headline.png" height="40%" width="40%"/>
      <div id="loginbtn">
        <Button type="primary" shape="circle" @click="jumpLogin">登录</Button>
      </div>
    </div>
    <div id="navigation">
      <Menu id="menu" mode="horizontal" :theme="theme1" active-name="1" @on-select="routerTo">
        <MenuItem id="homepage" to="/HomePage">
          <Icon type="ios-home" />
          首页
        </MenuItem>
        <MenuItem id="announcement" to="/Announcement">
          <Icon type="md-open" />
          公告
        </MenuItem>
        <!--<MenuItem id="announcement1" to="/announcement1">-->
          <!--<Icon type="ios-list-box" />-->
          <!--校园事务-->
        <!--</MenuItem>-->
        <!--<MenuItem id="announcement2" to="/announcement2">-->
          <!--<Icon type="ios-hand" />-->
          <!--失物招领-->
        <!--</MenuItem>-->
        <MenuItem id="help" to="/Help">
          <Icon type="ios-hand" />
          互助系统
        </MenuItem>
        <MenuItem id="forum" to="/Forum">
          <Icon type="ios-text" />
          论坛
        </MenuItem>
        <!--<MenuItem id="info1" to="/info1">-->
          <!--<Icon type="ios-people" />-->
          <!--群组消息-->
        <!--</MenuItem>-->
        <MenuItem id="info2" to="/Message" >
          <Icon type="ios-mail" />
          私信
        </MenuItem>
        <MenuItem id="appeal" to="/Appeal">
          <Icon type="md-help" />
          申诉
        </MenuItem>
      </Menu>
    </div>
  </div>
</template>
<script>
  import './../assets/headline.png'
  import './../assets/logo.jpg'
  export default {
    data () {
      return {
        theme1: 'light'
      }
    },
    methods: {
      jumpLogin(){
        //this.$router.push("/cart")
        //传递的参数用{{ $route.query.goodsId }}获取

        this.$router.push({path: '/Login'})
        //this.$router.go(-2)
        //后退两步
      },
      routerTo(name){
        this.$router.push(name)
      }
    }
  }
</script>
<style scoped>
  #loginbtn{
    position: absolute;
    right: 2%;
  }
  .head-content{
    position: relative;
    top: -50px;
    width: 100%;
  }
  #word{
    display: flex;
    align-items: center;
    justify-content: center;
    background: #245086;
  }
  #navigation{
    height: 25%;
    display: flex;
    align-items: center;
    justify-content: center;
  }

</style>
