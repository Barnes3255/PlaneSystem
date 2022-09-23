package ds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import ds.pojo.City;
import ds.pojo.Plane;
import ds.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class CityController {
    @Autowired
    @Qualifier("cityServiceImpl")
    CityService cityService;

    //获得中转城市名称
    @RequestMapping(value = "/transit",method = RequestMethod.POST)
    public String transit(HttpServletRequest req) throws IOException {

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

        System.out.println(f_plane.getStart_city()+" "+f_plane.getEnd_city()+" "+f_plane.getStart_day());

        //获得中转城市名称
        Map<String,Object> map=new HashMap<>();
        String transitCity=cityService.getTransitCity(map,f_plane.getStart_city(),f_plane.getEnd_city());
        req.setAttribute("transit_city",transitCity);
        req.setAttribute("start_city",f_plane.getStart_city());
        req.setAttribute("end_city",f_plane.getEnd_city());
        req.setAttribute("start_day",f_plane.getStart_day());

        return "forward:/searchTransit";
    }
    //获得城市时差
    @ResponseBody
    @RequestMapping(value = "/change_time",method = RequestMethod.POST)
    public String change_time(HttpServletRequest req) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String s = reader.readLine();
        String str=s.substring(8,s.length()-1);

        JSONObject oo= JSON.parseObject(str);
        String city=JSON.toJSONString(oo.get("city"));

        try {
            city=java.net.URLDecoder.decode(city, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        city=JSON.parseObject(city,String.class, Feature.InitStringFieldAsEmpty);

        HashMap<String,Object> map =new HashMap<>();
        map.put("city_name",city);
        List<City> c=cityService.getCityList(map);

        return JSON.toJSONString(c.get(0).getTime_adapter());
        }
    }
