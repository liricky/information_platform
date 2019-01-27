<template>
  <div class="layout">
    <Sider :style="{position: 'fixed', height: '100vh', left: 0, overflow: 'auto'}">
      <msider/>
    </Sider>
    <Layout :style="{marginLeft: '200px'}">
      <Header :style="{background: '#fff', boxShadow: '0 2px 3px 2px rgba(0,0,0,.1)'}">帖子删除</Header>
      <Content :style="{padding: '0 16px 16px'}">
        <Breadcrumb :style="{margin: '16px 0'}">
        </Breadcrumb>
        <Card>
        <Row :gutter="16">
          <Col span="2">
            <div>id</div>
          </Col>
          <Col span="10">
            <div>标题</div>
          </Col>
          <Col span="3">
            <div>类别</div>
          </Col>
          <Col span="3">
            <div>发帖人</div>
          </Col>
          <Col span="3">
            <div>发帖时间</div>
          </Col>
          <Col span="3">
            <div>操作</div>
          </Col>
        </Row>
        <Row :gutter="16" style="margin-top: 20px;" v-for="n in msg">
          <Col span="2">
            <div>{{n.id}}</div>
          </Col>
          <Col span="10">
            <div>{{n.title}}</div>
          </Col>
          <Col span="3">
            <div>{{n.type}}</div>
          </Col>
          <Col span="3">
            <div>{{n.addresser}}</div>
          </Col>
          <Col span="3">
            <div>{{n.date}}</div>
          </Col>
          <Col span="3">
            <ButtonGroup>
              <Button type="success" to="/ForumDetail" ghost>前往</Button>
              <Button type="error" @click="choose(n.id)" ghost>删除</Button>
            </ButtonGroup>
            <Modal
              v-model="modal1"
              title="是否删除该贴"
              @on-ok="ok(msgclick.id)"
              @on-cancel="cancel">
              <p>帖子id：{{msgclick.id}}</p>
              <p>帖子标题：{{msgclick.title}}</p>
            </Modal>
          </Col>
        </Row>
        </Card>
      </Content>
    </Layout>
  </div>
</template>
<script>
  import msider from '../../components/M_Sider.vue'
    export default {
        name: "ForumDelete",
        components:{
          msider
        },
      data(){
          return {
            modal1:false,
            msgclick:{},
            msg:[
              {
                id:0,
                title:"hahahaha",
                type:"体育",
                addresser:"刘某人",
                date:'2012-2-2'
              },
              {
                id:1,
                title:"hahahaha",
                type:"体育",
                addresser:"刘某人",
                date:'2012-2-2'
              },
              {
                id:2,
                title:"hahahaha",
                type:"体育",
                addresser:"刘某人",
                date:'2012-2-2'
              },
              {
                id:3,
                title:"123aha",
                type:"体育",
                addresser:"刘某人",
                date:'2012-2-2'
              },
            ]
          }
      },
      methods:{
        ok (id) {
          console.log(id);
          this.$Message.success('id:'+id+' 删除成功');
        },
        cancel () {
          this.$Message.info('取消了删除');
        },
        choose(id){
          var i = 0;
          for(i=0;i<this.msg.length;i++){
            if(id == this.msg[i].id){
              this.msgclick = this.msg[i];
            }
          }
          this.modal1=true;
        }
      }
    }
</script>

<style scoped>

</style>
