<template>
  <div>
    <tophead></tophead>
    <Tabs type="card" id="cardbox">
      <TabPane label="收件箱">
        <font size="10" v-if="status1 === 'fail'">信息获取失败</font>
        <br>
        <font size="4" v-if="status1 === 'fail'">{{errormsg1}}</font>
        <Dropdown id="drop" v-if="status1 === 'success'" placement="bottom-start" @on-click="test1">
          <a href="javascript:void(0)">
            <font size="3px">筛选信息</font>
            <Icon type="ios-arrow-down"></Icon>
          </a>
          <DropdownMenu slot="list">
            <DropdownItem name="read"><font size="2px">已读信息</font></DropdownItem>
            <DropdownItem name="unread"><font size="2px">未读信息</font></DropdownItem>
          </DropdownMenu>
        </Dropdown>
        <Row class="cardbox" style="background:#eee;padding:20px" v-if="status1 === 'success'">
          <Col class="cardcol" span="25" v-for="(msg,index) in msg" :key="msg.messageid">
            <div @click=show(msg.messageid)>
              <Card class="card" :bordered="true">
                <h1 id="headline" slot="title">{{msg.status}} {{msg.title}}</h1>
                <h3>发送人：{{msg.senderid}} {{msg.sendernickname}}</h3>
                <h3>发送时间：{{msg.date}}</h3>
              </Card>
            </div>
          </Col>
        </Row>
      </TabPane>
      <TabPane label="写信">
        <div class="background">
          <font size="10" v-if="status3 === 'fail'">信息获取失败</font>
          <br>
          <font size="4" v-if="status3 === 'fail'">{{errormsg3}}</font>
          <div class="leftback">
            <RadioGroup v-model="sendto" class="radiogroup" vertical>
              <Radio class="sendperson" v-for="(friend,index) in friend" :label="friend.userid">
                <Icon class="icon" type="md-person" size="20"></Icon>
                <span><font size="5px">{{friend.usernickname}}</font></span>
              </Radio>
            </RadioGroup>
          </div>
          <div class="rightback">
            <br>
            <div id="inputbox">
              <Input class="input" v-model="value1" size="large" placeholder="标题"/>
              <Input class="input" v-model="value2" type="textarea" :rows="40" placeholder="正文" />
            </div>
            <br>
            <div id="sendbuttonbox">
              <button id="sendbutton" type="primary" size="large">发 送</button>
            </div>
            <br>
          </div>
        </div>
      </TabPane>
      <TabPane label="已发送">
        <font size="10" v-if="status2 === 'fail'">信息获取失败</font>
        <br>
        <font size="4" v-if="status2 === 'fail'">{{errormsg2}}</font>
        <Row class="cardbox" style="background:#eee;padding:20px">
          <Col class="cardcol" span="25" v-for="(rev,index) in rev">
            <div @click=show(rev.messageid)>
              <Card class="card" :bordered="true">
                <h1 class="headline" slot="title">{{rev.title}}</h1>
                <h3>收信人：{{rev.receiverid}} {{rev.receivernickname}}</h3>
                <h3>发送时间：{{rev.date}}</h3>
              </Card>
            </div>
          </Col>
        </Row>
      </TabPane>
    </Tabs>
    <bottom></bottom>
    <Modal v-model="modal1">
      <h1 slot="header" style="text-align:center">
        <span>{{this.box_title}}</span>
      </h1>
      <div style="text-align:center">
        <h2>日期：{{this.box_date}}</h2>
        <h3>{{this.box_content}}</h3>
      </div>
      <div slot="footer">
      </div>
    </Modal>
  </div>
</template>
<style scoped>
  #cardbox{
    width: 80%;
    margin: auto;
  }
  .cardcol{
    margin: 4px auto;
  }
  .leftback{
    float: left;
    width: 23%;
    background: white;
    border: 2px solid black;
  }
  .rightback{
    float: right;
    width: 75%;
    background: lightgrey;
  }
  #sendbutton{
    float:bottom;
  }
  .input{
    margin: auto;
    width: 85%;
  }
  #inputbox{
    margin: auto;
  }
  #sendbuttonbox{
    margin: auto;
  }
  .sendperson{
    text-align: left;
    margin: auto;
    position: relative;
    bottom: 3px;
  }
  .radiogroup{
    float: left;
    position: relative;
    left:4%;
    margin: auto;
   }
  .icon{
    position: relative;
    bottom: 4px;
  }
