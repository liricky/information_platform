<template>
  <div id="main">
    <div id="head">
      <div id="headpic">
        <img id="pichead" src="./../../static/back.jpg" width="30%"/>
      </div>
    </div>
    <div id="next">
      <div id="pic">
        <img id="piccontent" src="./../../static/back.png" width="50%"/>
      </div>
      <div id="ground">
        <div id="body">
          <div class="modal-header">
            <h1>综合信息平台</h1>
          </div>
          <div class="modal-body">
            <section class="box-login v5-input-txt" id="box-login">
              <form id="login_form" action="" method="post" autocomplete="off">
                <div class="form-group"><Input id="input1" prefix="md-contact" placeholder="请输入账号" style="width: auto" v-model="userName"/></div>
                <div class="form-group"><Input id="input2" prefix="md-key" placeholder="请输入密码" style="width: auto" v-model="userPwd"/></div>
              </form>
              <div class="login-box marginB10">
                <div class="error-show" v-show="errorTip">用户名或密码错误</div>
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
<style>
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
  #head{
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
  #main{
    position: relative;
  }
  #body{
    position: relative;
    width: 100%;
    margin: 0 auto;
    right: 15%;
    top: 40%;
  }
  body{
    background: url("./../../static/back1.jpg");
  }
</style>
<script>
  import axios from 'axios'
    export default {
        data() {
            return {
              userName:'',
              userPwd:'',
              errorTip:false
            }
        },
        methods:{
          login(){
            axios.post("/users/login",{
              userName:this.userName,
              userPwd:this.userPwd
            }).then((response)=>{
              let res = response.data;
              if(res.status=="success"){
                this.errorTip = false;
                //to-do
              }else{
                this.errorTip = true;
              }
            })
          }
        }
    }
</script>
