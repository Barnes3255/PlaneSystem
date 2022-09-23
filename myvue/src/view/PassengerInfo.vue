<template>
  <div id="passengerInfo">

    <el-popover placement="top" style="width: 500px">
      <el-form :label-position="labelPosition" label-width="60px" :model="info">
        <el-form-item label="姓名">
          <el-input v-model="info.name"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="info.phone"></el-input>
        </el-form-item>
        <el-form-item label="身份证">
          <el-input v-model="info.id"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit" style="background-color: #8d9fb7 ;border-color: #8d9fb7">添加</el-button>
        </el-form-item>

      </el-form>
      <el-button style="float: right; padding: 14px ;font-size: 12px" type="text" slot="reference">添加乘客信息</el-button>
    </el-popover>

    <div style="margin-top: 45px;margin-bottom: 10px">
      <el-card v-for="passenger in passengers" class="el-card" :key="passenger.id_number">
      <div slot="header" class="clearfix" >
        <div>
          <span style="float:left ">  {{passenger.name}}</span>
          <el-button style="float: right; padding: 3px ;font-size: 10px" type="text" @click="del(passenger)">删除   </el-button>
        </div>
      </div>

      <div>
        <div class="dd">
          <span class="sp">
            姓名： {{passenger.name}}
          </span>
        </div>
        <div class="dd">
        <span class="sp">
        身份证号码： {{passenger.id_number}}
        </span>
        </div>
        <div class="dd">
        <span class="sp">
        电话号码： {{passenger.phone_number}}
        </span>
        </div>
      </div>
    </el-card>
    </div>
  </div>
</template>


<script>

const reg_phone=/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
const reg_id=/^([1-6][1-9]|50)\d{4}(18|19|20)\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/

import axios from "axios";
export default {
  name: "PassengerInfo",
  data(){
    return{
      labelPosition: 'right',
      info:{},
      passengers:[],
    }
  },
  created() {
    axios.post('/getPassengers', {
      data:{
        id:encodeURI(sessionStorage.getItem("id")),
      }
    }).then((response)=> {
      if(JSON.stringify(response.data)!='[]'){
        this.passengers=response.data;
      }
      console.log(this.passengers);
      console.log(response.data);
    }).catch(function (error) {
      console.log(error);
    });
  },
  methods:{
    onSubmit(){
      if(!reg_id.test(this.info.id)){
        this.$alert('请输入正确的身份证号码', 'error', {
          confirmButtonText: '确定'
        });
      }else {
        if(!reg_phone.test(this.info.phone)){
          this.$alert('请输入正确的电话号码', 'error', {
            confirmButtonText: '确定'
          });
        }else{
          axios.post('/addPassenger', {
            data:{
              //用户id
              id:encodeURI(sessionStorage.getItem("id")),
              //乘客信息
              id_number:encodeURI(this.info.id),
              name:encodeURI(this.info.name),
              phone_number:encodeURI(this.info.phone),
            }
          }).then((response)=> {
            if(response.data){
              this.$alert('添加成功', '信息', {
                confirmButtonText: '确定'
              });
            }
            this.info={}

          }).catch(function (error) {
            console.log(error);
          });
          this.refresh();
        }}

    },
    del(passenger){
      axios.post('/deletePassenger', {
        data:{
          //用户id
          id:encodeURI(sessionStorage.getItem("id")),
          //乘客信息
          id_number:encodeURI(passenger.id_number),
        }
      }).then((response)=> {
        console.log(response);
        this.info={}
        if(response.data){
          this.$alert('删除成功', '信息', {
            confirmButtonText: '确定'
          });
          this.refresh();
        }

      }).catch(function (error) {
        console.log(error);
      });

    }
    ,refresh(){
      this.$router.push({path:"/refresh"});
    }
  }
}
</script>

<style scoped>
#passengerInfo{
  height: 500px;
  overflow: auto;
}
.el-card{
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
}
.dd{
  width: 600px;
  font-size: 12px;
  margin-bottom: 6px;
}
.sp{
  float: left;
  margin-left: 10px;
}
</style>
