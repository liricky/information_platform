<template>
  <div class="layout">
    <Sider :style="{position: 'fixed', height: '100vh', left: 0, overflow: 'auto'}">
      <msider/>
    </Sider>
    <Layout :style="{marginLeft: '200px'}">
      <Header :style="{background: '#fff', boxShadow: '0 2px 3px 2px rgba(0,0,0,.1)'}">用户管理</Header>
      <Content :style="{padding: '0 16px 16px'}">
        <Breadcrumb :style="{margin: '16px 0'}">
        </Breadcrumb>
        <Card>
          <Row>
            <Col span="3">
              <div>工号/学号</div>
            </Col>
            <Col span="2">
              <div>用户名</div>
            </Col>
            <Col span="2">
              <div>封禁状态</div>
            </Col>
            <Col span="3">
              <div>封禁类别</div>
            </Col>
            <Col span="3">
              <div>封禁原因</div>
            </Col>
            <Col span="2">
              <div>封禁结束时间</div>
            </Col>
            <Col span="3">
              <div>用户密码</div>
            </Col>
            <Col span="6">
              <div>操作</div>
            </Col>
          </Row>
          <Row style="margin-top: 20px" v-for="n in msg">
            <Col span="3">
              <div>{{n.number}}</div>
            </Col>
            <Col span="2">
              <div>{{n.name}}</div>
            </Col>
            <Col span="2">
              <div>{{n.status}}</div>
            </Col>
            <Col span="3">
              <div v-if="n.forbid_type != ''">{{n.forbid_type}}</div>
              <div v-else>无</div>
            </Col>
            <Col span="3">
              <div v-if="n.forbid_reason != ''">{{n.forbid_reason}}</div>
              <div v-else>无</div>
            </Col>
            <Col span="2">
              <div v-if="n.forbid_date != ''">{{n.forbid_date}}</div>
              <div v-else>无</div>
            </Col>
            <Col span="3">
              <div>{{n.password}}</div>
            </Col>
            <Col span="6">
              <ButtonGroup>
                <Button type="success" @click="changechoose1(n.id)" ghost>修改密码</Button>
                <Button type="error"  @click="changechoose2(n.id)" ghost>封禁/解封</Button>
              </ButtonGroup>
              <Modal
                v-model="modal1"
                title="修改密码"
                @on-ok="ok1(choice.id)"
                @on-cancel="cancel1()">
                <p>id:{{choice.id}}</p>
                <p>工号学号：{{choice.number}}</p>
                <Input v-model="value" placeholder="请输入新密码" clearable style="width: 300px;margin-top: 10px" />
              </Modal>
              <Modal
                v-model="modal2"
                title="封禁/解封"
                @on-ok="ok2(choice.id)"
                @on-cancel="cancel2()">
                <div v-if="choice.status=='封禁'">
                  <p>是否解封该用户</p>
                  <p>id:{{choice.id}}</p>
                  <p>工号学号：{{choice.number}}</p>
                </div>
                <div v-else>
                  <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
                    <FormItem label="封禁类型" prop="forbid_type">
                      <Select v-model="formValidate.forbid_type">
                        <Option value="论坛封禁">论坛封禁</Option>
                        <Option value="任务封禁">任务封禁</Option>
                        <Option value="都封禁">都封禁</Option>
                      </Select>
                    </FormItem>
                    <FormItem label="封禁原因" prop="forbid_reason">
                      <Input v-model="formValidate.forbid_reason" placeholder="封禁原因"></Input>
                    </FormItem>
                    <FormItem label="封禁结束时间" prop="forbid_date">
                      <DatePicker type="date" placeholder="Select date" v-model="formValidate.forbid_date"></DatePicker>
                    </FormItem>
                  </Form>
                </div>
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
        name: "User_Manage",
        components:{
        msider
        },
        data(){
          return{
            choice:'',
            modal1:false,
            modal2:false,
            value:'',
            formValidate: {
              forbid_type:'',
              forbid_reason:'',
              forbid_date:''
            },
            ruleValidate: {
              forbid_reason: [
                { required: true, message: 'The reason cannot be empty', trigger: 'blur' }
              ],
              forbid_type: [
                { required: true, message: 'Please select the type', trigger: 'change' }
              ],
              forbid_date: [
                { required: true, type: 'date', message: 'Please select the date', trigger: 'change' }
              ],
            },
            msg:[
              {
                id:0,
                name:'789',
                number:'16121789',
                status:'封禁',
                forbid_type:'论坛封禁',
                forbid_reason:'敏感词汇',
                password:'123123',
                forbid_date:'2012-2-2'
              },
              {
                id:1,
                name:'789',
                number:'16121789',
                status:'封禁',
                forbid_type:'论坛封禁',
                forbid_reason:'敏感词汇',
                password:'123123',
                forbid_date:'2012-2-2'
              },
              {
                id:2,
                name:'789',
                number:'16121789',
                status:'封禁',
                forbid_type:'论坛封禁',
                forbid_reason:'敏感词汇',
                password:'123123',
                forbid_date:'2012-2-2'
              },
              {
                id:3,
                name:'789',
                number:'16121789',
                status:'未封禁',
                forbid_type:'',
                forbid_reason:'',
                password:'123123',
                forbid_date:''
              },
              {
                id:4,
                name:'789',
                number:'16121789',
                status:'未封禁',
                forbid_type:'',
                forbid_reason:'',
                password:'123123',
                forbid_date:''
              }
            ]

          }
        },
      methods:{
        changechoose1(id){
          var i = 0;
          for(i=0;i<this.msg.length;i++){
            if(id == this.msg[i].id){
              this.choice = this.msg[i];
              break;
            }
          }
          this.modal1=true;
        },
        changechoose2(id){
          var i = 0;
          for(i=0;i<this.msg.length;i++){
            if(id == this.msg[i].id){
              this.choice = this.msg[i];
              break;
            }
          }
          this.modal2=true;
        },
        ok1 (id) {
          var i = 0;
          for(i=0;i<this.msg.length;i++){
            if(id == this.msg[i].id){
              this.msg[i].password = this.value;
              break;
            }
          }
          this.value = '';
          this.$Message.info('密码修改成功');
        },
        cancel1 () {
          this.value = '';
          this.$Message.info('取消修改');
        },
        ok2(id){
            var i =0;
            for(i=0;i<this.msg.length;i++){
              if(id == this.msg[i].id){
                if(this.msg[i].status == '未封禁'){
                  if(this.formValidate.forbid_date != '' && this.formValidate.forbid_reason != '' && this.formValidate.forbid_type != '') {
                    this.msg[i].status = '封禁';
                    this.msg[i].forbid_date = this.formValidate.forbid_date;
                    this.msg[i].forbid_reason = this.formValidate.forbid_reason;
                    this.msg[i].forbid_type = this.formValidate.forbid_type;
                    this.$Message.success('封禁成功');
                    this.formValidate.forbid_date = '';
                    this.formValidate.forbid_reason = '';
                    this.formValidate.forbid_type = '';
                    break;
                  }
                  else {
                    this.$Message.error('表单填写不完整');
                    break;
                  }
                }
                else {
                  this.msg[i].status = '未封禁';
                  this.msg[i].forbid_date = '';
                  this.msg[i].forbid_reason = '';
                  this.msg[i].forbid_type = '';
                  this.$Message.success('解除封禁成功');
                }
              }
            }
        },
        cancel2 () {
          this.formValidate.forbid_date = '';
          this.formValidate.forbid_reason = '';
          this.formValidate.forbid_type = '';
          this.$Message.info('取消修改');
        },
      }
    }
</script>

<style scoped>
</style>
