import Vue from 'vue';
import Router from 'vue-router'
import Login from '../view/Login'
import Search from '../view/Search'
import Plane from '../view/Plane'
import Ticket from '../view/Ticket'
import TransitTicket from "../view/TransitTicket";
import Personal from "../view/Personal";
import DealPaid from "../view/DealPaid";
import DealUnpaid from "../view/DealUnpaid";
import PassengerInfo from "../view/PassengerInfo";
import PersonalNotice from "../view/PersonalNotice";
import Pay from "../view/Pay";
import TransitPay from "../view/TransitPay";
import Refresh from "../view/Refresh";
import Root from "../view/Root";
import AddPlane from "../view/AddPlane";
import DeletePlane from "../view/DeletePlane";
import DeleteAnyPlane from "../view/DeleteAnyPlane";
import ChangePlane from "../view/ChangePlane";
import ChangeAnyPlane from "../view/ChangeAnyPlane";
import ChangeAnyPlaneIn from "../view/ChangeAnyPlaneIn";
import ChangeTicket from "../view/ChangeTicket";
import ChangeAnyTicket from "../view/ChangeAnyTicket";
import DealSearch from "../view/DealSearch";
import Record from "../view/Record";
Vue.use(Router);

export default new Router({
  routes:[{
    path:'/login',
    component:Login,
    name:Login
  },{
    path:'/search',
    component:Search,
    name:Search
  },{
    path:'/plane',
    component:Plane,
    name:'Plane'
  },{
    path:'/ticket',
    component:Ticket,
    name:'Ticket'
  },{
    path:'/transitTicket',
    component:TransitTicket,
    name:'TransitTicket'
  },

    {
    path:'/personal',
    component:Personal,
    name:'Personal',
    children:[{
      path:'/dealPaid',
      component:DealPaid,
      name:'DealPaid',
    },{
      path:'/dealUnpaid',
      component:DealUnpaid,
      name:'DealUnpaid',
    },{
      path:'/passengerInfo',
      component:PassengerInfo,
      name:'PassengerInfo',
    },{
      path:'/personalNotice',
      component:PersonalNotice,
      name:'PersonalNotice',
    },{
      path:'/record',
      component:Record,
      name:'Record',
    },{
      path:'/refresh',
      component:Refresh,
      name:'Refresh',
    },{
      path:'/changePlane',
      component:ChangePlane,
      name:'ChangePlane',
    },{
      path:'/changeAnyPlane',
      component:ChangeAnyPlane,
      name:'ChangeAnyPlane',
    },{
      path:'/changeAnyPlaneIn',
      component:ChangeAnyPlaneIn,
      name:'ChangeAnyPlaneIn',
    },{
      path:'/changeTicket',
      component:ChangeTicket,
      name:'ChangeTicket',
    },{
      path:'/changeAnyTicket',
      component:ChangeAnyTicket,
      name:'ChangeAnyTicket',
    }
    ]
  }

  ,{
    path:'/pay',
    component:Pay,
    name:'Pay',
  },
    {
      path:'/transitPay',
      component:TransitPay,
      name:'TransitPay',
    },


    {
      path:'/root',
      component:Root,
      name:'Root',
      children:[
          {
           path:'/deletePlane',
           component:DeletePlane,
            name:'DeletePlane',
        },{
          path:'/addPlane',
          component:AddPlane,
          name:'AddPlane',
        },{
          path:'/dealSearch',
          component:DealSearch,
          name:'DealSearch',
        },{
          path:'/refresh',
          component:Refresh,
        },{
          path:'/deleteAnyPlane',
          component:DeleteAnyPlane,
          name:'DeleteAnyPlane',
        }
      ]
    },

  ]
})
