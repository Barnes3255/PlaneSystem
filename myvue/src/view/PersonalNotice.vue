<template>
  <div id="notice" >
    <el-card v-for="notice in notices" class="el-card" :key="notice.notice_id">
      <div slot="header" class="clearfix" >
        <div>
          <span style="float:left ;font-size: 10px">  {{notice.notice_time}}</span>
        </div>
      </div>

      <div>
        <div class="dd">
           <span>您预订的{{notice.plane_id}}次航班已取消，给您带来了不便，敬请谅解！</span>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Personal_notice",
  data(){
    return{
      notices:[]
    }
  },
  created() {
    axios.post('/getNotice', {
      data:{
        id:encodeURI(sessionStorage.getItem("id")),
      }
    }).then((response)=> {
      if(JSON.stringify(response.data)!='[]'){
        this.notices=response.data;
      }

    }).catch(function (error) {
      console.log(error);
    });
  },
}
</script>

<style scoped>

#notice{
  height: 500px;
  overflow: auto;
}
.el-card{
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
}
.dd{
  width: 600px;
  font-size: 12px;
  margin-bottom: 6px;
}

</style>