</style>
<script>
  import tophead from '@/components/Head.vue'
  import bottom from '@/components/Bottom.vue'
  import axios from 'axios'

    export default {
        data() {
            return {
              status1: '',
              errormsg1: '',
              status2: '',
              errormsg2: '',
              status3: '',
              errormsg3: '',
              status4: '',
              errormsg4: '',
              value1: '',
              value2: '',
              sendto: "",
              modal1: false,
              box_messageid: "",
              box_title: "",
              box_content: "",
              box_date: "",
              friend:[],
              msg: [],
              rev: [],

              test: 0,
            }
        },
        created(){
          this.getrev();
          this.getsent();
          this.getfriend();
        },
        methods:{
          getrev(){
            axios.get("/message/receive", {
              token: this.$store.state.token,
              userId: this.$store.state.userId,
            }).then((response) => {
              let res = response.data;
              if(res.status === "success") {
                this.msg = res.receivemsg;
                this.status1 = res.status;
              } else {
                this.status1 = res.status;
                this.errormsg1 = res.message;
              }
            })
          },
          getsent(){
            axios.get("/message/sent", {
              token: this.$store.state.token,
              userId: this.$store.state.userId,
            }).then((response) => {
              let res = response.data;
              if(res.status === "success") {
                this.rev = res.sendmsg;
                this.status2 = res.status;
              } else {
                this.status2 = res.status;
                this.errormsg2 = res.message;
              }
            })
          },
          getfriend(){
            axios.get("/user/getfriend", {
              token: this.$store.state.token,
              userId: this.$store.state.userId,
            }).then((response) => {
              let res = response.data;
              if(res.status === "success") {
                this.friend = res.friendmsg;
                this.status3 = res.status;
              } else {
                this.status3 = res.status;
                this.errormsg3 = res.message;
              }
            })
          },
          show(id){
            this.modal1 = true;
            axios.get("/message/detail",{
              token: this.$store.state.token,
              userId: this.$store.state.userId,
              messageid: id,
            }).then((response)=>{
              let res = response.data;
              if(res.status === "success"){
                // console.log(id);
                this.status4 = res.status;
                this.box_messageid = res.msg.messageid;
                this.box_title = res.msg.title;
                this.box_date = res.msg.date;
                this.box_content = res.msg.content;
                // console.log(this.msg);
                for(let i of this.msg){
                  // console.log("1");
                  // console.log(i);
                  // console.log(id);
                  if(i.messageid === id){
                    // console.log(i.messageid);
                    this.$set(i,"status","");
                    break;
                  }
                }
              }else{
                this.status4 = res.status;
                this.errormsg4 = res.message;
              }
            })
          },
          test1(name){
            // console.log(this.msg[0].title);
            for(var i=this.msg.length;i>0;i--)
              this.$delete(this.msg,i);
            if(name === "unread") {
              this.$set(this,"msg",[{
                id: 0,
                title: '史蒂夫·乔布斯99',
                content: '史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date: '2012-2-2',
                sender: '刘某人',
                status: 'new'
              },{
                id: 0,
                title: '史蒂夫·乔布斯97',
                content: '史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date: '2012-2-2',
                sender: '刘某人',
                status: 'new'
              }]);
            } else{
              this.$set(this,"rev",[{
                id: 0,
                title: '史蒂夫·乔布斯99',
                content: '史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date: '2012-2-2',
                sender: '刘某人',
                status: ''
              },{
                id: 0,
                title: '史蒂夫·乔布斯98',
                content: '史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date: '2012-2-2',
                sender: '刘某人',
                status: ''
              }]);
            }
            // console.log(this.msg[0].title);
            // console.log(this.msg);
          }
        },
        components: {
          bottom,
          tophead,
        },
        // watch: {
        //   'this.$store.token': 'getrev',
        //   'this.$store.token': 'getsent',
        //   'this.$store.token': 'getfriend',
        // }
    }
</script>
