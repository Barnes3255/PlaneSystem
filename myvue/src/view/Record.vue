<template>
  <div id="record" >
    <el-card v-for="record in records" class="el-card" :key="record.record_id">
      <div slot="header" class="clearfix" >
        <div>
          <span style="float:left ;font-size: 10px"> {{record.time}}</span>
        </div>
      </div>

      <div>
        <div class="dd">
          <span style="float: left;">{{record.description}}</span>
          <span style="float: right">{{record.amount}}</span>
        </div>
      </div>

    </el-card>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "Record",
  data(){
    return{
      records:[],
    }
  },
  created() {
    axios.post('/getRecord', {
      data:{
        id:encodeURI(sessionStorage.getItem("id")),
      }
    }).then((response)=> {
      if(JSON.stringify(response.data)!='[]'){
        this.records=response.data;
      }

    }).catch(function (error) {
      console.log(error);
    });
  }
}
</script>

<style scoped>
.el-card{
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
}
.dd{
  width: 600px;
  font-size: 16px;
  padding-bottom: 10px;
}
#record{
  height: 500px;
  overflow: auto;
}
</style>
