package ds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import ds.pojo.Deal;
import ds.pojo.DealRecord;
import ds.pojo.Plane;
import ds.pojo.Ticket;
import ds.service.DealRecordService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@CrossOrigin
public class DealRecordController {
    @Autowired
    @Qualifier("dealRecordServiceImpl")
    private DealRecordService dealRecordService;

    @ResponseBody
    @RequestMapping(value = "/addDealRecord",method = RequestMethod.POST)
    public String addDealRecord(HttpServletRequest req) throws IOException {

        //获得对象
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String s = reader.readLine();
        String str=s.substring(8,s.length()-1);
        System.out.println(str);
        DealRecord dealRecord = JSON.parseObject(str, DealRecord.class, Feature.InitStringFieldAsEmpty);
        //解码
        try {
            dealRecord.setDescription(java.net.URLDecoder.decode(dealRecord.getDescription(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        dealRecordService.addDealRecord(dealRecord);

        return "true";
    }

    @ResponseBody
    @RequestMapping(value = "/cancelRecord",method = RequestMethod.POST)
    public String cancelRecord(HttpServletRequest req) throws IOException {


        Plane plane=(Plane) req.getAttribute("planes");

        Date d=new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sdf.format(d);
        for(Ticket ticket:plane.getTickets()){
            for (Deal deal:ticket.getDeals()){
                DealRecord dealRecord=new DealRecord();
                dealRecord.setId(deal.getId());
                dealRecord.setTime(time);
                dealRecord.setAmount("+".concat(String.valueOf(plane.getPrice()*ticket.getCoun()/100+190)));
                dealRecord.setDescription("航班取消");
                dealRecordService.addDealRecord(dealRecord);
            }
        }

        return "true";
    }

    @ResponseBody
    @RequestMapping(value = "/getRecord",method = RequestMethod.POST)
    public String getRecord(HttpServletRequest req) throws IOException {

        //获得对象
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String s = reader.readLine();
        String str=s.substring(8,s.length()-1);

        JSONObject oo= JSON.parseObject(str);
        String j_id=JSON.toJSONString(oo.get("id"));
        Integer id= JSON.parseObject(j_id,Integer.class,Feature.InitStringFieldAsEmpty);

        HashMap<String,Object> map=new HashMap<>();
        map.put("id",id);
        List<DealRecord> dealRecords=dealRecordService.getDealRecordList(map);

        return JSON.toJSONString(dealRecords);
    }



}
