<template>
  <div>
    <tophead/>
    <div class="layout">
      <Row>
        <Col span="20" offset="2">
          <Layout>
            <Sider breakpoint="md" collapsible :collapsed-width="78" v-model="isCollapsed">
              <Menu active-name="1-3" theme="dark" width="auto" :class="menuitemClasses">
                <MenuItem name="1-1" to="/LostAFound/Board">
                  <Icon type="ios-navigate"></Icon>
                  <span>失物一栏</span>
                </MenuItem>
                <MenuItem name="1-2" to="/LostAFound/Publish">
                  <Icon type="ios-search"></Icon>
                  <span>发布信息</span>
                </MenuItem>
                <MenuItem name="1-3" to="/LostAFound/MyBoard">
                  <Icon type="ios-settings"></Icon>
                  <span>我的发布</span>
                </MenuItem>
              </Menu>
              <div slot="trigger"></div>
            </Sider>
            <Layout>
              <Content :style="{margin: '20px', background: '#fff', minHeight: '500px'}">
                <Col span="9" offset="1" v-for="n in msg" class="card">
                  <Card>
                    <b slot="title">{{n.title}}</b>
                    <p>{{n.content}}</p>
                    <Divider orientation="left"><b>联系方式</b></Divider>
                    <p>{{n.connect}}</p>
                    <div style="margin-top: 10px">{{n.date}}</div>
                    <Button type="error" style="float: right" @click="modal1 = true">删除</Button>
                    <Modal
                      v-model="modal1"
                      title="删除！！！"
                      @on-ok="ok"
                      @on-cancel="cancel">
                      <p>是否删除!!</p>
                    </Modal>
                  </Card>
                </Col>
              </Content>
            </Layout>
          </Layout>
        </Col>
      </Row>
    </div>
    <bottom/>
  </div>
</template>

<script>
  import tophead from '../../components/Head.vue'
  import bottom from '../../components/Bottom.vue'
  export default {
    name: "MyBoard",
    components :{
      tophead,
      bottom
    },
    data(){
      return{
        modal1:false,
        msg:[
          {
            title:'手机丢失',
            content: '在A303丢失，手机颜色红色·······',
            connect:'QQ:12345567',
            addresser:'123',
            date:'2012-2-2'
          },
          {
            title:'手机丢失',
            content: '在A303丢失，手机颜色红色·······',
            connect:'QQ:12345567',
            addresser:'123',
            date:'2012-2-2'
          },
          {
            title:'手机丢失',
            content: '在A303丢失，手机颜色红色·······',
            connect:'QQ:12345567',
            addresser:'123',
            date:'2012-2-2'
          },
          {
            title:'手机丢失',
            content: '在A303丢失，手机颜色红色·······',
            connect:'QQ:12345567',
            addresser:'123',
            date:'2012-2-2'
          },
          {
            title:'手机丢失',
            content: '在A303丢失，手机颜色红色·······',
            connect:'QQ:12345567',
            addresser:'123',
            date:'2012-2-2'
          }
        ]
      }
    },
    methods:{
      ok(){
        this.$Message.success('删除成功！');
      },
      cancel(){
        this.$Message.info('取消了删除');
      }
    }
  }
</script>

<style scoped>
  .card{
    margin-top: 10px;
  }
  .card p{
    font-size: 1em;
    text-align: left;
    text-indent: 2em;
  }
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }
  .menu-item span{
    display: inline-block;
    overflow: hidden;
    width: 69px;
    text-overflow: ellipsis;
    white-space: nowrap;
    vertical-align: bottom;
    transition: width .2s ease .2s;
  }
  .menu-item i{
    transform: translateX(0px);
    transition: font-size .2s ease, transform .2s ease;
    vertical-align: middle;
    font-size: 16px;
  }
  .collapsed-menu span{
    width: 0px;
    transition: width .2s ease;
  }
  .collapsed-menu i{
    transform: translateX(5px);
    transition: font-size .2s ease .2s, transform .2s ease .2s;
    vertical-align: middle;
    font-size: 22px;
  }
</style>
