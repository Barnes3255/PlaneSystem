<template>
  <div>
    <AnyTransitTicketIn   v-for="ticket2 in tickets2"   :ticket1="ticket1" :ticket2="ticket2" :price1="price1" :price2="price2" :key="ticket2.coun">    </AnyTransitTicketIn>
  </div>
</template>

<script>
import AnyTransitTicketIn from "./AnyTransitTicketIn";

export default {
  name: "AnyTransitTicket",
  props:['ticket1','price1','price2','tickets2'],
  components:{AnyTransitTicketIn},
  data(){
    return{
    }
  }
}
</script>

<style scoped>

</style>
