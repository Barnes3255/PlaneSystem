<template>
  <div id="delAnyPlane">
    <el-tabs id="box" type="border-card">
      <el-tab-pane >
        <span slot="label"><label>{{start_day}}</label></span>
        <RootPlane  v-if="planes[0].start_day!=null" v-for="plane in planes" :message="plane" :key="plane.plane_id" ></RootPlane>
        <div v-if="planes[0].start_day==null">
          <span>当日无航班</span>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>

</template>

<script>

import axios from "axios";
import RootPlane from "./RootPlane";

export default {
  name: "DeleteAnyPlane",
  components:{RootPlane},
  data(){
    return{
      planes:[{

      }],
      start_day:sessionStorage.getItem("start_day"),
      transit:[{
        planes1:{}
        ,planes2:{}
      }]
    }
  },created(){
    axios.post('/search4', {
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

  }
}
</script>

<style scoped>

#delAnyPlane{
  width: auto;
  height: 498px;
  overflow: auto;
}
#box{
  width: auto;
  min-height: 498px;
}

</style>
