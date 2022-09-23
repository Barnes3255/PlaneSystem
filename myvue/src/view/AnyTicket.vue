<template>
<div id="ticket">
<el-row :gutter="10">

  <el-col class="cols" :span="4">
    <div id="price">￥{{ticket.coun*price/100}}</div>
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
    <span ><el-button style="background-color: #8d9fb7 ;border-color: #8d9fb7" type="info" @click="pay(ticket.ticket_id,ticket.coun*price/100+190)">预订</el-button></span>
  </el-col>

</el-row>
<hr>
</div>
</template>

<script>
export default {
  name: "AnyTicket",
  props:['ticket','price'],
  data(){
    return{
    }
  },
  methods:{
    pay(ticket_id,price){
      console.log(price);
      console.log(ticket_id);
      this.$router.push({name:'Pay',query:{
        price:price,
        ticket_id:ticket_id
        }})
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
