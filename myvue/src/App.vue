<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="1">
            <div id="header-img" @click="getPerson"></div>
          </el-col>
          <el-col :span="22">
            &nbsp;
          </el-col>
          <el-col :span="1">
            <div id="search-img" @click="search"></div>
          </el-col>
        </el-row>
      </el-header>
      <el-main><router-view></router-view></el-main>
      <el-footer>&nbsp;</el-footer>
    </el-container>

  </div>
</template>


<script>
import Login from './view/Login'
export default {
  name: 'App',
  data(){
    return{
      circleUrl: "https://static.thenounproject.com/png/5113066-200.png",
      person:Login
    }
  },
  created() {
    if (sessionStorage.getItem("root")=="1"){
      this.$router.push({path: '/root'});
    }else if(sessionStorage.getItem("root")=="0") {
      this.$router.push({path: '/personal'});
    }else{
      this.$router.push({path: '/login'});

    }
  }
  ,methods:{

    getPerson(){

      if (sessionStorage.getItem("root")=="1"){
        this.$router.push({path: '/root'});

      }else if(sessionStorage.getItem("root")=="0") {
        this.$router.push({path: '/personal'});
      }

    },search(){
        this.$router.push({path: '/search'});
    }
  }
}
</script>

<style>

.el-header, .el-footer {
background-color: #B3C0D1;
color: #333;
text-align: center;
line-height: 60px;
}


.el-main {
background-color: #E9EEF3;
color: #333;
text-align: center;
}


#header-img{
  margin-top:5px ;
  width: 50px;
  height: 50px;
  background-image: url("https://static.thenounproject.com/png/5105250-200.png");
  background-size: 100% 100%;
}
#search-img{
  margin-top:10px ;
  width: 40px;
  height: 40px;
  background-image: url("https://static.thenounproject.com/png/4648873-200.png");
  background-size: 100% 100%;
}

</style>
