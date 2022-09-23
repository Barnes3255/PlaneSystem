<template>
  <el-card id="box">
    <AllPlanes :message="plane1" :buy="false"></AllPlanes>
    <AllPlanes :message="plane2" :buy="false"></AllPlanes>
    <hr>
    <el-card id="bbox">
      <el-row>
        <el-col :span="23"><div id="line1"><AnyTransitTicket v-for="ticket1 in tickets1" :ticket1="ticket1" :key="ticket1.coun" :price1="plane1.price" :price2="plane2.price" :tickets2="tickets2"></AnyTransitTicket></div></el-col>
      </el-row>
    </el-card>
  </el-card>
</template>

<script>

import AnyTransitTicket from "./AnyTransitTicket";
import AllPlanes from "./AllPlanes";
import axios from "axios";
import AnyTransitTicketIn from "./AnyTransitTicketIn";


export default {
  name: "TransitTicket",
  components:{AllPlanes,AnyTransitTicket,AnyTransitTicketIn},
  data(){
    return{
      plane1:"",
      plane2:"",
      tickets1:[{
        plane_id:0,
        number_rest:0,
        number_all:0,
        coun:0,
      }
      ],
      tickets2:[{
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
        plane_id:sessionStorage.getItem('plane_id1')
      }
    }).then((response)=> {

      this.plane1=response.data;

    }).catch(function (error) {
      console.log(error);
    });

    axios.post('/search1', {
      data:{
        plane_id:sessionStorage.getItem('plane_id2')
      }
    }).then((response)=> {

      this.plane2=response.data;

    }).catch(function (error) {
      console.log(error);
    });

    axios.post('/ticket', {
      data:{
        plane_id:sessionStorage.getItem('plane_id1')
      }
    }).then((response)=> {

      this.tickets1=response.data;
      console.log(this.tickets1);

    }).catch(function (error) {
      console.log(error);
    });

    axios.post('/ticket', {
      data:{
        plane_id:sessionStorage.getItem('plane_id2')
      }
    }).then((response)=> {

      this.tickets2=response.data;
      console.log(this.tickets2);

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
