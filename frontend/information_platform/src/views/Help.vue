<template>
    <div>
      <tophead></tophead>
      <div class="center">
        <Button type="primary" @click="createmission"><font size="4">发布任务</font></Button>
        <divider></divider>
        <Tabs class="cards" type="card">
          <TabPane label="大厅">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post,index) in post" :key="post.missionid">
                <Card class="card" :bordered="true">
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport(post.missionid,post.authorid)"/>
                  <div class="leftback">
                    <div>
                      <font size="4" @click="jumpUserDetail(post.authorid)">by:{{post.authorid}} {{post.authornickname}}</font>
                      <br>
                      <font size="4">开始时间:{{post.startdate}}<br>截止时间:{{post.enddate}}</font>
                    </div>
                    <div>
                      <font size="3">{{post.content}}</font>
                      <div class="rightback">
                        <Button type="primary" @click="getmission(post.missionid)"><font size="2">认领</font></Button>
                      </div>
                    </div>
                  </div>
                </Card>
              </Col>
            </Row>
          </TabPane>
          <TabPane label="已认领">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post1,index) in post1" :key="post1.missionid">
                <Card class="card" :bordered="true">
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport(post1.missionid,post1.authorid)"/>
                  <div class="leftback">
                    <div>
                      <font size="4" @click="jumpUserDetail(post1.authorid)">by:{{post1.authorid}} {{post1.authornickname}}</font>
                      <br>
                      <font size="4">开始时间:{{post1.startdate}}<br>截止时间:{{post1.enddate}}</font>
                    </div>
                    <div>
                      <font size="3">{{post1.content}}</font>
                      <div class="rightback">
                        <font size="3">发布人联系方式：{{post1.phone}}</font>
                        <br>
                        <font size="3">任务状态：{{post1.missionstatus}}</font>
                        <br>
                        <Button type="primary" @click="cancelmission(post1.missionid)"><font size="2">放弃任务</font></Button>
                        &nbsp;&nbsp;
                        <Button type="primary" @click="finishmission1(post1.missionid)"><font size="2">任务完成</font></Button>
                      </div>
                    </div>
                  </div>
                </Card>
              </Col>
            </Row>
          </TabPane>
          <TabPane label="已发布">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post2,index) in post2" :key="post2.missionid">
                <Card class="card" :bordered="true">
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport(post2.missionid,post2.acceptid)"/>
                  <div class="leftback">
                    <div>
                      <font size="4" @click="jumpUserDetail(post2.acceptid)">by:{{post2.acceptid}} {{post2.acceptnickname}}</font>
                      <br>
                      <font size="4">开始时间:{{post2.startdate}}<br>截止时间：{{post2.enddate}}</font>
                    </div>
                    <div>
                      <font size="3">{{post2.content}}</font>
                      <div class="rightback">
                        <font size="3">任务状态：{{post2.missionstatus}}</font>
                        <br>
                        <font size="3">认领人联系方式：{{post2.phone}}</font>
                        <br>
                        <Button type="primary" @click="cancelmission(post2.missionid)"><font size="2">放弃任务</font></Button>
                        &nbsp;&nbsp;
                        <Button type="primary" @click="finishmission2(post2.missionid)"><font size="2">任务完成</font></Button>
                      </div>
                    </div>
                  </div>
                </Card>
              </Col>
            </Row>
          </TabPane>
          <TabPane label="发布任务已完成">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post3,index) in post3" :key="post3.missionid">
                <Card class="card" :bordered="true">
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport(post3.missionid,post3.acceptid)"/>
                  <div class="leftback">
                    <div>
                      <font size="4" @click="jumpUserDetail(post3.acceptid)">by:{{post3.acceptid}} {{post3.acceptnickname}}</font>
                      <br>
                      <font size="4">开始时间:{{post3.startdate}}<br>完成时间:{{post3.finishdate}}</font>
                    </div>
                    <div>
                      <font size="3">{{post3.content}}</font>
                    </div>
                  </div>
                </Card>
              </Col>
            </Row>
          </TabPane>
          <TabPane label="认领任务已完成">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post4,index) in post4" :key="post4.missionid">
                <Card class="card" :bordered="true">
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport(post4.missionid,post4.authorid)"/>
                  <div class="leftback">
                    <div>
                      <font size="4" @click="jumpUserDetail(post4.authorid)">by:{{post4.authorid}} {{post4.authornickname}}</font>
                      <br>
                      <font size="4">开始时间:{{post4.startdate}}<br>完成时间:{{post4.finishdate}}</font>
                    </div>
                    <div>
                      <font size="3">{{post4.content}}</font>
                    </div>
                  </div>
                </Card>
              </Col>
            </Row>
          </TabPane>
        </Tabs>
      </div>
      <bottom></bottom>
      <Modal
        v-model="modal1"
        title="认领任务"
        @on-ok="ok"
        @on-cancel="cancel">
        <Input v-model="value1" type="textarea" :rows="2" placeholder="请输入联系方式！" />
      </Modal>
      <Modal
        v-model="modal2"
        title="放弃任务"
        @on-ok="ok1"
        @on-cancel="cancel1">
        <Input v-model="value2" type="textarea" :rows="2" placeholder="请解释原因！" />
      </Modal>
      <Modal
        v-model="modal3"
        title="发布任务"
        @on-ok="ok2"
        @on-cancel="cancel2">
        <Input v-model="value3" type="textarea" :rows="5" placeholder="请输入任务内容！" />
        <Input v-model="value4" type="textarea" :rows="2" placeholder="请输入联系方式！" />
        <font size="3">设置任务截止时间：</font>
        <Row>
          <Col span="12">
            <DatePicker @on-change="handle1" type="date" placeholder="Select date" style="width: 200px" ></DatePicker>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <TimePicker @on-change="handle2" type="time" placeholder="Select time" style="width: 168px"></TimePicker>
          </Col>
        </Row>
      </Modal>
    </div>
