<template>
  <div>
    <tophead></tophead>
    <!--<h1>{{user.id}}</h1>-->
    <div class="center">
      <font size="4">用户id： {{user.id}}</font>
      <br>
      <font size="4">用户积分： {{user.point}}</font>
      <br>
      <nobr>
        <font size="4">用户昵称： </font>
        <Input class="input" v-model="value1" size="large" placeholder="用户昵称" />
      </nobr>
      <br>
      <nobr>
        <font size="4">原用户密码： </font>
        <Input class="input" v-model="value2" size="large" placeholder="原用户密码" />
      </nobr>
      <br>
      <nobr>
        <font size="4">新用户密码： </font>
        <Input class="input" v-model="value3" size="large" placeholder="新用户密码" />
      </nobr>
      <br>
      <nobr>
        <font size="4">确认新用户密码： </font>
        <Input class="input" v-model="value4" size="large" placeholder="确认新用户密码" />
      </nobr>
      <br>
      <Button type="primary" size="large" @click="set">确认修改</Button>
    </div>
    <br>
    <div class="center1">
      <Tabs type="card" id="cardbox">
        <TabPane label="历史发帖">
          <Row class="cardbox" style="background:#eee;padding:20px">
            <Col class="cardcol" span="25" v-for="(sendpost,index) in sendpost" :key="sendpost.postid">
              <div>
                <Icon type="md-trash" size="25" @click="deletepost(sendpost.postid)"/>
                <Card class="card" :bordered="true">
                  <h1 class="headline" slot="title">{{sendpost.label}} {{sendpost.title}}</h1>
                  <h3>发帖时间：{{sendpost.date}}</h3>
                </Card>
              </div>
            </Col>
          </Row>
        </TabPane>
        <TabPane label="历史回帖">
          <Row class="cardbox" style="background:#eee;padding:20px">
            <Col class="cardcol" span="25" v-for="(replypost,index) in replypost" :key="replypost.id">
              <div>
                <Icon type="md-trash" size="25" @click="deletecomment(replypost.id)"/>
                <Card class="card" :bordered="true">
                  <h2 class="headline" slot="title">主帖标题:{{replypost.title}} <br> 回复内容:{{replypost.content}}</h2>
                  <h3>回帖时间：{{replypost.date}}</h3>
                </Card>
              </div>
            </Col>
          </Row>
        </TabPane>
      </Tabs>
    </div>
    <bottom class="bottom"></bottom>
  </div>
</template>
<style scoped>
  .center{
    width: 20%;
    margin: auto;
  }
  .card{
    margin: auto;
    border: 1px solid black;
  }
  .cardcol{
    margin: 4px auto;
  }
  .center1{
    width: 80%;
    margin: auto;
  }
  .bottom{
    position: fixed;
    bottom: 0px;
  }
