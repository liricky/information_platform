<template>
    <div>
      <tophead></tophead>
      <div class="center">
        <font size="4">用户id： {{userid}}</font>
        <br>
        <font size="4">昵称： {{userNickname}}</font>
        <br>
        <font size="4">用户积分： {{userpoint}}</font>
        <br>
        <!--<Input class="input" v-model="value1" size="large" :placeholder="user.nickname" />-->
        <!--<Input class="input" v-model="value2" size="large" :placeholder="user.userpwd" />-->
        <!--<Button type="primary" size="large">确认</Button>-->
        <Button type="primary" size="large">添加好友</Button>
        &nbsp;&nbsp;
        <Button type="primary" size="large">加入黑名单</Button>
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

    export default {
      data() {
          return {
            userid: '',
            userNickname: '',
            userpoint: '',
          }
      },
      components: {
        tophead,
        bottom
      },
      created(){
          this.getParams();
      },
      methods:{
        getParams(){
          this.userid = this.$route.query.id;
        }
      }
    }
</script>
