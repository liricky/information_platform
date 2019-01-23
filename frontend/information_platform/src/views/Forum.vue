<template>
  <div>
    <tophead></tophead>
    <div class="center">
      <Tabs class="cards" type="card">
        <TabPane label="推荐">
          <Row class="cardbox" style="background:#eee;padding:20px">
            <Col class="cardcol" span="25" v-for="(post,index) in post.slice(0,6)" :key="post.title">
              <div @click="jumpDetail(post.id)">
                <Card class="card" :bordered="true">
                  <div class="leftback">
                    <h3 id="headline" slot="title">{{post.label}} {{post.author}}</h3>
                    <h1>{{post.title}}</h1>
                    <p>{{post.date}}</p>
                    <div class="rightback">
                      <div>
                        <Icon type="ios-heart" size="19"/>
                        <font size="4">{{post.likenum}}</font>
                      </div>
                      <div>
                        <Icon type="ios-chatbubbles" size="19"/>
                        <font size="4">{{post.commentnum}}</font>
                      </div>
                    </div>
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
    margin: auto;
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
    float: right;
    position: relative;
    bottom: 70px;
  }
</style>
<script>
    import tophead from '@/components/Head'
    import bottom from '@/components/Bottom'

    export default {
        data() {
            return {
                post:[
                  {
                    id: 0,
                    label: '体育',
                    title: '新生杯',
                    author: '16122075',
                    content: '新生杯比赛即将开始，欢迎加入！',
                    date: '2019-01-01',
                    commentnum: 20,
                    likenum: 40,
                    readnum: 50
                  },
                  {
                    id: 1,
                    label: '体育',
                    title: '校园运动会',
                    author: '16122076',
                    content: '校园运动会即将在本周召开，欢迎同学参加！',
                    date: '2019-01-01',
                    commentnum: 5,
                    likenum: 0,
                    readnum: 20
                  },
                  {
                    id: 2,
                    label: '学术',
                    title: '挑战杯组队',
                    author: '16235432',
                    content: '挑战杯比赛组队，寻找计算机学院的同学',
                    date: '2019-01-01',
                    commentnum: 1,
                    likenum: 0,
                    readnum: 10
                  },
                  {
                    id: 3,
                    label: '音乐',
                    title: '校园十大歌手',
                    author: '16235432',
                    content: '校园十大歌手决赛将于周日在体育馆举办，欢迎参加！',
                    date: '2019-01-01',
                    commentnum: 10,
                    likenum: 25,
                    readnum: 90
                  }
                ],
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
      methods: {
        jumpPage(){
          this.$router.push({path: '/ForumPage'})
        },
        jumpDetail(id){
          this.$router.push({path: '/ForumDetail'})
        }
      }
    }
</script>
