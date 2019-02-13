<template>
    <div>
      <tophead></tophead>
      <!--<h1>{{find}}</h1>-->
      <div class="center">
        <RadioGroup v-model="find" class="radiogroup">
          <Radio label=0>
            <span><font size="5px">用户昵称</font></span>
          </Radio>
          <Radio label=1>
            <span><font size="5px">用户id</font></span>
          </Radio>
        </RadioGroup>
        <Input v-model="value" placeholder="请输入信息" />
        <Button type="primary" size="large" @click="search"><font size="3">搜索</font></Button>
        <Row class="cardbox" style="background:#eee;padding:20px">
          <Col class="cardcol" span="25" v-for="(msg,index) in msg" :key="msg.id">
            <div @click=jumpUserDetail(msg.id)>
              <Card class="card" :bordered="true">
                <h3 class="headline" slot="title">用户id：{{msg.id}}</h3>
                <h3>用户昵称：{{msg.nickname}}</h3>
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
    import axios from 'axios'

    export default {
        data() {
            return {
              status1: '',
              errormsg1: '',
              msg: [],
              find: '',
              value: '',
            }
        },
        components: {
          tophead,
          bottom
        },
        methods: {
          jumpUserDetail(id) {
            this.$router.push({
              path: '/UserDetail',
              query: {
                id: id
              }
            })
          },
          search(){
            // console.log(this.find);
            if(this.find === '' || this.value === '')
              this.$Message.info('搜索方式及搜索信息不能为空!');
            else {
              axios.get("/api/user/findfriend", {
                type: this.find,
                id: this.value,
              }).then((response) => {
                let res = response.data;
                if (res.status === "success") {
                  this.msg = res.userlist;
                  this.status1 = res.status;
                  this.$Message.info('搜索完成!');
                  this.value = '';
                  this.find = '';
                } else {
                  this.status1 = res.status;
                  this.errormsg1 = res.message;
                  this.$Message.info('搜索失败： ' + this.errormsg1);
                }
              })
            }
          }
        }
    }
</script>
