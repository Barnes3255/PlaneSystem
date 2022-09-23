package ds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import ds.pojo.Deal;
import ds.pojo.Passenger;
import ds.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PassengerController {
    @Autowired
    @Qualifier("passengerServiceImpl")
    PassengerService passengerService;

    //通过id获得该用户保存的所有乘客信息
    @RequestMapping(value = "/getPassengers",method = RequestMethod.POST)
    public String getPassenger(HttpServletRequest req) throws IOException {

        //获得对象
        String s = new BufferedReader(new InputStreamReader(req.getInputStream())).readLine();
        String str=s.substring(8,s.length()-1);
        Passenger passenger= JSON.parseObject(str, Passenger.class, Feature.InitStringFieldAsEmpty);

        //根据userID获得其绑定的所有乘客身份证号码
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id",passenger.getId());
        List<Passenger> passengerList =passengerService.getPassengerList2(map);

        //遍历所有乘客身份证号码，获得乘客信息
        List<Passenger> passengerListAll=new ArrayList<>();
        int index=0;
        for (Passenger pass:passengerList) {
            index++;
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id_number",pass.getId_number());
            System.out.println(pass.getId_number());
            List<Passenger> passengerList1 =passengerService.getPassengerList(map1);
            if (!passengerList1.isEmpty()){passengerList1.get(0).setId(index);}
            passengerListAll.addAll(passengerList1);
            System.out.println("passengerList1.size() = " + passengerList1.size());
        }
        return JSON.toJSONString(passengerListAll);
    }


    //添加一个乘客信息
    @RequestMapping(value = "/addPassenger",method = RequestMethod.POST)
    public String addPassenger(HttpServletRequest req) throws IOException {

        //获得对象
        String s = new BufferedReader(new InputStreamReader(req.getInputStream())).readLine();
        String str=s.substring(8,s.length()-1);
        Passenger passenger= JSON.parseObject(str, Passenger.class, Feature.InitStringFieldAsEmpty);

        //解码
        try {
            passenger.setName(java.net.URLDecoder.decode(passenger.getName(), "UTF-8"));
            passenger.setId_number(java.net.URLDecoder.decode(passenger.getId_number(), "UTF-8"));
            passenger.setPhone_number(java.net.URLDecoder.decode(passenger.getPhone_number(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //添加一个乘客信息
        passengerService.addPassenger(passenger);
        return "true";
    }

    //删除一个乘客信息
    @RequestMapping(value = "/deletePassenger",method = RequestMethod.POST)
    public String deletePassenger(HttpServletRequest req) throws IOException {

        //获得对象
        String s = new BufferedReader(new InputStreamReader(req.getInputStream())).readLine();
        String str=s.substring(8,s.length()-1);
        Passenger passenger= JSON.parseObject(str, Passenger.class, Feature.InitStringFieldAsEmpty);

        //解码
        try {
            passenger.setName(java.net.URLDecoder.decode(passenger.getName(), "UTF-8"));
            passenger.setId_number(java.net.URLDecoder.decode(passenger.getId_number(), "UTF-8"));
            passenger.setPhone_number(java.net.URLDecoder.decode(passenger.getPhone_number(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //删除一个乘客信息
        Map<String,Object> map=new HashMap();
        map.put("id_number",passenger.getId_number());
        map.put("id",passenger.getId());
        passengerService.deleteUserPassenger(map);
        return "true";
    }
}
