<template>
    <div>
      <tophead></tophead>
        <div class="center">
          <font size="6">发帖</font>
          <divider></divider>
          <Input v-model="value1" type="textarea" :rows="2" placeholder="帖子标题" />
          <divider></divider>
          <Input v-model="value1" type="textarea" :rows="10" placeholder="帖子内容" />
          <divider></divider>
          <Button type="info" size="large" icon="md-log-out" @click=send>发送</Button>
        </div>
      <br>
      <bottom></bottom>
    </div>
</template>
<style>
  .center{
    width: 80%;
    margin: auto;
  }
</style>
<script>
  import tophead from '@/components/Head'
  import bottom from '@/components/Bottom'

    export default {
        data() {
            return {
                msg: 'hello vue'
            }
        },
        components: {
          tophead,
          bottom
        }
    }
</script>
