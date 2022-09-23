<template>
<div id="ticket">
  <el-row :gutter="10">

    <el-col class="cols" :span="4"><div id="price">￥{{ticket1.coun*price1/100+ticket2.coun*price2/100}}</div></el-col>

    <el-col class="cols" :span="7">
      <span style="font-size: 10px">第一程:          &nbsp;&nbsp;&nbsp;&nbsp; +70 机场建设费 &nbsp;&nbsp;&nbsp;+120 燃油费</span><br>
      <span style="font-size: 10px">第二程:          &nbsp;&nbsp;&nbsp;&nbsp; +70 机场建设费 &nbsp;&nbsp;&nbsp;+120 燃油费</span>
    </el-col>

    <el-col class="cols" :span="5">
      <span style="font-size: 10px">&nbsp;  {{ticket1.coun/10}} 折    {{ticket1.level}}</span><br>
      <span style="font-size: 10px">&nbsp;  {{ticket2.coun/10}} 折    {{ticket2.level}}</span>
    </el-col>

    <el-col class="cols" :span="4">
      <span style="font-size: 4px">余票:  {{ticket1.number_rest}}  张</span><br>
      <span style="font-size: 4px">余票:  {{ticket2.number_rest}}  张</span>

    </el-col>

    <el-col class="cols" :span="3">
      <span ><el-button style="background-color: #8d9fb7 ;border-color: #8d9fb7" type="info" @click="pay(ticket1.ticket_id,ticket2.ticket_id,ticket1.coun*price1/100+ticket2.coun*price2/100+380)">预订</el-button></span>
    </el-col>

  </el-row>
  <hr>
</div>
</template>

<script>
export default {
  name: "AnyTransitTicketIn",
  props:['ticket1','ticket2','price1','price2'],
  methods:{
    pay(ticket_id1,ticket_id2,price){
      this.$router.push({name:'TransitPay',query:{
          price:price,
          ticket_id1:ticket_id1,
          ticket_id2:ticket_id2,
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
