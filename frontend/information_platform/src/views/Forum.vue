<template>
  <div>
    <tophead></tophead>
    <div class="center">
      <Tabs class="cards" type="card">
        <TabPane label="推荐">
          <font size="10" v-if="status1 === 'fail'">信息获取失败</font>
          <br>
          <font size="4" v-if="status1 === 'fail'">{{errormsg1}}</font>
          <Row class="cardbox" style="background:#eee;padding:20px"  v-if="status1 === 'success'">
            <Col class="cardcol" span="25" v-for="(post,index) in post" :key="post.postid">
              <div @click="jumpDetail(post.postid)" class="back">
                <Card class="card" :bordered="true">
                  <h1>{{post.title}}</h1>
                  <h3>{{post.label}} {{post.author}} {{post.authornickname}}</h3>
                  <h3>{{post.date}}</h3>
                    <div>
                      <Icon type="ios-heart" size="18"/>
                      <font size="3">{{post.likenum}}</font>
                    </div>
                    <div>
                      <Icon type="ios-chatbubbles" size="18"/>
                      <font size="3">{{post.commentnum}}</font>
                    </div>
                </Card>
              </div>
            </Col>
          </Row>
        </TabPane>
        <TabPane label="板块" id="la1">
          <div class="board" v-for="(board,index) in board" :key="board.id">
            <div class="label" @click="jumpPage">
              <img :src='board.img' height="50" width="50"/>
              <font size="6">{{board.name}}</font>
            </div>
          </div>
        </TabPane>
      </Tabs>
    </div>
    <bottom></bottom>
  </div>
</template>
<style scoped>
  #la1{
    border: 1px solid black;
  }
  .card{
    border: 1px solid black;
    float: top;
  }
  .center{
    width: 80%;
    margin: auto;
  }
  .cardcol{
    margin: 4px auto;
  }
  .label{
    float: left;
    width: 33%;
    margin: auto;
  }
  .rightback{
    float: left;
    position: relative;
    bottom: 80px;
    left: 95%;
  }
  .back{
    float: top;
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
          post: [],
          board: [
            {
              id: 0,
              name: '体育',
              img: './../../static/sport.png'
            },
            {
              id: 1,
              name: '学术',
              img: './../../static/study.png'
            },
            {
              id: 2,
              name: '音乐',
              img: './../../static/music.png'
            },
            {
              id: 3,
              name: '游戏',
              img: './../../static/game.png'
            }
          ]
        }
      },
      components: {
        tophead,
        bottom
      },
      created(){
        this.getrecommendpost();
      },
      methods: {
        getrecommendpost(){
          axios.get("/forum/recommend", {
            token: this.$store.state.token,
            userId: this.$store.state.userId,
          }).then((response) => {
            let res = response.data;
            if(res.status === "success") {
              this.post = res.post;
              this.status1 = res.status;
            } else {
              this.status1 = res.status;
              this.errormsg1 = res.message;
            }
          })
        },
        jumpPage(){
          this.$router.push({path: '/ForumPage'})
        },
        // 尝试携参数跳转页面
        jumpDetail(id){
          this.$router.push({
            path: '/ForumDetail',
            query: {
              id : id
            }
          })
        }
      }
    }
</script>
