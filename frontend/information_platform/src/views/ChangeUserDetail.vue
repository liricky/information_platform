<template>
  <div>
    <tophead></tophead>
    <div class="center">
      <font size="4">用户id： {{user.id}}</font>
      <br>
      <font size="4">用户积分： {{user.point}}</font>
      <br>
      <nobr>
      <font size="4">用户昵称： </font>
      <Input class="input" v-model="value1" size="large" :placeholder="user.nickname" />
      </nobr>
      <br>
      <nobr>
      <font size="4">用户密码： </font>
      <Input class="input" v-model="value2" size="large" :placeholder="user.userpwd" />
      </nobr>
      <br>
      <Button type="primary" size="large" @click="setmyself">确认修改</Button>
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
    <bottom></bottom>
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
          point: "",
          userpwd: ""
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
        value1: '',
        value2: '',
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
        axios.get("/user/showmyself", {
          token: this.$store.state.token,
          userid: this.$store.state.userId,
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.user.id = res.userdate.userid;
            this.user.nickname = res.userdate.usernickname;
            this.user.point = res.userdate.userpoint;
            this.user.userpwd = res.userdate.userpwd;
            this.status1 = res.status;
          } else {
            this.status1 = res.status;
            this.errormsg1 = res.message;
          }
        })
      },
      setmyself(){
        axios.post("/user/setmyself", {
          token: this.$store.state.token,
          userid: this.$store.state.userId,
          usernickname: this.value1,
          userpwd: this.value2,
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.status2 = res.status;
            this.$Message.info('修改成功！');

          } else {
            this.status2 = res.status;
            this.errormsg2 = res.message;
            this.$Message.info('修改失败： ' + this.errormsg2);
          }
        })
      },
      getpost(){
        axios.get("/user/getpost", {
          token: this.$store.state.token,
          userid: this.$store.state.userId,
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.sendpost = res.post;
            this.status3 = res.status;

          } else {
            this.status3 = res.status;
            this.errormsg3 = res.message;
          }
        })
      },
      getcomment(){
        axios.get("/user/getcomment", {
          token: this.$store.state.token,
          userid: this.$store.state.userId,
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.replypost = res.reply;
            this.status4 = res.status;

          } else {
            this.status4 = res.status;
            this.errormsg4 = res.message;
          }
        })
      },
      deletepost(id){
        axios.post("/user/deletepost", {
          token: this.$store.state.token,
          userid: this.$store.state.userId,
          postid: id,
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.status5 = res.status;
            this.$Message.info('删除成功！');
            let i = 0;
            for(var x of this.sendpost){
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
        axios.post("/user/deletecomment", {
          token: this.$store.state.token,
          userid: this.$store.state.userId,
          commentid: id,
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
    }
  }
</script>
