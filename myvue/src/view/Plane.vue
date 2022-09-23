<template>
  <el-tabs id="box" type="border-card">

    <el-tab-pane ><el-button id="change" @click="change" type="text"> 北京时间</el-button>
      <span slot="label"><label>{{start_day}}</label></span>
      <AllPlanes ref="allP" v-if="planes[0].start_day!=null" v-for="(plane,index) in planes" :message="plane" :key="plane.plane_id" :buy="true"></AllPlanes>
      <div v-if="planes[0].start_day==null">
        <span>当日无航班</span>
      </div>
    </el-tab-pane>
    <el-tab-pane >
       <span slot="label">中转</span>
      <TransitPlanes  v-if="transit[0].planes1.start_day!=null" v-for="plane in transit" :message="plane" :key="plane.planes1.plane_id"></TransitPlanes>
      <div v-if="transit[0].planes1.start_day==null">
        <span>当日无航班</span>
      </div>
    </el-tab-pane>
  </el-tabs>
</template>

<script>


import AllPlanes from "./AllPlanes";
import TransitPlanes from "./TransitPlanes";
import axios from "axios";

export default {
  name: "Plane",
  components:{AllPlanes,TransitPlanes},
  data(){
    return{
      planes:[{}],
      add_time:'',
      start_day:sessionStorage.getItem("start_day"),
      transit:[{
        planes1:{}
        ,planes2:{}
      }]
    }
  },mounted(){
    axios.post('/search2', {
      data:{
        start_city:encodeURI(sessionStorage.getItem("start_city")),
        end_city:encodeURI(sessionStorage.getItem("end_city")),
        start_day:sessionStorage.getItem("start_day")
      }
    }).then((response)=> {
      if(JSON.stringify(response.data)!='[]'){
        this.planes=response.data;
      }
    }).catch(function (error) {
      console.log(error);
    });
    axios.post('/transit', {
      data:{
        start_city:encodeURI(sessionStorage.getItem("start_city")),
        end_city:encodeURI(sessionStorage.getItem("end_city")),
        start_day:sessionStorage.getItem("start_day")
      }
    }).then((response)=> {
      if(JSON.stringify(response.data)!='[]'){
        this.transit=response.data;
      }
      console.log(response.data);
    }).catch(function (error) {
      console.log(error);
    });
    axios.post('/change_time', {
      data:{
        city:encodeURI(sessionStorage.getItem("end_city")),
      }
    }).then((response)=> {
      this.add_time=response.data;
    }).catch(function (error) {
      console.log(error);
    });
  },
  methods:{
    change(){

      console.log(this.add_time);
      var bb=document.getElementById("change").innerText;
      var head=this.add_time.substr(0,1);
          if(bb==="北京时间"){
            this.planes.forEach((plane, index) => {this.$refs.allP[index].change_time(this.add_time);});

            document.getElementById("change").innerText="当地时间";

            if(head==='+'){
              this.add_time=this.add_time.replace(/[+]/,"-");
            }else if(head==='-'){
              this.add_time=this.add_time.replace(/-/,"+");
            }

          }else if(bb==="当地时间"){
            this.planes.forEach((plane, index) => {this.$refs.allP[index].change_time(this.add_time);});
            document.getElementById("change").innerText="北京时间";

            if(head==='+'){
              this.add_time=this.add_time.replace(/[+]/,"-");
            }else if(head==='-'){
              this.add_time=this.add_time.replace(/-/,"+");
            }

          }

    }
  }
}
</script>

<style scoped>

#box{
  margin: auto;
  width: 1000px;
  min-height: 540px;
}

</style>
