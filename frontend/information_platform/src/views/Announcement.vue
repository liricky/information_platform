<template>
    <div>
      <tophead/>
      <Row>
        <Col span="16" offset="4">
          <Tabs :animated="false" style="text-align: left">
            <TabPane label="总览">
              <div style="min-height: 700px">
                <Divider orientation="left"class="title" ><b>系统通知</b></Divider>
                <Collapse value="0" style="background: white;width: 80%;margin:0 auto" >
                  <Panel v-for="(a,index) in a1" v-if="index < 3">
                    <span >{{a.title}}</span><div style="display:inline;margin-left: 60%">{{a.date}}</div>
                    <p slot="content" style="text-align: justify;text-indent:25px">{{a.content}}</p>
                  </Panel>
                </Collapse>
                <b class="more">......</b>
                <Divider orientation="left" class="title"><b>假日调休</b></Divider>
                <Collapse value="0" style="background: white;width: 80%;margin:0 auto" >
                  <Panel v-for="(a,index) in a2" v-if="index < 3">
                    <span >{{a.title}}</span><div style="display:inline;margin-left: 60%">{{a.date}}</div>
                    <p slot="content" style="text-align: justify;text-indent:25px">{{a.content}}</p>
                  </Panel>
                </Collapse>
                <b class="more">......</b>
                <Divider orientation="left" class="title"><b>失物启示</b></Divider>
                <Collapse value="0" style="background: white;width: 80%;margin:0 auto" >
                  <Panel v-for="(a,index) in a3" v-if="index < 3">
                    <span >{{a.title}}</span><div style="display:inline;margin-left: 60%">{{a.date}}</div>
                    <p slot="content" style="text-align: justify;text-indent:25px">{{a.content}}</p>
                  </Panel>
                </Collapse>
                <b class="more">......</b>
              </div>
            </TabPane>
            <TabPane label="系统通知">
              <div style="min-height: 700px">
                <Divider orientation="left" class="title"><b>系统通知</b></Divider>
                <Collapse value="0" style="background: white;width: 80%;margin:0 auto" >
                    <Panel v-for="a in a1">
                      <span >{{a.title}}</span><div style="display:inline;margin-left: 60%">{{a.date}}</div>
                      <p slot="content" style="text-align: justify;text-indent:25px">{{a.content}}</p>
                    </Panel>
                </Collapse>
              </div>
            </TabPane>
            <TabPane label="假日调休">
              <div style="min-height: 700px">
                <Divider orientation="left" class="title"><b>假日调休</b></Divider>
                <Collapse value="0" style="background: white;width: 80%;margin:0 auto" >
                  <Panel v-for="a in a2">
                    <span >{{a.title}}</span><div style="display:inline;margin-left: 60%">{{a.date}}</div>
                    <p slot="content" style="text-align: justify;text-indent:25px">{{a.content}}</p>
                  </Panel>
                </Collapse>
              </div>
            </TabPane>
            <TabPane label="失物启示">
              <div style="min-height: 700px">
                <Divider orientation="left" class="title"><b>失物启示</b></Divider>
                <Collapse value="0" style="background: white;width: 80%;margin:0 auto" >
                  <Panel v-for="a in a3">
                    <span >{{a.title}}</span><div style="display:inline;margin-left: 60%">{{a.date}}</div>
                    <p slot="content" style="text-align: justify;text-indent:25px">{{a.content}}</p>
                  </Panel>
                </Collapse>
              </div>
            </TabPane>
          </Tabs>
        </Col>
      </Row>

      <bottom/>
    </div>
</template>

<script>
  import tophead from '@/components/Head.vue'
  import bottom from '@/components/Bottom.vue'
    export default {
        name: "Announcement",
      data(){
          return{
            msg:[
              {
                title:'史蒂夫·乔布斯(系统通知)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'系统通知'
              },
              {
                title:'史蒂夫·乔布斯(系统通知)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'系统通知'
              },
              {
                title:'史蒂夫·乔布斯(系统通知)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'系统通知'
              },
              {
                title:'史蒂夫·乔布斯(系统通知)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'系统通知'
              },
              {
                title:'史蒂夫·乔布斯(调休通知)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'调休通知'
              },
              {
                title:'史蒂夫·乔布斯(调休通知)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'调休通知'
              },
              {
                title:'史蒂夫·乔布斯(调休通知)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'调休通知'
              },
              {
                title:'史蒂夫·乔布斯(调休通知)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'调休通知'
              },
              {
                title:'史蒂夫·乔布斯(失物启示)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'失物启示'
              },
              {
                title:'史蒂夫·乔布斯(失物启示)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'失物启示'
              },
              {
                title:'史蒂夫·乔布斯(失物启示)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'失物启示'
              },
              {
                title:'史蒂夫·乔布斯(失物启示)',
                content:'史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。',
                date:'2012-2-2',
                addresser:'刘某人',
                type:'失物启示'
              }
            ],
            a1:[],
            a2:[],
            a3:[],
          }
      },
      methods: {
        classify() {
          let i = 0, j = 0, k = 0, index;
          for (index = 0; index < this.msg.length; index++) {
            // if (this.msg[index].type === '系统通知') {
            //     this.a1.splice(i++, 1, this.msg[index])
            // }
            switch (this.msg[index].type){
              case '系统通知':
                this.a1.splice(i++, 1, this.msg[index]);
                break
              case '调休通知':
                this.a2.splice(j++, 1, this.msg[index]);
                break
              case '失物启示':
                this.a3.splice(k++, 1, this.msg[index]);
                break
            }
          }
        }
      },
      components: {
        tophead,
        bottom
      },
      mounted () {
        this.classify();
        console.log(this.a1);
      }
    }

</script>

<style scoped>
  .title{
    font-size: 2.5em;
    margin-top: 20px;
  }
  .more{
    font-size: 2em;
    margin-left: 10%;
  }
</style>
