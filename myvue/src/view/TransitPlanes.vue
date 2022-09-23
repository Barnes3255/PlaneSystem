<template>
  <div>
    <el-card>
      <el-row >

        <el-col class="col" :span="4" style="margin-left: 10px">
          <span style="font-size: 20px">{{message.planes1.start_city}}</span><span style="font-size: 10px">&nbsp;&nbsp;({{message.planes1.departure_airfield}})</span><br>
          <span style="font-size: 10px">{{message.planes1.start_day}} {{message.planes1.start_time}}</span><br>
        </el-col>

        <el-col class="col" :span="1">
      <span >
        -----
      </span>
        </el-col>

        <el-col class="col" :span="8">
          <span style="font-size: 20px">{{message.planes1.end_city}}</span><span style="font-size: 10px">&nbsp;&nbsp;({{message.planes2.departure_airfield}})</span><br>
          <span style="font-size: 10px">{{message.planes1.end_day}} {{message.planes1.end_time}}</span>&nbsp;---
          <span style="font-size: 10px">{{message.planes2.start_day}} {{message.planes2.start_time}}</span>
        </el-col>

        <el-col class="col" :span="1">
      <span >
        -----
      </span>
        </el-col>

        <el-col class="col" :span="4">
          <span style="font-size: 20px">{{message.planes2.end_city}}</span><span style="font-size: 10px">&nbsp;&nbsp;({{message.planes2.arrival_airfield}})</span><br>
          <span style="font-size: 10px">{{message.planes2.end_day}} {{message.planes2.end_time}}</span>
        </el-col>

        <el-col :span="1">
          <div style="width: 30px">&nbsp;</div>
        </el-col>

        <el-col class="col" :span="2" style="margin-top: 8px">
          ￥{{message.planes1.lowest_price/100+message.planes2.lowest_price/100}}
        </el-col>

        <el-col class="col" :span="2">
          <el-button style="background-color: #8d9fb7 ;border-color: #8d9fb7" type="info" @click="preBuyHandler">预订</el-button>
        </el-col>

      </el-row>
    </el-card>
  </div>
</template>

<script>

export default {
  props: ['message'],
  name: "TransitPlanes",
  data(){
    return{
      planes:this.message
    }
  },
  methods:{
    preBuyHandler(){
      sessionStorage.setItem('plane_id1',this.planes.planes1.plane_id);
      sessionStorage.setItem('plane_id2',this.planes.planes2.plane_id);
      this.$router.push({name:'TransitTicket'});
    }
  }

}

</script>

<style scoped>
#img{
  width: 50px;
  height: 50px;
  background-size: 100% 100%;
  background-image: url("https://static.thenounproject.com/png/5109422-200.png");
}
.col{
  /*background-color: #cac6c6;*/
}
</style>
