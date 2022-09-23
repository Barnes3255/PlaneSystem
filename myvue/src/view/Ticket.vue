<template>
<el-card id="box">
  <AllPlanes :message="plane" :buy="false"></AllPlanes>
  <hr>
  <el-card id="bbox">
    <el-row>
      <el-col :span="23"><div id="line1"><AnyTicket v-for="ticket in tickets" :ticket="ticket" :key="ticket.coun" :price="plane.price"></AnyTicket></div></el-col>
    </el-row>
  </el-card>
</el-card>
</template>

<script>


import AllPlanes from "./AllPlanes";
import axios from "axios";
import AnyTicket from "./AnyTicket";

export default {
  name: "Ticket",
  components:{AllPlanes,AnyTicket},
  data(){
    return{
      plane:"",
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
  margin: auto;
  width: 1000px;
  min-height: 540px;
}
#bbox {
  margin: auto;
}
#line1{
  margin-left: 40px;
  margin-top: 30px;
  padding-left: 20px;
  border-left: solid 2px #333333;
}
</style>
