<template>
    <div>
      <tophead></tophead>
      <div class="center">
        <RadioGroup v-model="find" class="radiogroup">
          <Radio label="forum">
            <span><font size="5px">用户昵称</font></span>
          </Radio>
          <Radio label="help">
            <span><font size="5px">用户id</font></span>
          </Radio>
        </RadioGroup>
        <Input v-model="value" placeholder="请输入信息" />
        <Button type="primary" size="large"><font size="3">搜索</font></Button>
        <Row class="cardbox" style="background:#eee;padding:20px">
          <Col class="cardcol" span="25" v-for="(msg,index) in msg" :key="msg.id">
            <div @click=jumpUserDetail(msg.id)>
              <Card class="card" :bordered="true">
                <h3 class="headline" slot="title">用户id：{{msg.id}}</h3>
                <h3>用户昵称：{{msg.name}}</h3>
              </Card>
            </div>
          </Col>
        </Row>
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
  .cardcol{
    margin: 4px auto;
  }
</style>
<script>
    import tophead from '@/components/Head'
    import bottom from '@/components/Bottom'

    export default {
        data() {
            return {
              value: '',
              find: false,
              msg:[
                {
                  id: "16122075",
                  name: "li"
                },
                {
                  id: "16122076",
                  name: "xx"
                }
              ]
            }
        },
        components: {
          tophead,
          bottom
        },
        methods: {
          jumpUserDetail(id) {
            this.$router.push({path: '/UserDetail'})
          }
        }
    }
</script>
