<template>
  <div>
    <tophead></tophead>
    <Tabs type="card" id="cardbox">
      <TabPane label="收件箱">
        <Dropdown id="drop" placement="bottom-start" @on-click="test1">
          <a href="javascript:void(0)">
            <font size="3px">筛选信息</font>
            <Icon type="ios-arrow-down"></Icon>
          </a>
          <DropdownMenu slot="list">
            <DropdownItem name="read"><font size="2px">已读信息</font></DropdownItem>
            <DropdownItem name="unread"><font size="2px">未读信息</font></DropdownItem>
          </DropdownMenu>
        </Dropdown>
        <Row class="cardbox" style="background:#eee;padding:20px">
          <Col class="cardcol" span="25" v-for="(msg,index) in msg.slice(0,6)" :key="msg.title">
            <Card class="card" :bordered="true">
              <h1 id="headline" slot="title">{{msg.status}} {{msg.title}}</h1>
              <button class="detail" type="primary" @click=show(msg.id)>详情</button>
              <h3>发送人：{{msg.sender}}</h3>
              <h3>发送时间：{{msg.date}}</h3>
            </Card>
          </Col>
        </Row>
      </TabPane>
      <TabPane label="写信">
        写信
      </TabPane>
      <TabPane label="已发送">
        <Row class="cardbox" style="background:#eee;padding:20px">
          <Col class="cardcol" span="25" v-for="(rev,index) in rev.slice(0,6)">
            <Card class="card" :bordered="true">
              <h1 class="headline" slot="title">{{rev.title}}</h1>
              <button class="detail" type="primary" @click=show1(rev.id)>详情</button>
              <h3>收信人：{{rev.receiver}}</h3>
              <h3>发送时间：{{rev.date}}</h3>
            </Card>
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
        <h2>发送人：{{this.box_sender}} 发送日期：{{this.box_date}}</h2>
        <h3>{{this.box_content}}</h3>
      </div>
      <div slot="footer">
      </div>
    </Modal>
    <Modal v-model="modal2">
      <h1 slot="header" style="text-align:center">
        <span>{{this.box_title1}}</span>
      </h1>
      <div style="text-align:center">
        <h2>收件人：{{this.box_sender1}} 发送日期：{{this.box_date1}}</h2>
        <h3>{{this.box_content1}}</h3>
      </div>
      <div slot="footer">
      </div>
    </Modal>
  </div>
</template>
<style scoped>
  #cardbox{
    width: 85%;
    margin: auto;
  }
  .cardcol{
    margin: 4px auto;
  }
  .detail{
    position: relative;
    left: 45%;
    bottom: 60px;
  }
</style>
<script>
  import tophead from '@/components/Head.vue'
  import bottom from '@/components/Bottom.vue'

    export default {
        data() {
            return {
              modal1: false,
              modal2: false,
              errorTip: false,
              box_title: "test_title",
              box_sender: "test_sender",
              box_date: "test_date",
              box_content: "test_content",
              box_title1: "test_title1",
              box_sender1: "test_sender1",
              box_date1: "test_date1",
              box_content1: "test_content1",
              msg:[
                {
                  id: 0,
                  title:'史蒂夫·乔布斯1',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  sender:'刘某人',
                  status:'new'
                },
                {
                  id: 1,
                  title:'史蒂夫·乔布斯2',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  sender:'刘某人',
                  status:'new'
                },
                {
                  id: 2,
                  title:'史蒂夫·乔布斯3',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  sender:'刘某人',
                  status:'new'
                },
                {
                  id: 3,
                  title:'史蒂夫·乔布斯4',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  sender:'刘某人',
                  status:''
                },
                {
                  id: 4,
                  title:'史蒂夫·乔布斯5',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  sender:'刘某人',
                  status:''
                },
                {
                  id: 5,
                  title:'史蒂夫·乔布斯6',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  sender:'刘某人',
                  status:''
                },
              ],
              rev:[
                {
                  id: 0,
                  title:'史蒂夫·乔布斯7',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  receiver:'刘某人'
                },
                {
                  id: 1,
                  title:'史蒂夫·乔布斯8',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  receiver:'刘某人'
                },
                {
                  id: 2,
                  title:'史蒂夫·乔布斯9',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  receiver:'刘某人'
                },
                {
                  id: 3,
                  title:'史蒂夫·乔布斯10',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  receiver:'刘某人'
                },
                {
                  id: 4,
                  title:'史蒂夫·乔布斯11',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  receiver:'刘某人'
                },
                {
                  id: 5,
                  title:'史蒂夫·乔布斯12',
                  content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                  date:'2012-2-2',
                  receiver:'刘某人'
                },
              ]
            }
        },
        methods:{
          show(id){
            this.modal1 = true;
            axios.post("/message/getbyid",{
              id:id
            }).then((response)=>{
              let res = response.data;
              if(res.status === "success"){
                this.errorTip = false;
                this.box_title = res.title;
                this.box_sender = res.sender;
                this.box_date = res.date;
                this.box_content = res.content;
              }else{
                this.errorTip = true;
              }
            })
          },
          show1(id){
            this.modal2 = true;
            axios.post("/message/getbyid1",{
              id:id
            }).then((response)=>{
              let res = response.data;
              if(res.status === "success"){
                this.errorTip = false;
                this.box_title1 = res.title;
                this.box_sender1 = res.sender;
                this.box_date1 = res.date;
                this.box_content1 = res.content;
              }else{
                this.errorTip = true;
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
              this.$set(this,"msg",[{
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
        }
    }
</script>
