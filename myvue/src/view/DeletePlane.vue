<template>
  <el-card id="box-card">
    <div slot="header" class="clearfix">
      <span id="title">航班查询</span>
    </div>

    <div class="box-box">
      <el-card  class="box-card2">
        <el-row :gutter="20" >
          <el-col :span="9"><div class="city1">
            <label class="city-l" v-model="cities.input1">{{cities.input1}}</label>
          </div></el-col>
          <el-col :span="6"><div class="from">
            <label>=========></label>
          </div></el-col>
          <el-col :span="9"><div class="city1">
            <label class="city-l" v-model="cities.input2">{{cities.input2}}</label>
          </div></el-col>
        </el-row>

        <el-form :model="cities" ref="cities" >
          <el-row :gutter="20" >
            <el-col :span="9"><div class="city2">
              <el-input :rules="rules" class="ipt" v-model="cities.input1" placeholder="请输入出发地"></el-input>
            </div></el-col>
            <el-col :span="6"><div class="from">
              <label></label>
            </div></el-col>
            <el-col :span="9"><div class="city2">
              <el-input :rules="rules" class="ipt" v-model="cities.input2" placeholder="请输入出发地"></el-input>
            </div></el-col>
          </el-row>
        </el-form>
      </el-card>
    </div>

    <div class="block">
      <span class="demonstration">出发日期</span>
      <el-date-picker value-format="yyyy-MM-dd"
                      v-model="value1"
                      type="date"
                      placeholder="选择日期" :picker-options="pickerOptions">
      </el-date-picker>

      <el-button style="background-color: #8d9fb7 ;border-color: #8d9fb7" id="search" type="info" @click="searchHandler">查询</el-button>
    </div>


  </el-card>

</template>

<script>
import axios from "axios";

export default {
  name: "Search",
  data() {
    return {
      cities:{
        input1:"北京",
        input2:"上海"
      },
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() < Date.now();
        },
      },
      value1: '',
      rules: {
        input1: [
          {
            required: true,
            message: "请输入出发地",
            trigger: "blur",
          },
        ],
        input2: [
          { required: true, message: "请输入目的地", trigger: "blur" },
        ],
      },
    };
  },
  methods:{
    searchHandler(){
      this.$refs.cities.validate((valid) => {
        if(valid){

          sessionStorage.setItem("start_city", this.cities.input1);
          sessionStorage.setItem("end_city", this.cities.input2);
          sessionStorage.setItem("start_day",this.value1);
          this.$router.push({path:'/deleteAnyPlane'});

        }else{
          console.log('error submit!');
          return false;
        }
      })
    }

  }
}


</script>

<style scoped>

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

#box-card {
  width: auto;
  height: 498px;
}

#title{
  font-size: 40px;
}

.box-card2 {
  width: 580px;
  height: 300px;
  margin: auto;
}

.block{
  margin-top: 28px;
  margin-right: 100px;
}

#search{
  float: right;
  margin-left: 100px;
}

.city1{
  /*background-color: #B3C0D1;*/
  font-size: 50px;
  text-align: center;
  width: 200px;
  height: 120px;
  padding-top: 40%;
}
.city-l{
  display: inline-block;
  height: 100px;
}
.from{
  /*background-color: antiquewhite;*/
  text-align: center;
  height: 120px;
  padding-top: 82%;
}

.ipt{
  padding-left: 10px;
  width: 150px;
}

</style>
