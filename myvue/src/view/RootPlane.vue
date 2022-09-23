<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="1">
          <div style="margin-top: 7px;margin-right: 3px"><div id="img"></div></div>
        </el-col>

        <el-col class="col" :span="6">
          <span style="font-size: 20px">{{message.start_city}}</span><span style="font-size: 10px">&nbsp;&nbsp;({{message.departure_airfield}})</span><br>
          <span style="font-size: 10px">{{message.start_day}} {{message.start_time}}</span>

        </el-col>

        <el-col class="col" :span="2">
      <span >
        ----
      </span>
        </el-col>

        <el-col class="col" :span="6">
          <span style="font-size: 20px">{{message.end_city}}</span><span style="font-size: 10px">&nbsp;&nbsp;({{message.arrival_airfield}})</span><br>
          <span style="font-size: 10px">{{message.end_day}} {{message.end_time}}</span>
        </el-col>

        <el-col :span="2">
          <div >&nbsp;</div>
        </el-col>


        <el-col  class="col" :span="3" style="padding-top: 5px">
      <span style="font-size: 17px">
            ￥{{message.price}}
      </span>
        </el-col>

        <el-col class="col" :span="4">
          <el-button style="background-color: #8d9fb7 ;border-color: #8d9fb7"  type="info" @click="delHandler(message.plane_id)">取消</el-button>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>


import axios from "axios";

export default {
  props: ['message'],
  name: "RootPlane",
  data(){
    return{
      plane:this.message,
    }
  },
  methods:{
    delHandler(plane_id){
      axios.post('/cancelPlaneRecord', {
        data:{
          plane_id:plane_id
        }
      }).then((response)=> {

      }).catch(function (error) {
        console.log(error);
      });
      axios.post('/cancelPlane', {
        data:{
          plane_id:plane_id
        }
      }).then((response)=> {
        if(response.data){
          this.$alert('取消成功', '信息', {
            confirmButtonText: '确定'
          });
          this.$router.push({path:"/refresh"});
        }
      }).catch(function (error) {
        console.log(error);
      });
    }
  }
}

</script>

<style scoped>
#img{
  width: 30px ;
  height: 30px ;
  background-size: 100% 100% ;
  background-image: url("https://static.thenounproject.com/png/5109422-200.png");
}
.col{
  /*background-color: #cac6c6;*/
}
</style>
