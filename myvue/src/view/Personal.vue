<template>
<el-card style="width: 1000px ;padding: 15px ;height:540px;margin: auto">
  <div style="border-color: #333333">
    <el-row >
      <el-col :span="6">
        <div class="bb left">

          <el-row class="tac">
            <el-col :span="12">

              <div style="padding-top: 10px ;padding-bottom:10px;background-color: #dbe0e7 ;width: 238px">
                <el-row :gutter="10">
                  <el-col :span="10">
                    <div id="user"></div>
                  </el-col >
                  <el-col :span="14" >
                    <div style="height: 10px;width: 10px;padding-top: 50px;">
                      {{username}}
                    </div>
                  </el-col>
                </el-row>
              </div>

              <el-menu
                :router="true"
                default-active="2"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose" style="width:237px">

                <el-submenu index="1">
                  <template slot="title">
                    <i class="el-icon-location"></i>
                    <span>我的订单</span>
                  </template>
                  <el-menu-item-group >
                    <el-menu-item index="/dealPaid">已付款</el-menu-item>
                    <el-menu-item index="/dealUnpaid">已退款</el-menu-item>
                  </el-menu-item-group>
                </el-submenu>

                <el-menu-item index="/passengerInfo">
                  <i class="el-icon-menu"></i>
                  <span slot="title">乘客信息</span>
                </el-menu-item>

                <el-menu-item index="/personalNotice" >
                  <i class="el-icon-document"></i>
                  <span slot="title">通知信息</span>
                </el-menu-item>

                <el-menu-item index="/record" >
                  <i class="el-icon-menu"></i>
                  <span slot="title">交易记录</span>
                </el-menu-item>


              </el-menu>
            </el-col>
          </el-row>

        </div>
      </el-col>

      <el-col :span="18">
        <div class="bb right">
          <router-view></router-view>
        </div>
      </el-col>

    </el-row>

  </div>

</el-card>
</template>

<script>
export default {
  name: "Personal",
  data(){
    return{
      username:sessionStorage.getItem("username")
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>
.bb{
  height: 500px;
  border-left: solid 2px  #7f91a4;
  border-top: solid 1px  #7f91a4;
  border-bottom: solid 1px  #7f91a4;
}
.left{
  /*background-color: #7f91a4;*/
  background-color: #ffffff;
  width: 300px;
}
.right{
  background-color: #f7f7fa;
  width: 720px;
  border-right: solid 1px  #7f91a4;
}
#user{
  margin: 10px;
  height: 80px;
  width: 80px;
  background-image: url("https://static.thenounproject.com/png/5105250-200.png");
  background-size: 100% 100%;
}
</style>
