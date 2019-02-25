<template>
  <div class="head-content">
    <div id="word">
      <img id="pic" src="./../assets/logo.jpg" height="17%" width="18%"/>
      <img src="./../assets/headline.png" height="40%" width="40%"/>
      <div id="loginbtn">
        <!--<Button type="primary" shape="circle" @click="jumpLogin">登录</Button>-->
        <!--<Button type="primary" shape="circle" to="/Manage/User">管理</Button>-->
        <!--<font size="4" v-text="$store.state.userId" color="white" @click=""></font>-->
        <!--<font size="4" v-text="$store.state.token" color="white" @click=""></font>-->
        <Icon type="md-settings" size="25" v-if="$store.state.token" @click="jumpToChangeUserDetail"/>
        <font id="name" size="4" v-if="$store.state.token" v-text="$store.state.userNickname" color="white"></font>
        &nbsp;&nbsp;

        <Button type="primary" shape="circle" @click="jumpLogin" v-if="!$store.state.token">登录</Button>
        <Button type="primary" shape="circle" @click="jumpLogout" v-if="$store.state.token">退出</Button>
      </div>
    </div>
    <div id="navigation">
      <Menu id="menu" mode="horizontal" :theme="theme1" active-name="1" @on-select="routerTo">
        <MenuItem id="homepage" name="/HomePage">
          <Icon type="ios-home" />
          首页
        </MenuItem>
        <MenuItem id="announcement" name="/Announcement">
          <Icon type="md-open" />
          公告
        </MenuItem>
        <!--<MenuItem id="announcement1" to="/announcement1">-->
          <!--<Icon type="ios-list-box" />-->
          <!--校园事务-->
        <!--</MenuItem>-->
        <MenuItem id="announcement2" name="/LostAFound/Board">
          <Icon type="ios-list-box"/>
          失物招领
        </MenuItem>
        <MenuItem id="help" name="/Help">
          <Icon type="ios-hand" />
          互助系统
        </MenuItem>
        <MenuItem id="forum" name="/Forum">
          <Icon type="ios-text" />
          论坛
        </MenuItem>
        <!--<MenuItem id="info1" to="/info1">-->
          <!--<Icon type="ios-people" />-->
          <!--群组消息-->
        <!--</MenuItem>-->
        <MenuItem id="info2" name="/Message" >
          <Icon type="ios-mail" />
          私信
        </MenuItem>
        <MenuItem id="appeal" name="/Appeal">
          <Icon type="md-help" />
          申诉
        </MenuItem>
        <MenuItem id="find" name="/Find">
          <Icon type="ios-search" />
          搜索好友
        </MenuItem>
      </Menu>
    </div>
  </div>
</template>
<script>
  import './../assets/headline.png'
  import './../assets/logo.jpg'
  import axios from 'axios'

  export default {
    data () {
      return {
        theme1: 'light',
        status: '',
        errormsg: '',
      }
    },
    methods: {
      jumpLogin(){
        this.$router.push({path: '/Login'});
      },
      jumpLogout(){
        axios({
          // url: apiRoot + '/logout',
          url: '/logout',
          headers: {
            "Authorization": this.$store.state.token,
            'Content-Type': 'application/json;charset=UTF-8'
          },
          method:'post',
          data: {
            userid: this.$store.state.userId,
          }
        }).then((response) => {
          let res = response.data;
          if (res.status === "success") {
            this.status = res.status;
            this.$store.commit('isLogout');
            this.$router.push({path: '/HomePage'});
            this.$Message.info("退出成功！");
          } else {
            this.status = res.status;
            this.errormsg = res.message;
            this.$Message.info("出现错误: " + this.errormsg);
          }
        })
      },
      routerTo(name){
        this.$router.push(name)
      },
      jumpToChangeUserDetail(){
        this.$router.push({path: '/ChangeUserDetail'})
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
  #pic{
    position: relative;
    top: 1%;
  }
</style>
