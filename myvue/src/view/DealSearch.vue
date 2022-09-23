<template>
<div id="dealSearch">
  <div style="margin-bottom: 20px">
    <el-row :gutter="20">
      <el-col :span="20">
        <el-input
          placeholder="请输入内容"
          v-model="input">
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-button style="background-color: #8d9fb7 ;border-color: #8d9fb7" type="primary" @click="dealSearch">查询</el-button>
      </el-col>
    </el-row>
  </div>
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
        <div style="float: right; padding: 3px ;font-size: 10px ; color: #7f91a4" v-if="deal.pay==='NO'">已退款</div>
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



</div>
</template>

<script>
import axios from "axios";
const reg_deal_id=/^[0-9]{1,8}$/
export default {
  name: "DealSearch",
  data(){
    return{
      input:'',
      deals:[]
    }
  },
  methods:{
    dealSearch(){

      if (!reg_deal_id.test(this.input)){
        this.$alert('请输入正确的订单号', 'error', {
          confirmButtonText: '确定'
        });
        this.$router.push('/refresh');
      }else{
        axios.post('/dealSearch', {
          data:{
            deal_id:this.input
          }
        }).then((response)=> {
          if(JSON.stringify(response.data)!='[]'){
            this.deals=response.data;
          }else{
            this.$alert('该订单不存在', '信息', {
              confirmButtonText: '确定'
            });
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
#dealSearch{
  padding: 20px;
}
</style>
