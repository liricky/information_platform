<template>
  <div class="homepage-content">
    <tophead></tophead>
    <Layout>
      <Header  style="height: 200px;">
        <Carousel v-model="value1" loop style="height: 200px">
          <CarouselItem>
            <div class="demo-carousel">1</div>
          </CarouselItem>
          <CarouselItem>
            <div class="demo-carousel">2</div>
          </CarouselItem>
          <CarouselItem>
            <div class="demo-carousel">3</div>
          </CarouselItem>
        </Carousel>
      </Header>
      <Layout style="height: 380px">
        <Sider hide-trigger width="300" style="background: white"><calendar style="width: 100%;height: 100%;margin-left: 0px"></calendar></Sider>
        <Content style="margin-top: 10px">
          <b style="font-size: 20px;"><Icon type="ios-crop" />最近公告</b>
          <Collapse simple style="text-align: left">
            <Panel v-for="(msg,index) in msg.slice(0,8)">
              <span >{{msg.title}}</span><div style="display: inline;width: 30%;float: right; margin-right: 10%"><div style="display: inline;float:right;margin-right: 10%">{{msg.date}}</div><div style="display:inline;float:left">{{msg.type}}</div></div>
              <p slot="content" style="text-align: justify">{{msg.content}}</p>
              <div slot="content">
                <ButtonGroup shape="circle" style="margin-left: 85%;margin-top: 10px;" size="small">
                  <Button type="info" @click="jumpDetail(msg.type)"><div v-if="msg.type === '失物启示'">详情</div><div v-else>更多</div></Button>
                </ButtonGroup>
              </div>
            </Panel>
          </Collapse>
        </Content>
      </Layout>
    </Layout>
    <!--<div>this is template body</div>-->
    <bottom></bottom>
  </div>
</template>
<style scoped>
  /*.homepage-content{*/
    /*position: static;*/
  /*}*/
</style>
<script>
    import tophead from '@/components/Head.vue'
    import bottom from '@/components/Bottom.vue'
    import calendar from '@/components/calendar.vue'
    import axios from 'axios'
    export default {
        data() {
            return {
                msg:[
                ]
            }
        },
        components:{
          bottom,
          tophead,
          calendar
        },
        methods:{
          getdata() {
            axios({
              url:apiRoot+'/api/announcement/new',
              method:'get'
            }).then((response) => {
              let res = response.data;
              if(res.status === "success") {
                this.msg = res.data;
                this.classify();
              } else {
                this.status1 = res.status;
                this.errormsg1 = res.message;
                this.$Message.info('获取失败： ' + this.errormsg1);
              }
            })
          },
          jumpDetail(type){
            if(type === "失物启示"){
              this.$router.push({
                path: '/lostafound/board'
              })
            }
            else{
              this.$router.push({
                path: '/announcement'
              })
            }
          }
      },
      created(){
        this.getdata();
      }
    }
</script>
