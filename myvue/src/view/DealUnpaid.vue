<template>
  <div id="dealUnpaid">

    <el-card v-for="deal in deals" class="el-card" :key="deal.deal_id">
      <div slot="header" class="clearfix" style="">
        <div style="">
          <span style="float: left;font-size: 10px;">
                      订单号: {{deal.deal_id}}
          </span>
          <span style="float: left;font-size: 10px;margin-left: 10px">
                      ￥{{deal.price}}
          </span>

          <el-popover
            placement="top-start"
            :title="deal.passenger.name"
            width="200"
            trigger="hover"
            :content="deal.passenger.id_number">
            <el-button slot="reference" style="float: right; padding: 3px ;font-size: 10px" type="text" >乘客信息</el-button>
          </el-popover>

          <span style="float: right;font-size: 10px;margin-right: 10px">
                      订单完成时间: {{deal.time}}
          </span>
        </div>
      </div>

      <!--      第一程-->
      <div>

        <div >
          {{deal.tickets[0].plane.start_city}} ({{deal.tickets[0].plane.departure_airfield}})--- {{deal.tickets[0].plane.end_city}}({{deal.tickets[0].plane.arrival_airfield}})
        </div>

        <div class="dd">
          <span style="font-size: 10px ">
        出发时间:{{deal.tickets[0].plane.start_day}} {{deal.tickets[0].plane.start_time}} &nbsp;&nbsp;到达时间:{{deal.tickets[0].plane.end_day}} {{deal.tickets[0].plane.end_time}}
        </span>
        </div>

      </div>


      <!--      第二程-->
      <div style="margin-top: 15px" v-if="deal.tickets[1]">

        <div style="background-color: #e8eeef; height: 1pt;"></div>
        <div style="margin-top: 15px">
          {{deal.tickets[1].plane.start_city}} ({{deal.tickets[1].plane.departure_airfield}})--- {{deal.tickets[1].plane.end_city}}({{deal.tickets[1].plane.arrival_airfield}})
        </div>
        <div class="dd">
          <span style="font-size: 10px ">
            出发时间:{{deal.tickets[1].plane.start_day}} {{deal.tickets[1].plane.start_time}} &nbsp;&nbsp;到达时间:{{deal.tickets[1].plane.end_day}} {{deal.tickets[1].plane.end_time}}
          </span>
        </div>
      </div>

    </el-card>

  </div>
</template>


<script>


import axios from "axios";
export default {
  name: "dealUnpaid",
  data(){
    return{
      deals:[],
    }
  },
  created() {
    axios.post('/dealUnpaid', {
      data:{
        id:sessionStorage.getItem("id"),
        pay:"NO"
      }
    }).then((response)=> {
      if(JSON.stringify(response.data)!='[]'){
        this.deals=response.data;
      }
    }).catch(function (error) {
      console.log(error);
    });
  }
}
</script>

<style scoped>
#dealUnpaid{
  height: 500px;
  overflow: auto;
}
.el-card{
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
}
.dd{
  margin-top: 8px;
}
</style>
