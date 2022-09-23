<template>

  <el-card id="pay">

    <label id="l_price" v-show="false">{{this.$route.query.price}}</label>
    <label id="l_ticket_id" v-show="false">{{this.$route.query.ticket_id}}</label>

    <el-tabs>
      <el-tab-pane label="填写付款信息">
        <el-form :label-position="labelPosition" label-width="80px" :model="info">
          <el-form-item label="姓名">
            <el-input v-model="info.name"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="info.phone"></el-input>
          </el-form-item>
          <el-form-item label="身份证号">
            <el-input v-model="info.id"></el-input>
          </el-form-item>

          <el-form-item>
            <label style="font-size: 20px ">￥ {{this.$route.query.price}}&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <el-button type="primary" @click="onSubmit1()" style="background-color: #8d9fb7 ;border-color: #8d9fb7">支付</el-button>
          </el-form-item>

        </el-form>
      </el-tab-pane>
      <el-tab-pane label="已有乘客信息">
        <el-form :label-position="labelPosition" label-width="80px"  >

          <el-form-item  label="已有乘客:" >
            <el-radio-group v-model="radio">
              <my-radio v-for="passenger in passengers" :passenger="passenger" :key="passenger.id"></my-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item>
            <label style="font-size: 20px ">￥ {{this.$route.query.price}}&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <el-button type="primary" @click="onSubmit2()" style="background-color: #8d9fb7 ;border-color: #8d9fb7">支付</el-button>
          </el-form-item>

        </el-form>
      </el-tab-pane>
    </el-tabs>


  </el-card>
</template>

<script>
import axios from "axios";
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
  props:['price','ticket_id1','ticket_id2'],
  name: "TransitPay",

  data() {
    return {
      p:this.$route.query,
      passengers:'',
      labelPosition: 'right',
      pass:{},
      info: {
        name: '',
        phone: '',
        id: '',
      },
      radio:''
    };
  },
  created() {
    axios.post('/getPassengers', {
      data:{
        id:encodeURI(sessionStorage.getItem("id")),
      }
    }).then((response)=> {
      if(JSON.stringify(response.data)!='[]'){
        this.passengers=response.data;
      }
      console.log(this.passengers);
      console.log(response.data);
    }).catch(function (error) {
      console.log(error);
    });
  }
  ,methods:{
    onSubmit1(){
      axios.post('/addDealRecord', {
        data:{
          amount:encodeURI('-'.concat(this.p.price)),
          id:encodeURI(sessionStorage.getItem("id")),
          description:encodeURI("购票"),
          time:new Date().Format("yyyy-MM-dd hh:mm:ss")
        }
      }).then((response)=> {
        console.log(response.data);
      }).catch(function (error) {
        console.log(error);
      });
      // 向订单表中插入一条数据,并向deal_ticket表中插入订单和飞机票的绑定信息，并更新飞机票中的余票数
      axios.post('/pay2', {
        data:{
          price:encodeURI(this.p.price),
          attribute:encodeURI("transit"),
          id:encodeURI(sessionStorage.getItem("id")),
          id_number:encodeURI(this.info.id),
          ticket_id1:encodeURI(this.p.ticket_id1),
          ticket_id2:encodeURI(this.p.ticket_id2),
          time:new Date().Format("yyyy-MM-dd hh:mm:ss")
        }
      }).then((response)=> {
        console.log(response);
      }).catch(function (error) {
        console.log(error);
      });
      // 添加乘客信息并加入用户-乘客信息绑定表，将乘客信息绑定给该用户
      axios.post('/addPassenger', {
        data:{
          //用户id
          id:encodeURI(sessionStorage.getItem("id")),
          //乘客信息
          id_number:encodeURI(this.info.id),
          name:encodeURI(this.info.name),
          phone_number:encodeURI(this.info.phone),
        }
      }).then((response)=> {
          if(response.data){
            this.$alert('支付成功', '信息', {
              confirmButtonText: '确定'
            });}
      }).catch(function (error) {
        console.log(error);
      });
      this.$router.push({path:'/search'});
    },
    onSubmit2(){
      axios.post('/addDealRecord', {
        data:{
          amount:encodeURI('-'.concat(this.p.price)),
          id:encodeURI(sessionStorage.getItem("id")),
          description:encodeURI("购票"),
          time:new Date().Format("yyyy-MM-dd hh:mm:ss")
        }
      }).then((response)=> {
        console.log(response.data);
      }).catch(function (error) {
        console.log(error);
      });
      //在已有乘客信息的情况下，向订单表中插入一条数据
      axios.post('/pay2', {
        data:{
          id_number:encodeURI(document.getElementsByClassName(this.radio)[0].innerText),
          price:encodeURI(this.p.price),
          attribute:encodeURI("transit"),
          id:encodeURI(sessionStorage.getItem("id")),
          ticket_id1:encodeURI(this.p.ticket_id1),
          ticket_id2:encodeURI(this.p.ticket_id2),
          time:new Date().Format("yyyy-MM-dd hh:mm:ss")
        }
      }).then((response)=> {
        if(response.data){
          this.$alert('支付成功', '信息', {
            confirmButtonText: '确定'
          });}
      }).catch(function (error) {
        console.log(error);
      });

      this.$router.push({path:'/search'});
    }
  },
  components:{
    "my-radio":{
      props:['passenger'],
      template:' <div style="float: left"><el-radio  :label="passenger.id+passenger.name" ></el-radio>' +
        '<label :class="passenger.id+passenger.name">{{passenger.id_number}}</label>' +
        '<label :class="passenger.id+passenger.name">{{passenger.phone_number}}</label></div>'
    }
  }
}
</script>

<style scoped>

#pay {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 500px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

</style>
