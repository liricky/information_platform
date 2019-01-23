<template>
  <div>
    <tophead></tophead>
    <div class="center">
      forumdetail
    </div>
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
        msg: 'hello vue'
      }
    },
    components: {
      tophead,
      bottom
    }
  }
</script>
