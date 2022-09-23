package ds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ds.mapper.CityMapper;
import ds.pojo.*;
import ds.service.PlaneService;
import ds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin
public class PlaneController {
    @Autowired
    @Qualifier("planeServiceImpl")
    private PlaneService planeService;

    //根据plane_id获得航班
    @ResponseBody
    @RequestMapping(value = "/search1",method = RequestMethod.POST)
    public String search(HttpServletRequest req) throws IOException {

        //获得对象
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String s = reader.readLine();
        String str=s.substring(8,s.length()-1);
        Plane f_plane= JSON.parseObject(str,Plane.class, Feature.InitStringFieldAsEmpty);

        //根据plane_id获得航班,plane_id为主键，所以集合中只有一个元素
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("plane_id",f_plane.getPlane_id());
        map.put("exist","YES");
        List<Plane> planes = planeService.getPlaneList(map);

        return JSON.toJSONString(planes.get(0));
    }


    //获得带有航班最低价的航班对象
    @ResponseBody
    @RequestMapping(value = "/search2",method = RequestMethod.POST)
    public String lowestPrice(HttpServletRequest req) throws IOException {

        //获得对象
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String s = reader.readLine();
        String str=s.substring(8,s.length()-1);
        System.out.println(str);
        Plane f_plane= JSON.parseObject(str,Plane.class, Feature.InitStringFieldAsEmpty);

        //解码
        try {
            f_plane.setStart_city(java.net.URLDecoder.decode(f_plane.getStart_city(), "UTF-8"));
            f_plane.setEnd_city(java.net.URLDecoder.decode(f_plane.getEnd_city(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //获得带有航班最低价的航班对象
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("start_city",f_plane.getStart_city());
        map.put("end_city",f_plane.getEnd_city());
        map.put("start_day",f_plane.getStart_day());
        List<Plane> planes = planeService.getPlaneListWithLowestPrice(map);

        return JSON.toJSONString(planes);
    }

    //由CityController的/transit转发而来
    @ResponseBody
    @RequestMapping(value = "/searchTransit",method = RequestMethod.POST)
    public String transit(HttpServletRequest req) throws IOException, ParseException {

        System.out.println(req.getAttribute("transit_city"));

        //第一程
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("start_city",req.getAttribute("start_city"));
        map1.put("end_city",req.getAttribute("transit_city"));
        map1.put("start_day",req.getAttribute("start_day"));
        List<Plane> planes1 = planeService.getPlaneListWithLowestPrice(map1);


        //第二程
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("start_city",req.getAttribute("transit_city"));
        map2.put("end_city",req.getAttribute("end_city"));
        map2.put("start_day",req.getAttribute("start_day"));
        List<Plane> planes2 = planeService.getPlaneListWithLowestPrice(map2);

        //获得第二天
        String day=(String) req.getAttribute("start_day");
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date =sdf.parse(day);
        date.setTime(date.getTime()+86400000);
        String next_day=sdf.format(date);

        //跨日第二程
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("start_city",req.getAttribute("transit_city"));
        map3.put("end_city",req.getAttribute("end_city"));
        map3.put("start_day",next_day);
        List<Plane> planes3 = planeService.getPlaneListWithLowestPrice(map3);
        planes2.addAll(planes3);


        //找到合适的中转航班，中转时间大于1h,小于12h,且在同一个机场
        List<Planes2> mm=new ArrayList<>();
        SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < planes1.size(); i++) {
            for (int j = 0; j < planes2.size(); j++) {
                if (!planes1.get(i).getArrival_airfield().equals(planes2.get(j).getDeparture_airfield())){
                    continue;
                }
                System.out.println(1);
                String day1=planes1.get(i).getStart_day()+" "+planes1.get(i).getEnd_time();
                String day2=planes2.get(j).getStart_day()+" "+planes2.get(j).getStart_time();
                System.out.println(day1);
                System.out.println(day2);
                long ms=sdf1.parse(day2).getTime()-sdf1.parse(day1).getTime();
                System.out.println(ms);
                if(ms>=3600000&&ms<=43200000){
                    Planes2 pp=new Planes2(planes1.get(i),planes2.get(j));
                    mm.add(pp);
                    break;
                }
            }
        }


        return JSON.toJSONString(mm, SerializerFeature.DisableCircularReferenceDetect);
    }

    //搜索获得航班
    @ResponseBody
    @RequestMapping(value = "/search4",method = RequestMethod.POST)
    public String search4(HttpServletRequest req) throws IOException {

        //获得对象
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String s = reader.readLine();
        String str=s.substring(8,s.length()-1);
        Plane f_plane= JSON.parseObject(str,Plane.class, Feature.InitStringFieldAsEmpty);

        //解码
        try {
            f_plane.setStart_city(java.net.URLDecoder.decode(f_plane.getStart_city(), "UTF-8"));
            f_plane.setEnd_city(java.net.URLDecoder.decode(f_plane.getEnd_city(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("start_city",f_plane.getStart_city());
        map.put("end_city",f_plane.getEnd_city());
        map.put("start_day",f_plane.getStart_day());
        map.put("exist","YES");
        List<Plane> planes = planeService.getPlaneList(map);

        return JSON.toJSONString(planes);
    }

    @RequestMapping(value = "/addPlane",method = RequestMethod.POST)
    public String addPlane(HttpServletRequest req) throws IOException{

        //获得对象
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String s = reader.readLine();
        String str=s.substring(8,s.length()-1);
        System.out.println(str);

        //解码
        try {
            str=java.net.URLDecoder.decode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Plane f_plane= JSON.parseObject(str,Plane.class,Feature.InitStringFieldAsEmpty);

        JSONObject oo= JSON.parseObject(str);
        String list=JSON.toJSONString(oo.get("tickets"));
        System.out.println(list);
        List<Ticket> tickets=JSON.parseArray(list,Ticket.class);
        f_plane.setTickets(tickets);
        System.out.println(JSON.toJSONString(f_plane));
        planeService.addPlane(f_plane);


        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("start_city",f_plane.getStart_city());
        map.put("end_city",f_plane.getEnd_city());
        map.put("start_day",f_plane.getStart_day());
        map.put("end_day",f_plane.getEnd_day());
        map.put("start_time",f_plane.getStart_time());
        map.put("end_time",f_plane.getEnd_time());
        map.put("departure_airfield",f_plane.getDeparture_airfield());
        map.put("arrival_airfield",f_plane.getArrival_airfield());
        map.put("price",f_plane.getPrice());


        List<Plane> planes = planeService.getPlaneList(map);
        System.out.println(f_plane.getTickets().toString());


        if (!planes.isEmpty()){
            System.out.println(f_plane);
            int plane_id=planes.get(0).getPlane_id();
            for(Ticket ticket:tickets){
                ticket.setNumber_rest(ticket.getNumber_all());
                ticket.setPlane_id(plane_id);
            }
            req.setAttribute("tickets",tickets);
        }

        return "forward:/addTickets";
    }

    //取消航班
    @RequestMapping(value = "/cancelPlane",method = RequestMethod.POST)
    public String cancelPlane(HttpServletRequest req) throws IOException {

        //获得对象
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String s = reader.readLine();
        String str = s.substring(8, s.length() - 1);
        System.out.println(str);

        JSONObject oo= JSON.parseObject(str);
        String j_plane_id=JSON.toJSONString(oo.get("plane_id"));
        Integer plane_id= JSON.parseObject(j_plane_id,Integer.class,Feature.InitStringFieldAsEmpty);
        Plane plane=planeService.getCancelPlane(plane_id);
        HashMap<String,Object> map=new HashMap<>();
        map.put("exist","NO");
        map.put("plane_id",plane_id);
        planeService.updatePlane(map);
        req.setAttribute("planes",plane);

        Date d=new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String notice_time=sdf.format(d);
        for(Ticket ticket:plane.getTickets()){
            for (Deal deal:ticket.getDeals()){
                planeService.addCancelNotice(deal.getId(),plane_id,notice_time);
            }
        }

        return "forward:/cancel";
    }
    @RequestMapping(value = "/cancelPlaneRecord",method = RequestMethod.POST)
    public String cancelPlaneRecord(HttpServletRequest req) throws IOException {

        //获得对象
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String s = reader.readLine();
        String str = s.substring(8, s.length() - 1);
        System.out.println(str);

        JSONObject oo= JSON.parseObject(str);
        String j_plane_id=JSON.toJSONString(oo.get("plane_id"));
        Integer plane_id= JSON.parseObject(j_plane_id,Integer.class,Feature.InitStringFieldAsEmpty);
        Plane plane=planeService.getCancelPlane(plane_id);
        req.setAttribute("planes",plane);

        return "forward:/cancelRecord";
    }

    @ResponseBody
    @RequestMapping(value = "/getNotice",method = RequestMethod.POST)
    public String getPassenger(HttpServletRequest req) throws IOException {

        //获得对象
        String s = new BufferedReader(new InputStreamReader(req.getInputStream())).readLine();
        String str=s.substring(8,s.length()-1);

        JSONObject oo= JSON.parseObject(str);
        String j_id=JSON.toJSONString(oo.get("id"));
        Integer id= JSON.parseObject(j_id,Integer.class,Feature.InitStringFieldAsEmpty);
        List<Notice> notices =planeService.getNoticeList(id);
        return JSON.toJSONString(notices);
    }

}
