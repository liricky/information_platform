<template>
  <div>
    <tophead></tophead>
    <div class="center">
      <font size="10" v-if="status1 === 'fail' || status2 === 'fail' || status3 === 'fail'">信息获取失败</font>
      <br>
      <font size="4" v-if="status1 === 'fail' || status2 === 'fail' || status3 === 'fail'">{{errormsg1}}</font>
      <div class="topback" v-if="status1 === 'success'">
        <!--携带参数显示-->
        <!--<font size="10">{{postid}}</font>-->

        <font size="8">{{post.title}}</font>
        <br>
        <div>
          <font size="5" @click="jumpUserDetail(post.author)">{{post.author}} {{post.authornickname}}</font>
          <br>
          <font size="5">{{post.date}}</font>
        </div>
        <Icon class="flag" type="ios-flag" size="40" @click="jumpToReport1(post.postid)"/>
        <divider></divider>
        <font size="4">{{post.content}}</font>
        <br>
        <br>
        <div class="btnback">
          <Button type="info" size="large" icon="ios-heart" v-if="$store.state.token && likestatus === 'true'" @click="changelikestatus">已点赞</Button>
          <Button type="info" size="large" icon="ios-heart-outline" v-else @click="changelikestatus">点赞</Button>
          <Button type="info" size="large" icon="md-create" @click=comment(post.id)>评论</Button>
        </div>
      </div>
      <br>
      <div class="bottomback" v-if="status1 === 'success'">
        <divider><font size="5">热评</font></divider>
        <Row class="cardbox" style="background:#eee;padding:20px">
          <Col class="cardcol" span="25" v-for="(hotreply,index) in hotreply" :key="hotreply.commentid">
            <Card class="card" :bordered="true">
              <div class="comment">
                <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport2(hotreply.commentid,hotreply.author)"/>
                <font size="4" @click="jumpUserDetail(hotreply.author)">by: {{hotreply.author}} {{hotreply.authornickname}}</font>
                <br>
                <font size="4">date: {{hotreply.date}}</font>
              </div>
              <divider></divider>
              <font size="4">{{hotreply.content}}</font>
              <div class="rightback">
                <div>
                  <Icon type="ios-heart" size="19" v-if="$store.state.token && hotreply.likestatus === 'true'" @click="changecommentlikestatus(hotreply.commentid)"/>
                  <Icon type="ios-heart-outline" size="19" v-else @click="changecommentlikestatus(hotreply.commentid)"/>
                  <font size="4">{{hotreply.likenum}}</font>
                </div>
              </div>
            </Card>
          </Col>
        </Row>
        <divider><font size="5">评论</font></divider>
        <Row class="cardbox" style="background:#eee;padding:20px">
          <Col class="cardcol" span="25" v-for="(reply,index) in reply" :key="reply.commentid">
            <Card class="card" :bordered="true">
              <div class="comment">
                <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport2(reply.commentid,reply.author)"/>
                <font size="4" @click="jumpUserDetail(reply.author)">by: {{reply.author}} {{reply.authornickname}}</font>
                <br>
                <font size="4">date: {{reply.date}}</font>
              </div>
              <divider></divider>
              <font size="4">{{reply.content}}</font>
              <div class="rightback">
                <div>
                  <Icon type="ios-heart" size="19" v-if="$store.state.token && reply.likestatus === 'true'" @click="changecommentlikestatus(reply.commentid)"/>
                  <Icon type="ios-heart-outline" size="19" v-else @click="changecommentlikestatus(reply.commentid)"/>
                  <font size="4">{{reply.likenum}}</font>
                </div>
              </div>
            </Card>
          </Col>
        </Row>
      </div>
    </div>
    <bottom></bottom>
    <Modal
      v-model="modal1"
      title="评论"
      @on-ok="makecomment"
      @on-cancel="cancel">
      <Input v-model="value1" type="textarea" :rows="6" placeholder="请输入评论内容！" />
    </Modal>
  </div>
