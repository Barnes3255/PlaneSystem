<template>
  <div id="addPlane">
    <div style="text-align: center;margin-top: 15px;margin-bottom: 15px"><h3>航班信息</h3></div>
    <el-form :label-position="labelPosition" label-width="80px" :model="info" >
      <el-row >
        <el-col :span="2">&nbsp</el-col>
        <el-col :span="9">
          <el-form-item label="出发城市">
            <el-input v-model="info.start_city"></el-input>
          </el-form-item>
          <el-form-item label="到达城市">
            <el-input v-model="info.end_city"></el-input>
          </el-form-item>
          <el-form-item label="出发日期">
            <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="info.start_day" style="width: 100%;"></el-date-picker>
          </el-form-item>
          <el-form-item label="到达日期">
            <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="info.end_day" style="width: 100%;"></el-date-picker>
          </el-form-item>
          <el-form-item label="班次价格">
            <el-input v-model="info.price"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="2">&nbsp</el-col>
        <el-col :span="9">
          <el-form-item label="出发机场">
            <el-input v-model="info.departure_airfield"></el-input>
          </el-form-item>

          <el-form-item label="到达机场">
            <el-input v-model="info.arrival_airfield"></el-input>
          </el-form-item>

          <el-form-item label="出发时间">
            <el-time-picker value-format="HH:mm:ss" placeholder="选择时间" v-model="info.start_time" style="width: 100%;"></el-time-picker>
          </el-form-item>

          <el-form-item label="到达时间">
            <el-time-picker value-format="HH:mm:ss" placeholder="选择时间" v-model="info.end_time" style="width: 100%;"></el-time-picker>
          </el-form-item>

        </el-col>

      </el-row>

      <div style="margin: 20px;width: auto;height: 1px;background-color: #cacfda "></div>

      <div style="text-align: center;margin-top: 15px;margin-bottom: 15px;margin-left: 81px">
        <el-button type="primary" @click="addTicket()" style="background-color: #8d9fb7 ;border-color: #8d9fb7">添加一种机票类型</el-button>
        <el-button type="primary" @click="delTicket()" style="background-color: #8d9fb7 ;border-color: #8d9fb7">减少一种机票类型</el-button>
      </div>

      <div v-for="(ticket,index) in info.tickets" :key="index">
        <ticket :index="index" :tickets="info.tickets"> </ticket>
      </div>

      <el-form-item>
        <el-button type="primary" @click="onSubmit" style="background-color: #8d9fb7 ;border-color: #8d9fb7">添加</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
import axios from "axios";

const reg_ticket_number=/^([1-9]|[1-9][0-9]|100)$/
const reg_ticket_count=/^([1-9]|[1-9][0-9]|[1][0-9][0-9]|200)$/
const reg_charater=/[\u4e00-\u9fa5]{1,10}/
const reg_price=/^([1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9]|10000)$/
export default {
  name: "addPlane",
  data() {

    return {
      labelPosition: 'right',
      info: {
        end_city: '',
        start_city: '',
        start_day:'',
        end_day:'',
        start_time:'',
        end_time:'',
        price:'',
        departure_airfield:'',
        arrival_airfield:'',
        tickets:[
          {coun:"",
            number_all:""
          }
        ]
      },
    }

  },
  components: {
    ticket: {
      props:['index','tickets'],
      template:'<el-row> ' +
        '<el-col :span="2">&nbsp</el-col> ' +
        '<el-col :span="9">' +
        '<el-form-item label="折扣">' +
        '<el-input v-model="tickets[index].coun"></el-input>' +
        '</el-form-item>  ' +
        '</el-col> ' +
        '<el-col :span="2">&nbsp</el-col> ' +
        '<el-col :span="9">' +
        '<el-form-item label="票数">' +
        '<el-input v-model="tickets[index].number_all"></el-input>' +
        '</el-form-item>  ' +
        '</el-col>' +
        '</el-row>'
    }
  },
  methods:{
    addTicket(){
      this.info.tickets.push({coun:'',number_all:''});
    },delTicket(){
      this.info.tickets.pop({});
    },
    onSubmit() {
      var sub=true;
      if(!reg_charater.test(this.info.start_city)){
        this.$alert('请输入正确的出发城市', 'error', {
          confirmButtonText: '确定'
        });
      }else if(!reg_charater.test(this.info.end_city)){
        this.$alert('请输入正确的终点城市', 'error', {
          confirmButtonText: '确定'
        });
      }
      else if(!reg_charater.test(this.info.departure_airfield)){
        this.$alert('请输入正确的出发机场', 'error', {
          confirmButtonText: '确定'
        });
      }
      else if(!reg_charater.test(this.info.arrival_airfield)){
        this.$alert('请输入正确的到达机场', 'error', {
          confirmButtonText: '确定'
        });
      }else if(!reg_price.test(this.info.price)){
        this.$alert('请设置0-10000的机票价格', 'error', {
          confirmButtonText: '确定'
        });
      }else{
        this.info.tickets.forEach((ticket,index)=>{
          if(!reg_ticket_count.test(ticket.coun)){
            this.$alert('请设置大于0-200的机票折扣', 'error', {
              confirmButtonText: '确定'
            });
            sub=false;
          }else if(!reg_ticket_number.test(ticket.number_all)){
            this.$alert('请设置0-100的机票数目', 'error', {
              confirmButtonText: '确定'
            });
            sub=false;
          }
        });
        if(sub){
          axios.post('/addPlane', {
            data:{
              end_city: encodeURI(this.info.end_city),
              start_city: encodeURI(this.info.start_city),
              start_day:encodeURI(this.info.start_day),
              end_day:encodeURI(this.info.end_day),
              start_time:encodeURI(this.info.start_time),
              end_time:encodeURI(this.info.end_time),
              price:encodeURI(this.info.price),
              departure_airfield:encodeURI(this.info.departure_airfield),
              arrival_airfield:encodeURI(this.info.arrival_airfield),
              tickets:this.info.tickets
            }
          }).then((response)=> {
            if(response.data){
              this.$alert('添加成功', '信息', {
                confirmButtonText: '确定'
              });
              this.$router.push("/root");
            }
          }).catch(function (error) {
            console.log(error);
          });
        }
      }
      console.log(this.info);
    }
  }
}
</script>

<style scoped>
#addPlane{
  width: auto;
  height: 498px;
  overflow: auto;
}

</style>
