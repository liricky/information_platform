<template>
    <div>
      <tophead></tophead>
      <div>
        <div class="back">
          <div class="leftback">
            <font id="font" size="6">请选择申诉类别</font>
            <br>
            <Select v-model="model4" size="large" style="width:100px">
              <Option v-for="item in optionlist" :value="item.value" :key="item.value">{{item.label}}</Option>
            </Select>
          </div>
          <div class="rightback">
            <div id="inputbox">
              <br>
              <Input class="input" v-model="value1" size="large" placeholder="申诉标题"/>
              <Input class="input" v-model="value2" type="textarea" :rows="25" placeholder="申诉理由" />
              <br>
              <br>
              <button id="sendbutton" type="primary" size="large">发 送</button>
            </div>
            <br>
          </div>
        </div>
      </div>
      <bottom></bottom>
    </div>
</template>
<style>
  #sendbutton{
    margin: auto;
  }
  .leftback{
    width: 25%;
    margin: auto;
    float: left;
    background: white;
    border: 2px solid black;
  }
  .rightback{
    margin: auto;
    float: right;
    width: 70%;
    background: lightgrey;
  }
  .back{
    width: 80%;
    display: flex;
    margin: auto;
  }
  #inputbox{
    margin: auto;
  }
  .input{
    margin: auto;
    width: 85%;
  }
</style>
<script>
    import tophead from '@/components/Head';
    import bottom from '@/components/Bottom';

    export default {
        data() {
            return {
                optionlist:[
                  {
                    value: "论坛封禁",
                    label: "论坛封禁"
                  },
                  {
                    value: "互助封禁",
                    label: "互助封禁"
                  }
                ]
            }
        },
        components: {
          tophead,
          bottom
        }
    }
</script>
