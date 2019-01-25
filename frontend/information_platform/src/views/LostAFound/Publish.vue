<template>
  <div>
    <tophead/>
    <div class="layout" >
      <Row>
        <Col span="20" offset="2">
          <Layout>
            <Sider breakpoint="md" collapsible :collapsed-width="78" v-model="isCollapsed">
              <Menu active-name="1-2" theme="dark" width="auto" :class="menuitemClasses">
                <MenuItem  name="1-1" to="/LostAFound/Board">
                  <Icon type="ios-navigate"></Icon>
                  <span>失物一栏</span>
                </MenuItem>
                <MenuItem name="1-2" to="/LostAFound/Publish">
                  <Icon type="ios-search"></Icon>
                  <span>发布信息</span>
                </MenuItem>
                <MenuItem name="1-3" to="/LostAFound/MyBoard" >
                  <Icon type="ios-settings"></Icon>
                  <span>我的发布</span>
                </MenuItem>
              </Menu>
              <div slot="trigger"></div>
            </Sider>
            <Layout>
              <Content :style="{margin: '20px', background: '#fff', minHeight: '500px'}">
                <div style="margin-top: 20px">
                  <div style="display: inline;font-size:1.5em ">标题：</div>
                  <Input v-model="value1" placeholder="Enter something..." clearable style="width: 80%" />
                </div>
                <div style="margin-top: 20px">
                  <div style="font-size:1.5em;margin-left: -76%">详细信息：</div>
                  <Input v-model="value2" type="textarea":rows="10"  placeholder="限200字以内" style="width: 80%;margin-top: 10px"  />
                </div>
                <div style="margin-top: 20px">
                  <div style="display: inline;font-size:1.5em ">联系方式：</div>
                  <Input v-model="value3" placeholder="Enter something..." clearable style="width: 75%" />
                </div>
                <Button shape="circle" style="margin-top: 20px" @click="push()">提交</Button>
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
    name: "Publish",
    components :{
      tophead,
      bottom
    },
    data(){
      return{
        value1:'',
        value2:'',
        value3:''
     }
    },
    methods:{
      push(){
        if(this.value1 ==''){
          this.$Message.warning('标题不能为空');
        }
        else if(this.value2 == ''){
          this.$Message.warning('详细信息不能为空');
        }
        else if(this.value3 == ''){
          this.$Message.warning('联系方式不能为空');
        }
        else if(this.value2.length>200){
          this.$Message.warning('详细信息不能超过200字');
        }
        else{
          console.log(this.value2.length)
          this.$Message.success('发布成功');
          // this.value1='';
          // this.value2='';
          // this.value3='';
        }
      }
    }
  }
</script>

<style scoped>
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
