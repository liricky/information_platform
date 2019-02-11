<template>
    <div>
      <tophead></tophead>
      <div class="center">
        <Button type="primary" @click="claim2"><font size="4">发布任务</font></Button>
        <Tabs class="cards" type="card">
          <TabPane label="大厅">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post,index) in post" :key="post.title">
                <Card class="card" :bordered="true">
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport"/>
                  <div class="leftback">
                    <div>
                      <font size="4" @click="jumpUserDetail(post.author)">by:{{post.author}}</font>
                      <br>
                      <font size="4">by:{{post.date}}</font>
                    </div>
                    <div>
                      <font size="3">{{post.content}}</font>
                      <div class="rightback">
                        <Button type="primary" @click="claim(post.id)"><font size="2">认领</font></Button>
                      </div>
                    </div>
                  </div>
                </Card>
              </Col>
            </Row>
          </TabPane>
          <TabPane label="已认领">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post1,index) in post1" :key="post1.title">
                <Card class="card" :bordered="true">
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport"/>
                  <div class="leftback">
                    <div>
                      <font size="4" @click="jumpUserDetail(post1.author)">by:{{post1.author}}</font>
                      <br>
                      <font size="4">by:{{post1.date}}</font>
                    </div>
                    <div>
                      <font size="3">{{post1.content}}</font>
                      <div class="rightback">
                        <font size="3">发布人联系方式：{{post1.phone}}</font>
                        <br>
                        <font size="3">任务状态：{{post1.status}}</font>
                        <br>
                        <Button type="primary" @click="claim1(post1.id)"><font size="2">放弃任务</font></Button>
                        &nbsp;&nbsp;
                        <Button type="primary" @click="claim1(post1.id)"><font size="2">任务完成</font></Button>
                      </div>
                    </div>
                  </div>
                </Card>
              </Col>
            </Row>
          </TabPane>
          <TabPane label="已发布">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post1,index) in post1" :key="post1.title">
                <Card class="card" :bordered="true">
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport"/>
                  <div class="leftback">
                    <div>
                      <font size="4" @click="jumpUserDetail(post1.author)">by:{{post1.author}}</font>
                      <br>
                      <font size="4">by:{{post1.date}}</font>
                    </div>
                    <div>
                      <font size="3">{{post1.content}}</font>
                      <div class="rightback">
                        <font size="3">任务状态：{{post1.status}}</font>
                        <br>
                        <font size="3">认领人联系方式：{{post1.phone}}</font>
                        <br>
                        <Button type="primary" @click="claim1(post1.id)"><font size="2">放弃任务</font></Button>
                        &nbsp;&nbsp;
                        <Button type="primary" @click="claim1(post1.id)"><font size="2">任务完成</font></Button>
                      </div>
                    </div>
                  </div>
                </Card>
              </Col>
            </Row>
          </TabPane>
          <TabPane label="已完成">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post2,index) in post2" :key="post2.title">
                <Card class="card" :bordered="true">
                  <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport"/>
                  <div class="leftback">
                    <div>
                      <font size="4" @click="jumpUserDetail(post2.author)">by:{{post2.author}}</font>
                      <br>
                      <font size="4">by:{{post2.date}}</font>
                    </div>
                    <div>
                      <font size="3">{{post2.content}}</font>
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

    export default {
        data() {
            return {
              value1: '',
              value2: '',
              value3: '',
              value4: '',
              modal1: false,
              modal2: false,
              modal3: false,
              post:[
                {
                  id: 0,
                  author: '16122075',
                  content: '求带早餐！',
                  date: '2019-01-01',
                },
                {
                  id: 1,
                  author: '16122076',
                  content: '帮取快递！',
                  date: '2019-01-01',
                },
                {
                  id: 2,
                  author: '16235432',
                  content: '收二手电脑！',
                  date: '2019-01-01',
                },
                {
                  id: 3,
                  author: '16235432',
                  content: '求马原书！',
                  date: '2019-01-01',
                }
              ],
              post1:[
                {
                  id: 4,
                  author: '16122075',
                  content: '求带早餐！',
                  date: '2019-01-01',
                  phone: '12345678901',
                  status: '进行中'
                },
                {
                  id: 5,
                  author: '16122076',
                  content: '帮取快递！',
                  date: '2019-01-01',
                  phone: '09876543210',
                  status: '等待发布人联系'
                }
              ],
              post2:[
                {
                  id: 4,
                  author: '16122075',
                  content: '求带早餐！',
                  date: '2019-01-01',
                },
                {
                  id: 5,
                  author: '16122076',
                  content: '帮取快递！',
                  date: '2019-01-01',
                }
              ]
            }
        },
        components: {
          tophead,
          bottom
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
        jumpToReport(){
          this.$router.push({path: '/Report'})
        },
        jumpUserDetail(id) {
          this.$router.push({path: '/UserDetail'})
        }
      }
    }
</script>
