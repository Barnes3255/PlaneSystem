<template>
<div>
<el-card>
  <el-row :gutter="20">
    <el-col :span="1">
      <div style="width: 30px">&nbsp;</div>
    </el-col>
    <el-col class="col" :span="2">
      <div id="img"></div>
    </el-col>

    <el-col class="col" :span="4">
      <span style="font-size: 20px">{{message.start_city}}</span><span style="font-size: 10px">&nbsp;&nbsp;({{message.departure_airfield}})</span><br>
      <span style="font-size: 10px">{{message.start_day}} {{message.start_time}}</span>

    </el-col>

    <el-col class="col" :span="2">
      <span >
        -------
      </span>
    </el-col>

    <el-col class="col" :span="4">
      <span style="font-size: 20px">{{message.end_city}}</span><span style="font-size: 10px">&nbsp;&nbsp;({{message.arrival_airfield}})</span><br>
      <span style="font-size: 10px">{{message.end_day}} {{message.end_time}}</span>
    </el-col>

    <el-col :span="5">
      <div style="width: 30px">&nbsp;</div>
    </el-col>

    <el-col v-if="buy" class="col" :span="2">
      ￥{{message.lowest_price/100}}
    </el-col>

    <el-col v-if="!buy" class="col" :span="5" style="padding-top: 10px">
      <span style="font-size: 25px">
            ￥{{message.price}}
    </span>
    </el-col>

    <el-col class="col" :span="4">
      <el-button style="background-color: #8d9fb7 ;border-color: #8d9fb7" v-if="buy"  type="info" @click="preBuyHandler">预订</el-button>
    </el-col>
  </el-row>
</el-card>
</div>
</template>

<script>

function time_transformation(t_day,t_time,add_time) {
  var time=t_day.concat(" ").concat(t_time);
  var d=new Date(time);
  var head=add_time.substring(0,1);
  var add_time1=add_time.substring(1);
  if(head=='+'){
     d.setHours(d.getHours()+parseInt(add_time1));
  }else if(head=='-'){
    d.setHours(d.getHours()-add_time1);
  }
  return d;
}

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
  props: ['message','buy'],
  name: "AllPlanes",
  data(){
    return{
      plane:this.message,
    }
  },
  methods:{
    preBuyHandler(){
      sessionStorage.setItem('plane_id',this.message.plane_id);
      this.$router.push({name:'Ticket'});
    },
    change_time(add_time){
      var date=time_transformation(this.message.end_day,this.message.end_time,add_time);
      this.message.end_day=date.Format("yyyy-MM-dd");
      this.message.end_time=date.Format("hh:mm:ss");
    }
  },
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