</template>
<style scoped>
  .card{
    margin: auto;
    border: 1px solid black;
  }
  .center{
    width: 80%;
    margin: auto;
  }
  .cardcol{
    margin: 4px auto;
  }
  .flag{
    float: right;
  }
</style>
<script>
  import tophead from '@/components/Head'
  import bottom from '@/components/Bottom'
  import axios from 'axios'

    export default {
        data() {
            return {
              post: [],
              post1: [],
              post2: [],
              post3: [],
              post4: [],
              status1: '',
              errormsg1: '',
              status2: '',
              errormsg2: '',
              status3: '',
              errormsg3: '',
              status4: '',
              errormsg4: '',
              status5: '',
              errormsg5: '',
              status6: '',
              errormsg6: '',
              status7: '',
              errormsg7: '',
              status8: '',
              errormsg8: '',
              status9: '',
              errormsg9: '',
              status10: '',
              errormsg10: '',
              status11: '',
              errormsg11: '',
              type: '',
              value1: '',
              value2: '',
              value3: '',
              value4: '',
              modal1: false,
              modal2: false,
              modal3: false,
              temp: '',
              datevalue: null,
              timevalue: null,
              sign: '',
            }
        },
        components: {
          tophead,
          bottom
        },
      created(){
        this.helpall();
        this.helpongoing();
        this.helpsent();
        this.helpfinish();
        this.checktype();
      },
      methods: {
        getmission(id){
          if(this.sign)
            this.$Message.info("您已被封禁，无法使用该功能，如有疑问可进行申诉！");
          else {
            this.modal1 = true;
            this.temp = id;
          }
        },
        ok () {
          if(this.value1 === '')
            this.$Message.info('联系方式不能为空！');
          else {
            axios.post("/help/claim", {
              missionid: this.temp,
              token: this.$store.state.token,
              userid: this.$store.state.userId,
              phone: this.value1,
            }).then((response) => {
              let res = response.data;
              if (res.status === "success") {
                this.status5 = res.status;
                this.$Message.info('认领成功！');
                this.value1 = '';
                let x = 0;
                for(let i of this.post){
                  if(i.missionid === this.temp){
                    this.post.splice(x,1);
                    break;
                  }
                  x++;
                }
              } else {
                this.status5 = res.status;
                this.errormsg5 = res.message;
                this.$Message.info('认领失败： ' + this.errormsg5);
              }
            });
          }
        },
        cancel () {
          this.$Message.info('已取消认领！');
        },
        cancelmission(id){
          if(this.sign)
            this.$Message.info("您已被封禁，无法使用该功能，如有疑问可进行申诉！");
          else {
            this.modal2 = true;
            this.temp = id;
          }
        },
        ok1 () {
          if(this.value2 === '')
            this.$Message.info('原因不能为空！');
          else {
            axios.post("/help/cancel", {
              missionid: this.temp,
              token: this.$store.state.token,
              userid: this.$store.state.userId,
            }).then((response) => {
              let res = response.data;
              if (res.status === "success") {
                this.status6 = res.status;
                this.$Message.info('放弃任务成功！');
                this.value1 = '';
                let x = 0;
                for(let i of this.post1){
                  if(i.missionid === this.temp){
                    this.post1.splice(x,1);
                    break;
                  }
                  x++;
                }
                x = 0;
                for(let i of this.post2){
                  if(i.missionid === this.temp){
                    this.post2.splice(x,1);
                    break;
                  }
                  x++;
                }
              } else {
                this.status6 = res.status;
                this.errormsg6 = res.message;
                this.$Message.info('放弃任务失败： ' + this.errormsg6);
              }
            });
          }
        },
        cancel1 () {
          this.$Message.info('取消放弃任务！');
        },
        finishmission1(id){
          if(this.sign)
            this.$Message.info("您已被封禁，无法使用该功能，如有疑问可进行申诉！");
          else {
            axios.post("/help/claimfinish", {
              missionid: id,
              token: this.$store.state.token,
              userid: this.$store.state.userId,
            }).then((response) => {
              let res = response.data;
              if (res.status === "success") {
                this.status7 = res.status;
                this.$Message.info('确认完成任务成功！');
                let x = 0;
                for(let i of this.post1){
                  if(i.missionid === id){
                    this.post1.splice(x,1);
                    break;
                  }
                  x++;
                }
                x = 0;
                for(let i of this.post2){
                  if(i.missionid === id){
                    this.post2.splice(x,1);
                    break;
                  }
                  x++;
                }
              } else {
                this.status7 = res.status;
                this.errormsg7 = res.message;
                this.$Message.info('确认完成任务失败： ' + this.errormsg7);
              }
            })
          }
        },
        finishmission2(id){
          if(this.sign)
            this.$Message.info("您已被封禁，无法使用该功能，如有疑问可进行申诉！");
          else {
            axios.post("/help/sentfinish", {
              missionid: id,
              token: this.$store.state.token,
              userid: this.$store.state.userId,
            }).then((response) => {
              let res = response.data;
              if (res.status === "success") {
                this.status8 = res.status;
                this.$Message.info('确认完成任务成功！');
                let x = 0;
                for(let i of this.post1){
                  if(i.missionid === id){
                    this.post1.splice(x,1);
                    break;
                  }
                  x++;
                }
                x = 0;
                for(let i of this.post2){
                  if(i.missionid === id){
                    this.post2.splice(x,1);
                    break;
                  }
                  x++;
                }
              } else {
                this.status8 = res.status;
                this.errormsg8 = res.message;
                this.$Message.info('确认完成任务失败： ' + this.errormsg8);
              }
            })
          }
        },
        createmission(){
          if(this.sign)
            this.$Message.info("您已被封禁，无法使用该功能，如有疑问可进行申诉！");
          else {
            this.modal3 = true;
          }
        },
        ok2 () {
          if(this.value3 === '' || this.value4 === '' || this.datevalue === null || this.timevalue === null)
            this.$Message.info('任务内容、联系方式及设置时间不能为空');
          else {
            axios.post("/help/send", {
              token: this.$store.state.token,
              userid: this.$store.state.userId,
              content: this.value3,
              phone: this.value4,
              enddate: this.datevalue + " " + this.timevalue,
            }).then((response) => {
              let res = response.data;
              if (res.status === "success") {
                this.status9 = res.status;
                this.$Message.info('发布任务成功！');
                this.value3 = '';
                this.value4 = '';
                this.datevalue = null;
                this.timevalue = null;
              } else {
                this.status9 = res.status;
                this.errormsg9 = res.message;
                this.$Message.info('发布任务失败： ' + this.errormsg9);
              }
            });
          }
        },
        cancel2 () {
          this.$Message.info('取消发布任务！');
        },
        jumpToReport(id,author) {
          this.$router.push({
            path: '/Report',
            query: {
              id : id,
              type: 3,
              reportid: author
            }
          })
        },
        // jumpUserDetail(id) {
        //   this.$router.push({path: '/UserDetail'})
        // },
        jumpUserDetail(id) {
          this.$router.push({
            path: '/UserDetail',
            query: {
              id : id
            }
          })
        },
        helpall(){
          axios.get("/help/all", {
          }).then((response) => {
            let res = response.data;
            if(res.status === "success") {
              this.post = res.mission;
              this.status1 = res.status;
            } else {
              this.status1 = res.status;
              this.errormsg1 = res.message;
            }
          })
        },
        helpongoing(){
          axios.get("/help/ongoing", {
            token: this.$store.state.token,
            userId: this.$store.state.userId,
          }).then((response) => {
            let res = response.data;
            if(res.status === "success") {
              this.post1 = res.mission;
              this.status2 = res.status;
            } else {
              this.status2 = res.status;
              this.errormsg2 = res.message;
            }
          })
        },
        helpsent(){
          axios.get("/help/sent", {
            token: this.$store.state.token,
            userId: this.$store.state.userId,
          }).then((response) => {
            let res = response.data;
            if(res.status === "success") {
              this.post2 = res.mission;
              this.status3 = res.status;
            } else {
              this.status3 = res.status;
              this.errormsg3 = res.message;
            }
          })
        },
        helpfinish(){
          axios.get("/help/sendfinish", {
            token: this.$store.state.token,
            userId: this.$store.state.userId,
          }).then((response) => {
            let res = response.data;
            if(res.status === "success") {
              this.post3 = res.mission;
              this.status4 = res.status;
            } else {
              this.status4 = res.status;
              this.errormsg4 = res.message;
            }
          });
          axios.get("/help/getfinish", {
            token: this.$store.state.token,
            userId: this.$store.state.userId,
          }).then((response) => {
            let res = response.data;
            if(res.status === "success") {
              this.post4 = res.mission;
              this.status11 = res.status;
            } else {
              this.status11 = res.status;
              this.errormsg11 = res.message;
            }
          })
        },
        handle1(date){
          this.datevalue = date;
        },
        handle2(time){
          this.timevalue = time;
        },
        checktype(){
          axios.get("/appeal/get", {
            token: this.$store.state.token,
            userId: this.$store.state.userId,
          }).then((response) => {
            let res = response.data;
            if(res.status === "success") {
              this.status10 = res.status;
              if(res.type === 2 || res.type === 3)
                this.sign = true;
              else
                this.sign = false;
            } else {
              this.status10 = res.status;
              this.errormsg10 = res.message;
            }
          })
        }
      }
    }
</script>
