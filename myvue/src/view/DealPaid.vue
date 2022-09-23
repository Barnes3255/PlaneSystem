<template>
<div id="dealPaid">

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

        <el-button style="float: right; padding: 3px ;font-size: 10px" type="text" @click="change(deal,0)">改签   </el-button>
        <div >
          {{deal.tickets[0].plane.start_city}} ({{deal.tickets[0].plane.departure_airfield}})--- {{deal.tickets[0].plane.end_city}}({{deal.tickets[0].plane.arrival_airfield}})
        </div>

        <div class="dd">
          <el-button style="float: right; padding: 3px ;font-size: 10px" type="text" @click="refund(deal,deal.tickets[0])">退票   </el-button>
        <span style="font-size: 10px ">
        出发时间:{{deal.tickets[0].plane.start_day}} {{deal.tickets[0].plane.start_time}} &nbsp;&nbsp;到达时间:{{deal.tickets[0].plane.end_day}} {{deal.tickets[0].plane.end_time}}
        </span>
        </div>

      </div>


<!--      第二程-->
      <div style="margin-top: 15px" v-if="deal.tickets[1]">

        <div style="background-color: #e8eeef; height: 1pt;"></div>
        <div style="margin-top: 15px">
          <el-button style="float: right; padding: 3px ;font-size: 10px" type="text" @click="change(deal,1)">改签   </el-button>
          {{deal.tickets[1].plane.start_city}} ({{deal.tickets[1].plane.departure_airfield}})--- {{deal.tickets[1].plane.end_city}}({{deal.tickets[1].plane.arrival_airfield}})
        </div>

        <div class="dd">          <el-button style="float: right; padding: 3px ;font-size: 10px" type="text" @click="refund(deal,deal.tickets[1])">退票   </el-button>
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

Date.prototype.Format = function (fmt) {
  var o = {
    "M+": this.getMonth() + 1, // 月份
    "d+": this.getDate(), // 日
    "h+": this.getHours(), // 小时
    "m+": this.getMinutes(), // 分
    "s+": this.getSeconds(), // 秒
    "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
    "S": this.getMilliseconds() // 毫秒
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}

export default {
  name: "DealPaid",
  data(){
    return{
      deals:[],
  }
  },
  created() {
    axios.post('/dealPaid', {
      data:{
        id:sessionStorage.getItem("id"),
        pay:"YES"
      }
    }).then((response)=> {
      if(JSON.stringify(response.data)!='[]'){
        this.deals=response.data;
      }
    }).catch(function (error) {
      console.log(error);
    });
  },methods:{
    change(deal,index){
      this.$router.push({name:'ChangePlane',query:{
         deal:deal,
          index:index
        }});
    },
    refund(deal,ticket){
      if(deal.attribute==='direct'){
        axios.post('/addDealRecord', {
          data:{
            amount:encodeURI('+'.concat(deal.price)),
            id:encodeURI(sessionStorage.getItem("id")),
            description:encodeURI("退票"),
            time:new Date().Format("yyyy-MM-dd hh:mm:ss")
          }
        }).then((response)=> {
          console.log(response.data);
        }).catch(function (error) {
          console.log(error);
        });
        axios.post('/refund_direct', {
          data:{
            deal_id:deal.deal_id,
            pay:'NO',
            ticket_id:ticket.ticket_id
          }
        }).then((response)=> {
          if(response.data){
            this.$alert('退票成功', '信息', {
              confirmButtonText: '确定'
            });
            this.$router.push({path:"/refresh"});
          }
        }).catch(function (error) {
          console.log(error);
        });
        this.$router.push({path:"/refresh"});
      }else if(deal.attribute==='transit'){
        axios.post('/addDealRecord', {
          data:{
            amount:encodeURI('+'.concat(ticket.plane.price*ticket.coun/100+190)),
            id:encodeURI(sessionStorage.getItem("id")),
            description:encodeURI("退票"),
            time:new Date().Format("yyyy-MM-dd hh:mm:ss")
          }
        }).then((response)=> {
          console.log(response.data);
        }).catch(function (error) {
          console.log(error);
        });
        axios.post('/refund_transit', {
          data:{
            deal_id:deal.deal_id,
            price:deal.price-ticket.plane.price*ticket.coun/100-190,
            ticket_id:ticket.ticket_id,
            attribute:'direct',
            deal:{
              price:ticket.plane.price*ticket.coun/100+190,
              attribute:'direct',
              id:sessionStorage.getItem('id'),
              id_number:deal.id_number,
              time:deal.time,
              pay:'NO'
            }
          }
        }).then((response)=> {
          if(response.data){
            this.$alert('退票成功', '信息', {
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
}
</script>

<style scoped>
#dealPaid{
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
