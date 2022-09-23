<template>
  <el-card id="box">
    <ChangeAnyPlaneIn :message="plane" :buy="false"></ChangeAnyPlaneIn>
    <hr>
    <el-card id="bbox">
      <el-row>
        <el-col :span="23"><div id="line1"><ChangeAnyTicket v-for="ticket in tickets" :ticket="ticket" :key="ticket.coun" :plane="plane" :deal="deal" :index="index"></ChangeAnyTicket></div></el-col>
      </el-row>
    </el-card>
  </el-card>
</template>

<script>


import axios from "axios";
import ChangeAnyPlaneIn from "./ChangeAnyPlaneIn";
import ChangeAnyTicket from "./ChangeAnyTicket";
import RootPlane from "./RootPlane";
import AllPlanes from "./AllPlanes";

export default {
  name: "ChangeTicket",
  components:{AllPlanes, RootPlane, ChangeAnyPlaneIn,ChangeAnyTicket},
  data(){
    return{
      index:this.$route.query.index,
      plane:"",
      deal:this.$route.query.deal,
      tickets:[{
        plane_id:0,
        number_rest:0,
        number_all:0,
        coun:0,
      }
      ]
    }
  },
  mounted() {
    axios.post('/search1', {
      data:{
        plane_id:sessionStorage.getItem('plane_id')
      }
    }).then((response)=> {

      this.plane=response.data;

    }).catch(function (error) {
      console.log(error);
    });

    axios.post('/ticket', {
      data:{
        plane_id:sessionStorage.getItem('plane_id')
      }
    }).then((response)=> {

      this.tickets=response.data;
      console.log(this.tickets);

    }).catch(function (error) {
      console.log(error);
    });
  }
}
</script>

<style scoped>
#box{
  width: auto;
  height: auto;
}
#bbox {

  height: 349px;
  overflow: auto;
}
#line1{
  margin-left: 40px;
  margin-top: 30px;
  padding-left: 20px;
  border-left: solid 2px #333333;
}
</style>
