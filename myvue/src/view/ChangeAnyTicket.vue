<template>
  <div id="ticket">
    <el-row :gutter="10">

      <el-col class="cols" :span="4">
        <div id="price">￥{{ticket.coun*plane.price/100}}</div>
      </el-col>

      <el-col class="cols" :span="7">
        <span style="font-size: 10px">+70 机场建设费 &nbsp;&nbsp;&nbsp;+120 燃油费</span>
      </el-col>

      <el-col class="cols" :span="5">
        <span style="font-size: 10px">&nbsp;  {{ticket.coun/10}}折    {{ticket.level}}</span>
      </el-col>

      <el-col class="cols" :span="4">
        <span style="font-size: 4px">余票:  {{ticket.number_rest}}  张</span>
      </el-col>

      <el-col class="cols" :span="3">
        <span ><el-button style="background-color: #8d9fb7 ;border-color: #8d9fb7" type="info" @click="change(deal,plane,ticket,index)">预订</el-button></span>
      </el-col>

    </el-row>
    <hr>
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
  name: "AnyTicket",
  props:['ticket','plane','deal','index'],
  data(){
    return{
    }
  },
  methods:{
    change(deal,plane,ticket,index){
      console.log(deal);
      if(deal.attribute==='direct'){
        if((ticket.coun*plane.price/100+190)>deal.price){
          axios.post('/addDealRecord', {
            data:{
              amount:encodeURI('-'.concat((ticket.coun*plane.price/100+190)-deal.price)),
              id:encodeURI(sessionStorage.getItem("id")),
              description:encodeURI("改签"),
              time:new Date().Format("yyyy-MM-dd hh:mm:ss")
            }
          }).then((response)=> {
            console.log(response.data);
          }).catch(function (error) {
            console.log(error);
          });
        }
        axios.post('/change', {
          data:{
            price:(ticket.coun*plane.price/100+190)>deal.price?(ticket.coun*plane.price/100+190):deal.price,
            old_ticket_id:deal.tickets[index].ticket_id,
            new_ticket_id:ticket.ticket_id,
            deal_id:deal.deal_id
          }
        }).then((response)=> {
          if(response.data){
            this.$alert('改签成功', '信息', {
              confirmButtonText: '确定'
            });
            this.$router.push({path:"/dealPaid"});
          }
        }).catch(function (error) {
          console.log(error);
        });
      }else if(deal.attribute==='transit'){

        if((ticket.coun*plane.price)>deal.tickets[index].coun*deal.tickets[index].plane.price){
          axios.post('/addDealRecord', {
            data:{
              amount:encodeURI('-'.concat((ticket.coun*plane.price)/100-deal.tickets[index].coun*deal.tickets[index].plane.price/100)),
              id:encodeURI(sessionStorage.getItem("id")),
              description:encodeURI("改签"),
              time:new Date().Format("yyyy-MM-dd hh:mm:ss")
            }
          }).then((response)=> {
            console.log(response.data);
          }).catch(function (error) {
            console.log(error);
          });
        }

        axios.post('/change', {
          data:{
            price:(ticket.coun*plane.price)>deal.tickets[index].coun*deal.tickets[index].plane.price?(deal.price-deal.tickets[index].coun*deal.tickets[index].plane.price/100+ticket.coun*plane.price/100):deal.price,
            old_ticket_id:deal.tickets[index].ticket_id,
            new_ticket_id:ticket.ticket_id,
            deal_id:deal.deal_id
          }
        }).then((response)=> {
          if(response.data){
            this.$alert('改签成功', '信息', {
              confirmButtonText: '确定'
            });
            this.$router.push({path:"/dealPaid"});
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
#ticket{
  padding-top: 20px;
  height: auto;
}
#price{
  margin-left: 10px;
  text-align: left;
  font-size: 22px;
}

</style>

