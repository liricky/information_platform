<template>
  <div>
    <tophead></tophead>
    <div class="center">
      <div class="topback">
        <!--携带参数显示-->
        <!--<font size="10">{{postid}}</font>-->

        <font size="8">{{post.title}}</font>
        <br>
        <font size="5" @click="jumpUserDetail(userid)">{{post.author}} {{post.date}}</font>
        <Icon class="flag" type="ios-flag" size="40" @click="jumpToReport"/>
        <divider></divider>
        <font size="4">{{post.content}}</font>
        <br>
        <br>
        <div class="btnback">
          <Button type="info" size="large" icon="ios-heart-outline">点赞</Button>
          <Button type="info" size="large" icon="md-create" @click=comment(post.id)>评论</Button>
        </div>
      </div>
      <br>
      <div class="bottomback">
        <divider><font size="5">热评</font></divider>
        <Row class="cardbox" style="background:#eee;padding:20px">
          <Col class="cardcol" span="25" v-for="(post2,index) in post2" :key="post2.title">
            <Card class="card" :bordered="true">
              <div class="comment">
                <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport"/>
                <font size="4">by: {{post2.author}}</font>
                <br>
                <font size="4">date: {{post2.date}}</font>
              </div>
              <divider></divider>
              <font size="3">{{post2.content}}</font>
              <div class="rightback">
                <div>
                  <Icon type="ios-heart" size="19"/>
                  <font size="4">{{post2.likenum}}</font>
                </div>
              </div>
            </Card>
          </Col>
        </Row>
        <divider><font size="5">评论</font></divider>
        <Row class="cardbox" style="background:#eee;padding:20px">
          <Col class="cardcol" span="25" v-for="(post1,index) in post1" :key="post1.title">
            <Card class="card" :bordered="true">
              <div class="comment">
                <Icon class="flag" type="ios-flag" size="30" @click="jumpToReport"/>
                <font size="4">by: {{post1.author}}</font>
                <br>
                <font size="4">date: {{post1.date}}</font>
              </div>
              <divider></divider>
              <font size="3">{{post1.content}}</font>
              <div class="rightback">
                <div>
                  <Icon type="ios-heart" size="19"/>
                  <font size="4">{{post1.likenum}}</font>
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
      @on-ok="ok"
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

  export default {
    data() {
      return {
        // 携带参数赋值页面变量
        postid: "",

        value1: '',
        userid: 16122075,
        modal1: false,
        post: {
          id: 0,
          label: '体育',
          title: '新生杯',
          author: '16122075',
          content: '新生杯比赛即将开始，欢迎加入！',
          date: '2019-01-01',
        },
        post1: [
          {
            id: 0,
            author: '16122075',
            content: '我要参加！',
            date: '2019-01-01',
            likenum: 40
          },
          {
            id: 1,
            author: '16122076',
            content: '听说还可以减免晨跑！',
            date: '2019-01-01',
            likenum: 0
          }
        ],
        post2: [
          {
            id: 0,
            author: '16122075',
            content: '我要参加！',
            date: '2019-01-01',
            likenum: 40
          }
        ]
      }
    },
    components: {
      tophead,
      bottom
    },
    // 页面初始化时调用函数,传入参数,渲染对应内容
    // created(){
    //   this.getParams()
    // },
    methods: {
      ok() {
        this.$Message.info('发表成功！');
        this.value1 = '';
      },
      cancel() {
        this.$Message.info('已取消发表！');
      },
      comment(id) {
        console.log("ok");
        this.modal1 = true;
      },
      jumpToReport() {
        this.$router.push({path: '/Report'})
      },
      jumpUserDetail(id) {
        this.$router.push({path: '/UserDetail'})
      },

      // 参数传递方法
      // getParams(){
      //   this.postid = this.$route.query.id
      // }
    },
    // Vue的侦听器,用来检测数据的变化,变化时执行对应函数
    // watch: {
    //   '$route': 'getParams'
    // }
  }
</script>
