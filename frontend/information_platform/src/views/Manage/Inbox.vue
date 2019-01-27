<template>
  <div>
    <Sider :style="{position: 'fixed', height: '100vh', left: 0, overflow: 'auto'}">
      <msider/>
    </Sider>
    <Layout :style="{marginLeft: '200px'}">
      <Header :style="{background: '#fff', boxShadow: '0 2px 3px 2px rgba(0,0,0,.1)'}">收件箱</Header>
      <Breadcrumb :style="{margin: '16px 0'}">
      </Breadcrumb>
      <Content :style="{padding: '0 16px 16px'}">
        <Card>
          <Tabs :animated="false" style="text-align: left">
            <TabPane label="举报信息">
              <div style="min-height: 500px">
                <Divider orientation="left" class="title"><b>举报信息</b></Divider>
                <Collapse>
                  <Panel  v-for="n in msg1">
                    <span>举报</span><div style="display: inline;margin-left: 20%">被举报人：{{n.reported}}</div>
                    <div style="display:inline;margin-left: 20%">举报人：{{n.report}}</div>
                    <div style="display:inline;margin-left: 20%">举报时间：{{n.date}}</div>
                    <p slot="content">举报类型：{{n.type}}</p>
                    <p slot="content" style="margin-top: 10px">举报原因：</p>
                    <p slot="content" style="margin-top: 10px;text-indent: 2em">{{n.reason}}</p>
                  </Panel>
                </Collapse>
              </div>
            </TabPane>
            <TabPane label="申诉信息">
              <div style="min-height: 500px">
                <Divider orientation="left" class="title"><b>申诉信息</b></Divider>
                <Collapse>
                  <Panel v-for="n in msg2">
                    <span>{{n.title}}</span><div style="display: inline;margin-left: 40%">申诉人：{{n.report}}</div><div style="display: inline;margin-left: 20%">申诉时间：{{n.date}}</div>
                    <p slot="content">申诉类型：{{n.type}}</p>
                    <p slot="content" style="margin-top: 10px">申诉原因：</p>
                    <p slot="content" style="margin-top: 10px;text-indent: 2em">{{n.reason}}</p>
                  </Panel>
                </Collapse>
              </div>
            </TabPane>
          </Tabs>
        </Card>
      </Content>
    </Layout>
  </div>
</template>

<script>
  import msider from '../../components/M_Sider.vue'
    export default {
        name: "Inbox",
      components:{
        msider
      },
      data(){
          return {
            msg1:[
              {
              report:'A',
              reported: 'B',
              date:'2012-2-2',
              type:'论坛举报',
              reason:'123123123213213'
              },
              {
                report:'A',
                reported: 'B',
                date:'2012-2-2',
                type:'论坛举报',
                reason:'123123123213213'
              },
              {
                report:'A',
                reported: 'B',
                date:'2012-2-2',
                type:'论坛举报',
                reason:'123123123213213'
              },
              {
                report:'A',
                reported: 'B',
                date:'2012-2-2',
                type:'论坛举报',
                reason:'123123123213213'
              },
              {
                report:'A',
                reported: 'B',
                date:'2012-2-2',
                type:'论坛举报',
                reason:'123123123213213'
              }
            ],
            msg2:[
              {
                title:'123123',
                report:'A',
                type:'论坛违规',
                reason:'123123123123',
                date:"2012-2-2"
              },
              {
                title:'123123',
                report:'A',
                type:'论坛违规',
                reason:'123123123123',
                date:"2012-2-2"
              },
              {
                title:'123123',
                report:'A',
                type:'论坛违规',
                reason:'123123123123',
                date:"2012-2-2"
              },
              {
                title:'123123',
                report:'A',
                type:'论坛违规',
                reason:'123123123123',
                date:"2012-2-2"
              },
              {
                title:'123123',
                report:'A',
                type:'论坛违规',
                reason:'123123123123',
                date:"2012-2-2"
              }
            ]
          }
      }
    }
</script>

<style scoped>

</style>
