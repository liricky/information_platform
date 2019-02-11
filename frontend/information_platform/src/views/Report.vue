<template>
    <div>
      <tophead></tophead>
      <div class="center">
        <font size="6">举报</font>
        <divider></divider>
        <RadioGroup v-model="sendto" class="radiogroup">
          <Radio label="forum">
            <span><font size="5px">论坛违规</font></span>
          </Radio>
          <Radio label="help">
            <span><font size="5px">任务违规</font></span>
          </Radio>
        </RadioGroup>
        <Input v-model="value1" type="textarea" :rows="5" placeholder="举报原因" />
        <divider></divider>
        <Button type="primary" size="large"><font size="4">提交</font></Button>
      </div>
      <br>
      <bottom></bottom>
    </div>
</template>
<style scoped>
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
              sendto: false
            }
        },
        components: {
          tophead,
          bottom
        }
    }
</script>
