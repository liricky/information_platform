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
          <b style="font-size: 20px;"><Icon type="ios-crop" />推荐栏</b>
          <Collapse simple style="text-align: left">
            <Panel v-for="(msg,index) in msg.slice(0,8)">
              <span >{{msg.title}}</span><div style="display: inline;margin-left: 60%">{{msg.addresser}}</div><div style="display:inline;margin-left: 10%">{{msg.date}}</div>
              <p slot="content" style="text-align: justify">{{msg.content}}</p>
              <div slot="content">
                <ButtonGroup shape="circle" style="margin-left: 85%;margin-top: 10px;" size="small">
                  <Button type="info">回复</Button>
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
    export default {
        data() {
            return {
                msg:[
                  {
                    title:'史蒂夫·乔布斯',
                    content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                    date:'2012-2-2',
                    addresser:'刘某人'
                  },
                  {
                    title:'史蒂夫·乔布斯',
                    content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                    date:'2012-2-2',
                    addresser:'刘某人'
                  },
                  {
                    title:'史蒂夫·乔布斯',
                    content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                    date:'2012-2-2',
                    addresser:'刘某人'
                  },
                  {
                    title:'史蒂夫·乔布斯',
                    content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                    date:'2012-2-2',
                    addresser:'刘某人'
                  },
                  {
                    title:'史蒂夫·乔布斯',
                    content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                    date:'2012-2-2',
                    addresser:'刘某人'
                  },
                  {
                    title:'史蒂夫·乔布斯',
                    content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                    date:'2012-2-2',
                    addresser:'刘某人'
                  },
                ]
            }
        },
        components:{
          bottom,
          tophead,
          calendar
        },
    }
</script>
