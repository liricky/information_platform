<template>
    <div>
      <tophead></tophead>
      <div class="center">
        <h1>封禁内容</h1>
        <Row class="cardbox" style="background:#eee;padding:20px">
          <Col class="cardcol" span="25" v-for="(msg,index) in msg">
            <Card class="card" :bordered="true">
              <h1 class="headline" slot="title">封禁类别:{{msg.type}}</h1>
              <h3>封禁内容：{{msg.content}}</h3>
              <h3>封禁原因：{{msg.reason}}</h3>
              <h3>封禁结束时间：{{msg.date}}</h3>
            </Card>
          </Col>
        </Row>
      </div>
      <br>
      <div>
        <div class="back">
          <div class="leftback">
            <font id="font" size="4">请选择申诉类别</font>
            <br>
            <Select v-model="value3" size="large" style="width:100px">
              <Option v-for="item in optionlist" :value="item.id" :key="item.id">{{item.label}}</Option>
            </Select>
          </div>
          <div class="rightback">
            <div id="inputbox">
              <br>
              <Input class="input" v-model="value1" size="large" placeholder="申诉标题"/>
              <Input class="input" v-model="value2" type="textarea" :rows="25" placeholder="申诉理由" />
              <br>
              <br>
              <button id="sendbutton" type="primary" size="large" @click="sendappeal">发 送</button>
            </div>
            <br>
          </div>
        </div>
      </div>
      <bottom class="bottom"></bottom>
    </div>
</template>
<style scoped>
  #sendbutton{
    margin: auto;
  }
  .leftback{
    width: 25%;
    margin: auto;
    float: left;
    background: white;
    border: 2px solid black;
  }
  .rightback{
    margin: auto;
    float: right;
    width: 70%;
    background: lightgrey;
  }
  .back{
    width: 80%;
    display: flex;
    margin: auto;
  }
  #inputbox{
    margin: auto;
  }
  .input{
    margin: auto;
    width: 85%;
  }
  .center{
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
            optionlist:[
              {
                id: 0,
                value: "论坛封禁",
                label: "论坛封禁"
              },
              {
                id: 1,
                value: "互助封禁",
                label: "互助封禁"
              }
            ],
            type: '',
            value1: '',
            value2: '',
            value3: '',
            status1: '',
            errormsg1: '',
            status2: '',
            errormsg2: '',
            msg: [],
          }
      },
      components: {
        tophead,
        bottom
      },
      created(){
        this.init();
      },
      methods:{
        sendappeal(){
          if(this.value1 === '' || this.value2 === '' || this.value3 === '')
            this.$Message.info('申诉类别、申述标题及申诉内容不能为空！');
          else {
            axios({
              url: apiRoot + '/appeal/send',
              headers: {Authorization: this.$store.state.token},
              method: 'post',
              data: {
                userid: this.$store.state.userId,
                type: this.value3,
                title: this.value1,
                reason: this.value2,
              }
            }).then((response) => {
              let res = response.data;
              if (res.status === "success") {
                this.status1 = res.status;
                this.$Message.info('发送申诉成功!');
                this.value1 = '';
                this.value2 = '';
                this.value3 = '';
              } else {
                this.status1 = res.status;
                this.errormsg1 = res.message;
                this.$Message.info('发送申诉失败： ' + this.errormsg1);
              }
            })
          }
        },
        init(){
          axios({
            url: apiRoot + '/appeal/getdetail/' + this.$store.state.userId,
            headers: {Authorization: this.$store.state.token},
            method: 'get',
          }).then((response) => {
            let res = response.data;
            if (res.status === "success") {
              this.status2 = res.status;
              this.msg = res.data;
            } else {
              this.status2 = res.status;
              this.errormsg2 = res.message;
            }
          })
        }
      }
    }
</script>
