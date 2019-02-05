<template>
    <div>
      <tophead></tophead>
      <div class="center">
        <Button type="primary" @click="claim2"><font size="4">发布任务</font></Button>
        <Tabs class="cards" type="card">
          <TabPane label="大厅">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post,index) in post" :key="post.missionid">
                <Card class="card" :bordered="true">
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport(post.missionid,post.author)"/>
                  <div class="leftback">
                    <div>
                      <font size="4" @click="jumpUserDetail(post.authorid)">by:{{post.authorid}} {{post.authornickname}}</font>
                      <br>
                      <font size="4">开始时间:{{post.startdate}}<br>截止时间:{{post.enddate}}</font>
                    </div>
                    <div>
                      <font size="3">{{post.content}}</font>
                      <div class="rightback">
                        <Button type="primary" @click="claim(post.missionid)"><font size="2">认领</font></Button>
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
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport(post1.missionid)"/>
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
                        <Button type="primary" @click="claim1(post1.missionid)"><font size="2">放弃任务</font></Button>
                        &nbsp;&nbsp;
                        <Button type="primary" @click="claim1(post1.missionid)"><font size="2">任务完成</font></Button>
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
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport(post2.missionid)"/>
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
                        <Button type="primary" @click="claim1(post2.missionid)"><font size="2">放弃任务</font></Button>
                        &nbsp;&nbsp;
                        <Button type="primary" @click="claim1(post2.missionid)"><font size="2">任务完成</font></Button>
                      </div>
                    </div>
                  </div>
                </Card>
              </Col>
            </Row>
          </TabPane>
          <TabPane label="已完成">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post3,index) in post3" :key="post3.missionid">
                <Card class="card" :bordered="true">
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport(post3.missionid)"/>
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
            <DatePicker type="date" placeholder="Select date" style="width: 200px"></DatePicker>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <TimePicker type="time" placeholder="Select time" style="width: 168px"></TimePicker>
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
              value3: '',
              value4: '',
              modal1: false,
              modal2: false,
              modal3: false,
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
      },
      methods: {
        claim(id){
          this.modal1 = true;
        },
        ok () {
          this.$Message.info('认领成功！');
          this.value1 = '';
        },
        cancel () {
          this.$Message.info('已取消认领！');
        },
        claim1(id){
          this.modal2 = true;
        },
        ok1 () {
          this.$Message.info('已放弃任务！');
          this.value2 = '';
        },
        cancel1 () {
          this.$Message.info('取消放弃任务！');
        },
        claim2(){
          this.modal3 = true;
        },
        ok2 () {
          this.$Message.info('已发布任务！');
          this.value3 = '';
          this.value4 = '';
        },
        cancel2 () {
          this.$Message.info('取消发布任务！');
        },
        // jumpToReport(){
        //   this.$router.push({path: '/Report'})
        // },
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
        jumpUserDetail(id) {
          this.$router.push({path: '/UserDetail'})
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
          axios.get("/help/finish", {
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
          })
        }
      }
    }
</script>
