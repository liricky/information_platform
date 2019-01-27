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
      <Button type="primary" size="large">确认修改</Button>
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
        user:{
          id: "16122075",
          nickname: "li",
          point: "100",
          userpwd: "123456"
        }
      }
    },
    components: {
      tophead,
      bottom
    }
  }
</script>
