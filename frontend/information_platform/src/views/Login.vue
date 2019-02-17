<template>
  <div class="login-content">
    <div id="head1">
      <div id="headpic">
        <img id="pichead" src="./../../static/back.jpg" width="30%"/>
      </div>
    </div>
    <div id="next">
      <div id="pic">
        <img id="piccontent" src="./../../static/back.png" width="50%"/>
      </div>
      <div id="ground">
        <div id="body1">
          <div class="modal-header">
            <h1>综合信息平台</h1>
          </div>
          <div class="modal-body">
            <section class="box-login v5-input-txt" id="box-login">
              <form id="login_form" action="" method="post" autocomplete="off">
                <div class="form-group"><Input id="input1" prefix="md-contact" placeholder="请输入账号" style="width: auto" v-model="loginForm.userId"/></div>
                <div class="form-group"><Input id="input2" prefix="md-key" placeholder="请输入密码" style="width: auto" v-model="loginForm.userPwd"/></div>
              </form>
              <div class="login-box marginB10">
                <div class="error-show" v-show="errorTip">账号或密码错误</div>
                <div class="error-show" v-show="errorTip1">账号和密码不能为空</div>
                <Button type="primary" @click="login">登 录</Button>
                <div id="login-form-tips" class="tips-error bg-danger" style="display: none;">错误提示</div>
              </div>
            </section>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
  .modal-header{
    width: 100%;
    margin: 2% auto;
    font-size: 1em;
  }
  .form-group{
    width: 100%;
    margin: 2% auto;
  }
  .login-box marginB10{
    margin: 2% auto;
    float: top;
    width: 100%;
  }
  #input1{
    float: top;
  }
  #input2{
    float: top;
  }
  #ground{
    position: relative;
    width: 35%;
    float: left;
  }
  #head1{
    position: relative;
    width:90%;
    margin: 3% auto;
    float: top;
  }
  #pichead{
    position: relative;
    margin: auto;
  }
  #next{
    position: relative;
    width:90%;
    margin: 3% auto;
    float:top
  }
  #pic{
    position: relative;
    width: 65%;
    float: left;
  }
  #piccontent{
    position: relative;
    margin: auto;
  }
  #headpic{
    position: relative;
    margin: 2% auto;
    width: 65%;
    float: left;
  }
  .login-content{
    position: fixed;
    top: 0;
    height: 100%;
    width: 100%;
    background: url("./../../static/back1.jpg");
  }
  #body1{
    position: relative;
    width: 100%;
    margin: 0 auto;
    right: 15%;
    top: 40%;
  }

</style>
<script>
  import axios from 'axios'

    export default {
        data() {
            return {
              errorTip: false,
              errorTip1: false,
              loginForm: {
                userId: "",
                userPwd: ""
              },
              userToken: "",
              userNickname: ""
            }
        },
        methods:{
          login(){
            if(!this.loginForm.userId||!this.loginForm.userPwd){
              this.errorTip1 = true;
            } else {
              axios({
                url: apiRoot + '/login',
                method: 'post',
                data: {
                  userId: this.loginForm.userId,
                  userPwd: this.loginForm.userPwd,
                }
              }).then((response) => {
                let res = response.data;
                if (res.status === "success") {
                  this.errorTip = false;
                  var _this = this;
                  setTimeout(function () {
                    _this.$router.push({path: '/HomePage'})
                  },1000)
                  localStorage.setItem('token',res.data.token)
                  localStorage.setItem('userNickname',res.data.userNickname)
                  localStorage.setItem('userId',res.data.userId)
                  this.$store.commit('isLogin',{token:res.data.token,userNickname:res.data.userNickname,userId:res.data.userId})
                  this.$Message.info("登录成功！");
                } else {
                  this.errorTip = true;
                }
              })
            }
          }
        }
    }
</script>
