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
    <bottom></bottom>
  </div>
</template>
<style scoped>
  .center{
    width: 20%;
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
        value1: '',
        value2: '',
      }
    },
    components: {
      tophead,
      bottom
    },
    created() {
      this.getParams();
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
      }
    }
  }
</script>