</style>
<script>
  import tophead from '@/components/Head'
  import bottom from '@/components/Bottom'
  import axios from 'axios'

  export default {
    data() {
      return {
        user:{
          id: "",
          nickname: "",
          point: ""
        },
        status1: '',
        errormsg1: '',
        status2: '',
        errormsg2: '',
        status3: '',
        errormsg3: '',
        status4: '',
        errormsg4: '',
        status5: '',
        errormsg5: '',
        status6: '',
        errormsg6: '',
        status7: '',
        errormsg7: '',
        value1: '',
        value2: '',
        value3: '',
        value4: '',
        sendpost: [],
        replypost: [],
      }
    },
    components: {
      tophead,
      bottom
    },
    created() {
      this.getParams();
      this.getpost();
      this.getcomment();
    },
    methods: {
      getParams(){
        this.user.id = this.$store.state.userId;
        this.user.nickname = this.$store.state.userNickname;
        axios({
          url: apiRoot + '/user/showmyself/' + this.$store.state.userId,
          headers: {Authorization: this.$store.state.token},
          method: 'get',
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.user.id = res.data.userid;
            this.user.nickname = res.data.usernickname;
            this.user.point = res.data.userpoint;
            this.status1 = res.status;
          } else {
            this.status1 = res.status;
            this.errormsg1 = res.message;
          }
        })
      },
      set(){
        this.setmyself();
        this.setpwd();
      },
      setmyself(){
        if(this.value1) {
          axios({
            url: apiRoot + '/user/setmyself',
            headers: {Authorization: this.$store.state.token},
            method: 'post',
            data: {
              userid: this.$store.state.userId,
              usernickname: this.value1,
            }
          }).then((response) => {
            let res = response.data;
            if (res.status === "success") {
              this.status2 = res.status;
              this.$Message.info('修改昵称成功！');
              this.value1 = '';
            } else {
              this.status2 = res.status;
              this.errormsg2 = res.message;
              this.$Message.info('修改昵称失败： ' + this.errormsg2);
            }
          })
        }
      },
      setpwd(){
        if(this.value2 && this.value3 && this.value4) {
          if (this.value3 === this.value4 && this.value3) {
            axios({
              url: apiRoot + '/editpwd',
              headers: {Authorization: this.$store.state.token},
              method: 'post',
              data: {
                userID: this.$store.state.userId,
                userOldPwd: this.value2,
                userNewPwd: this.value3
              }
            }).then((response) => {
              let res = response.data;
              if (res.status === "success") {
                this.status7 = res.status;
                this.$Message.info('修改密码成功！');
                this.value2 = '';
                this.value3 = '';
                this.value4 = '';
              } else {
                this.status7 = res.status;
                this.errormsg7 = res.message;
                this.$Message.info('修改密码失败： ' + this.errormsg7);
              }
            })
          } else {
            this.$Message.info('两次输入的新密码不一致，请重新输入!');
            this.value2 = '';
            this.value3 = '';
            this.value4 = '';
          }
        }
      },
      getpost(){
        axios({
          url: apiRoot + '/user/getpost/' + this.$store.state.userId,
          headers: {Authorization: this.$store.state.token},
          method: 'get',
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.sendpost = res.data;
            this.status3 = res.status;

          } else {
            this.status3 = res.status;
            this.errormsg3 = res.message;
          }
        })
      },
      getcomment(){
        axios({
          url: apiRoot + '/user/getcomment/' + this.$store.state.userId,
          headers: {Authorization: this.$store.state.token},
          method: 'get',
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.replypost = res.data;
            this.status4 = res.status;

          } else {
            this.status4 = res.status;
            this.errormsg4 = res.message;
          }
        })
      },
      deletepost(id){
        axios({
          url: apiRoot + '/user/deletepost',
          headers: {Authorization: this.$store.state.token},
          method: 'post',
          data: {
            userid: this.$store.state.userId,
            postid: id,
          }
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.status5 = res.status;
            this.$Message.info('删除成功！');
            let i = 0;
            for(let x of this.sendpost){
              if(x.postid === id){
                this.sendpost.splice(i,1);
                break;
              }
              i++;
            }
          } else {
            this.status5 = res.status;
            this.errormsg5 = res.message;
            this.$Message.info('删除失败： ' + this.errormsg5);
          }
        })
      },
      deletecomment(id){
        axios({
          url: apiRoot + '/user/deletecomment',
          headers: {Authorization: this.$store.state.token},
          method: 'post',
          data: {
            userid: this.$store.state.userId,
            commentid: id,
          }
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.status6 = res.status;
            this.$Message.info('删除成功！');
            let i = 0;
            for(var x of this.replypost){
              if(x.id === id){
                this.replypost.splice(i,1);
                break;
              }
              i++;
            }
          } else {
            this.status6 = res.status;
            this.errormsg6 = res.message;
            this.$Message.info('删除失败： ' + this.errormsg6);
          }
        })
      }
    },
    watch: {
      '$route': 'getParams'
    }
  }
</script>