</template>
<style scoped>
  .center{
    width: 80%;
    margin: auto;
  }
  .btnback{
    margin: auto;
  }
  .flag{
    float: right;
  }
  .card{
    border: 1px solid black;
  }
</style>
<script>
  import tophead from '@/components/Head'
  import bottom from '@/components/Bottom'
  import axios from 'axios'

  export default {
    data() {
      return {
        // 携带参数赋值页面变量
        postid: "",
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
        post: {
          postid: '',
          label: '',
          title: '',
          content: '',
          author: '',
          authornickname: '',
          date: ''
        },
        hotreply: [],
        reply: [],
        modal1: false,
        likestatus: '',
        value1: '',
        type: '',
        sign: '',
      }
    },
    components: {
      tophead,
      bottom
    },
    // 页面初始化时调用函数,传入参数,渲染对应内容
    created(){
      this.getParams();
      this.getDetail();
      this.checktype();
    },
    methods: {
      ok() {
        this.$Message.info('发表成功！');
        this.value1 = '';
      },
      cancel() {
        this.$Message.info('已取消发表！');
      },
      comment() {
        if (this.$store.state.token) {
          if (this.sign)
            this.$Message.info("您已被封禁，无法使用该功能，如有疑问可进行申诉！");
          else {
            this.modal1 = true;
          }
        }
        else {
          this.$Message.info('请先登录！');
          this.$router.push({path: '/Login'});
        }
      },
      jumpToReport1(id) {
        this.$router.push({
          path: '/Report',
          query: {
            id : id,
            type: 2,
            reportid: this.post.author
          }
        })
      },
      jumpToReport2(id,reportid) {
        this.$router.push({
          path: '/Report',
          query: {
            id : id,
            type:1,
            reportid: reportid
          }
        })
      },
      jumpUserDetail(id) {
        this.$router.push({
          path: '/UserDetail',
          query: {
            id : id
          }
        })
      },
      // 参数传递方法
      getParams(){
        this.postid = this.$route.query.id;
      },
      getDetail(){
        axios({
          url: apiRoot + '/forum/detail/' + this.postid,
          method: 'get',
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.post.postid = res.data.postid;
            this.post.label = res.data.label;
            this.post.title = res.data.title;
            this.post.content = res.data.content;
            this.post.author = res.data.author;
            this.post.authornickname = res.data.authornickname;
            this.post.date = res.data.date;
            this.status1 = res.status;
          } else {
            this.status1 = res.status;
            this.errormsg1 = res.message;
          }
        });
        axios({
          url: apiRoot + '/forum/gethotcomment/' + this.$store.state.userId + '/' + this.postid,
          headers: {Authorization: this.$store.state.token},
          method: 'get',
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.hotreply = res.data;
            this.status2 = res.status;
          } else {
            this.status2 = res.status;
            this.errormsg2 = res.message;
          }
        });
        axios({
          url: apiRoot + '/forum/getcomment/' + this.$store.state.userId + '/' + this.postid,
          headers: {Authorization: this.$store.state.token},
          method: 'get',
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.reply = res.data;
            this.status3 = res.status;
          } else {
            this.status3 = res.status;
            this.errormsg3 = res.message;
          }
        });
        if(this.$store.state.token) {
          axios({
            url: apiRoot + '/forum/getlike/' + this.$store.state.userId + '/' + this.postid,
            headers: {Authorization: this.$store.state.token},
            method: 'get',
          }).then((response) => {
            let res = response.data;
            if (res.status === "success") {
              this.likestatus = res.data.likestatus;
              this.status4 = res.status;
            } else {
              this.status4 = res.status;
              this.errormsg4 = res.message;
            }
          });
        }
      },
      makecomment(){
        if (this.$store.state.token) {
          if (this.value1 === "") {
            this.$Message.info('评论内容不能为空！');
          } else {
            axios({
              url: apiRoot + '/forum/createcomment',
              headers: {Authorization: this.$store.state.token},
              method: 'post',
              data: {
                userid: this.$store.state.userId,
                content: this.value1,
                postid: this.postid,
              }
            }).then((response) => {
              let res = response.data;
              if (res.status === "success") {
                this.status5 = res.status;
                this.ok();
              } else {
                this.status5 = res.status;
                this.errormsg5 = res.message;
                this.$Message.info('评论发送出错！');
              }
            });
          }
        } else {
          this.$Message.info('请先登录！');
          this.$router.push({path: '/Login'});
        }
      },
      changelikestatus(){
        if(this.$store.state.token) {
          axios({
            url: apiRoot + '/forum/changelike',
            headers: {Authorization: this.$store.state.token},
            method: 'post',
            data: {
              userid: this.$store.state.userId,
              postid: this.postid,
              likestatus: this.likestatus === "true" ? "false" : "true",
            }
          }).then((response) => {
            let res = response.data;
            if (res.status === "success") {
              this.status6 = res.status;
              this.likestatus = res.data.likestatus;
              this.$Message.info('修改点赞状态成功！');
            } else {
              this.status6 = res.status;
              this.errormsg6 = res.message;
              this.$Message.info('修改点赞状态出错！');
            }
          });
        } else{
          this.$Message.info('请先登录！');
          this.$router.push({path: '/Login'});
        }
      },
      getcommentlikestatus(commentid){
        if(this.$store.state.token) {
          axios({
            url: apiRoot + '/forum/getcommentlike/' + this.$store.state.userId + '/' + commentid,
            headers: {Authorization: this.$store.state.token},
            method: 'get',
          }).then((response) => {
            let res = response.data;
            if (res.status === "success") {
              this.status7 = res.status;
              return res.data.likestatus;
            } else {
              this.status7 = res.status;
              this.errormsg7 = res.message;
              return "";
            }
          });
        } else{
          this.$Message.info('请先登录！');
          this.$router.push({path: '/Login'});
        }
      },
      changecommentlikestatus(commentid){
        if(this.$store.state.token) {
          axios({
            url: apiRoot + '/forum/changecommentlike',
            headers: {Authorization: this.$store.state.token},
            method: 'post',
            data: {
              userid: this.$store.state.userId,
              commentid: commentid,
              likestatus: this.getcommentlikestatus(commentid) === "true" ? "false" : "true",
            }
          }).then((response) => {
            let res = response.data;
            if (res.status === "success") {
              this.status8 = res.status;
              this.$Message.info('修改点赞状态成功！');
              for (let i of this.reply) {
                if(i.commentid === commentid){
                  this.$set(i,"likestatus",i.likestatus === "true" ? "false" : "true");
                  break;
                }
              }
              for (let i of this.hotreply) {
                if(i.commentid === commentid){
                  this.$set(i,"likestatus",i.likestatus === "true" ? "false" : "true");
                  break;
                }
              }
            } else {
              this.status8 = res.status;
              this.errormsg8 = res.message;
              this.$Message.info('修改点赞状态出错！');
            }
          });
        } else{
          this.$Message.info('请先登录！');
          this.$router.push({path: '/Login'});
        }
      },
      checktype(){
        axios({
          url: apiRoot + '/appeal/get/' + this.$store.state.userId,
          headers: {Authorization: this.$store.state.token},
          method: 'get',
        }).then((response) => {
          let res = response.data;
          if(res.status === "success") {
            this.status9 = res.status;
            if(res.data.type === 1 || res.data.type === 3)
              this.sign = true;
            else
              this.sign = false;
          } else {
            this.status9 = res.status;
            this.errormsg9 = res.message;
          }
        })
      }
    },
    // Vue的侦听器,用来检测数据的变化,变化时执行对应函数
    watch: {
      '$route': 'getParams'
    }
  }
</script>
