<template>
  <div class="login-container">
    <el-form
      :model="ruleForm2"
      :rules="rules2"
      status-icon
      ref="ruleForm2"
      label-position="left"
      label-width="0px"
      class="demo-ruleForm login-page"
    >
      <h3 class="title">登录</h3>
      <el-form-item prop="username">
        <el-input
          type="text"
          v-model="ruleForm2.username"
          auto-complete="off"
          placeholder="用户名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          type="password"
          v-model="ruleForm2.password"
          auto-complete="off"
          placeholder="密码"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button
          type="info"
          style="width: 100% ;background-color: #8d9fb7 ;border-color: #8d9fb7  "
          @click="handleSubmit"
          :loading="logining"
        >登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "login",
  data() {
    return {
      suc:"",
      logining: false,
      ruleForm2: {
        id:0,
        username: "",
        password: "",
      },
      rules2: {
        username: [
          {
            required: true,
            message: "请输入您的账号",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入您的密码", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    handleSubmit(){
      this.$refs.ruleForm2.validate((valid) => {
        if(valid){
          this.logining = true;
          axios.post('/login', {
            data:{
              name:encodeURI(this.ruleForm2.username),
              pwd:encodeURI(this.ruleForm2.password)
            }
          }).then((response)=> {
            this.suc=response.data;
            console.log(response);

            if(this.suc.id!=null&&this.suc.id==90000000){

              this.logining = false;
              sessionStorage.setItem('id', this.suc.id);
              sessionStorage.setItem('username', this.ruleForm2.username);
              sessionStorage.setItem('root',"1");
              this.$alert('登录成功', '登录成功', {
                confirmButtonText: 'ok'
              })
              console.log(sessionStorage.getItem("id"));
              console.log(sessionStorage.getItem("root"));
              this.$router.push({path: '/root'});

            }else if(this.suc.id!=null){

              this.logining = false;
              sessionStorage.setItem('id', this.suc.id);
              sessionStorage.setItem('username', this.ruleForm2.username);
              sessionStorage.setItem('root',"0");
              this.$alert('登录成功', '登录成功', {
                confirmButtonText: 'ok'
              })
              console.log(sessionStorage.getItem("id"));
              console.log(sessionStorage.getItem("root"));
              this.$router.push({path: '/search'});

            } else{
              this.logining = false;
              console.log(this.suc);
              this.$alert('用户名或密码错误', '登录失败', {
                confirmButtonText: 'ok'
              })
            }

          }).catch(function (error) {
            console.log(error);
          });

        }else{
          console.log('error submit!');
          return false;
        }
      })
    }
  },
};
</script>


<style scoped>
.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
label.el-checkbox {
  margin: 0px 0px 15px;
  text-align: left;
}
h3{
  text-align: left;
}

</style>


