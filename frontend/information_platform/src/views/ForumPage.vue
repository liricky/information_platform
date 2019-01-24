<template>
  <div>
    <tophead></tophead>
    <div class="center">
      <div class="top">
        <div>
          <img :src='pagesrc1' width="80%" height="200px"/>
        </div>
        <Button class="followbutton" type="primary" size="large"><font size="4">关注</font></Button>
      </div>
      <div class="bottom">
        <Button class="sendbutton" type="primary" shape="circle" icon="ios-create" size="large" @click=jumpToForumCreate><font size="3">发帖</font></Button>
        <Tabs type="card">
          <TabPane label="最新回复">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post,index) in post" :key="post.title">
                <div @click="jumpDetail(post.id)">
                  <Card class="card" :bordered="true">
                    <h3 class="headline" slot="title">{{post.label}} {{post.author}}</h3>
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
                  </Card>
                </div>
              </Col>
            </Row>
          </TabPane>
          <TabPane label="最新发布">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post1,index) in post1" :key="post1.title">
                <div @click="jumpDetail(post1.id)">
                  <Card class="card" :bordered="true">
                    <h3 class="headline" slot="title">{{post1.label}} {{post1.author}}</h3>
                    <h1>{{post1.title}}</h1>
                    <p>{{post1.date}}</p>
                    <div class="rightback">
                      <div>
                        <Icon type="ios-heart" size="19"/>
                        <font size="4">{{post1.likenum}}</font>
                      </div>
                      <div>
                        <Icon type="ios-chatbubbles" size="19"/>
                        <font size="4">{{post1.commentnum}}</font>
                      </div>
                    </div>
                  </Card>
                </div>
              </Col>
            </Row>
          </TabPane>
          <TabPane label="精华">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post2,index) in post2" :key="post2.title">
                <div @click="jumpDetail(post2.id)">
                  <Card class="card" :bordered="true">
                    <h3 class="headline" slot="title">{{post2.label}} {{post2.author}}</h3>
                    <h1>{{post2.title}}</h1>
                    <p>{{post2.date}}</p>
                    <div class="rightback">
                      <div>
                        <Icon type="ios-heart" size="19"/>
                        <font size="4">{{post2.likenum}}</font>
                      </div>
                      <div>
                        <Icon type="ios-chatbubbles" size="19"/>
                        <font size="4">{{post2.commentnum}}</font>
                      </div>
                    </div>
                  </Card>
                </div>
              </Col>
            </Row>
          </TabPane>
          <TabPane label="全部">
            <Row class="cardbox" style="background:#eee;padding:20px">
              <Col class="cardcol" span="25" v-for="(post3,index) in post3" :key="post3.title">
                <div @click="jumpDetail(post3.id)">
                  <Card class="card" :bordered="true">
                    <h3 class="headline" slot="title">{{post3.label}} {{post3.author}}</h3>
                    <h1>{{post3.title}}</h1>
                    <p>{{post3.date}}</p>
                    <div class="rightback">
                      <div>
                        <Icon type="ios-heart" size="19"/>
                        <font size="4">{{post3.likenum}}</font>
                      </div>
                      <div>
                        <Icon type="ios-chatbubbles" size="19"/>
                        <font size="4">{{post3.commentnum}}</font>
                      </div>
                    </div>
                  </Card>
                </div>
              </Col>
            </Row>
          </TabPane>
        </Tabs>
      </div>
    </div>
    <bottom></bottom>
  </div>
</template>
<style scoped>
  .center{
    width: 80%;
    margin: auto;
  }
  .card{
    margin: auto;
  }
  .cardcol{
    margin: 4px auto;
  }
  .rightback{
    float: right;
    position: relative;
    bottom: 70px;
  }
  .followbutton{
    position: relative;
    bottom: 110px;
    left: 47%;
  }
  .sendbutton{
   position: relative;
    left:45%;
  }
</style>
<script>
  import tophead from '@/components/Head'
  import bottom from '@/components/Bottom'

    export default {
        data() {
            return {
              pagesrc1: './../../static/pagesrc1.png',
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
                }
              ],
              post1:[
                {
                  id: 2,
                  label: '体育1',
                  title: '新生杯1',
                  author: '16122075',
                  content: '新生杯比赛即将开始，欢迎加入1！',
                  date: '2019-01-01',
                  commentnum: 20,
                  likenum: 40,
                  readnum: 50
                },
                {
                  id: 3,
                  label: '体育1',
                  title: '校园运动会1',
                  author: '16122076',
                  content: '校园运动会即将在本周召开，欢迎同学参加1！',
                  date: '2019-01-01',
                  commentnum: 5,
                  likenum: 0,
                  readnum: 20
                }
              ],
              post2:[
                {
                  id: 4,
                  label: '体育2',
                  title: '新生杯2',
                  author: '16122075',
                  content: '新生杯比赛即将开始，欢迎加入2！',
                  date: '2019-01-01',
                  commentnum: 20,
                  likenum: 40,
                  readnum: 50
                },
                {
                  id: 5,
                  label: '体育2',
                  title: '校园运动会2',
                  author: '16122076',
                  content: '校园运动会即将在本周召开，欢迎同学参加2！',
                  date: '2019-01-01',
                  commentnum: 5,
                  likenum: 0,
                  readnum: 20
                }
              ],
              post3:[
                {
                  id: 6,
                  label: '体育3',
                  title: '新生杯3',
                  author: '16122075',
                  content: '新生杯比赛即将开始，欢迎加入3！',
                  date: '2019-01-01',
                  commentnum: 20,
                  likenum: 40,
                  readnum: 50
                },
                {
                  id: 7,
                  label: '体育3',
                  title: '校园运动会3',
                  author: '16122076',
                  content: '校园运动会即将在本周召开，欢迎同学参加3！',
                  date: '2019-01-01',
                  commentnum: 5,
                  likenum: 0,
                  readnum: 20
                }
              ]
            }
        },
        components: {
          tophead,
          bottom
        },
      methods: {
        jumpDetail(id){
          this.$router.push({path: '/ForumDetail'})
        },
        jumpToForumCreate() {
          this.$router.push({path: '/ForumCreate'})
        }
      }
    }
</script>
