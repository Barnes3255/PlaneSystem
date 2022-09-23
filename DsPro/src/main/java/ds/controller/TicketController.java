package ds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import ds.pojo.Plane;
import ds.pojo.Ticket;
import ds.service.TicketService;
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
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class TicketController {

    @Autowired
    @Qualifier("ticketServiceImpl")
    TicketService ticketService;

    ////根据航班id获得该航班所有票的信息
    @RequestMapping(value = "/ticket",method = RequestMethod.POST)
    public String ticket(HttpServletRequest req) throws IOException {

        //获得对象
        String s = new BufferedReader(new InputStreamReader(req.getInputStream())).readLine();
        String str=s.substring(8,s.length()-1);
        Ticket f_ticket= JSON.parseObject(str, Ticket.class, Feature.InitStringFieldAsEmpty);

        //根据航班id获得该航班所有票的信息
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("plane_id",f_ticket.getPlane_id());
        List<Ticket> tickets = ticketService.getTicketList(map);
        return JSON.toJSONString(tickets);

    }

    //余票数减一
    @RequestMapping(value = "/numberRestMinus1",method = RequestMethod.POST)
    public String numberRestMinus1(HttpServletRequest req) throws IOException {

        int ticket_id=Integer.parseInt( String.valueOf(req.getAttribute("ticket_id")));
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ticket_id",ticket_id);
        ticketService.updateTicketMinus1(map);
        return "true";

    }
    //余票数加一
    @RequestMapping(value = "/numberRestAdd1",method = RequestMethod.POST)
    public String numberRestAdd1(HttpServletRequest req) throws IOException {

        int ticket_id=Integer.parseInt( String.valueOf(req.getAttribute("ticket_id")));
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ticket_id",ticket_id);
        ticketService.updateTicketAdd1(map);
        return "true";

    }

    //中转票的第一程和第二程余票数都减一
    @RequestMapping(value = "/numberRestMinus1_2",method = RequestMethod.POST)
    public String numberRestMinus1_2(HttpServletRequest req) throws IOException {

        int ticket_id1=(int)req.getAttribute("ticket_id1");
        int ticket_id2=(int)req.getAttribute("ticket_id2");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ticket_id",ticket_id1);
        ticketService.updateTicketMinus1(map);
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("ticket_id",ticket_id2);
        ticketService.updateTicketMinus1(map1);
        return "true";

    }

    @RequestMapping(value = "/addTickets",method = RequestMethod.POST)
    public String addTickets(HttpServletRequest req) throws IOException {
        ticketService.addTickets((List<Ticket>) req.getAttribute("tickets"));
        return "true";

    }

    //原票余票数减一，改签后的票的余票数加一
    @RequestMapping(value = "/ticketChange",method = RequestMethod.POST)
    public String ticketChange(HttpServletRequest req) throws IOException {
        int ticket_id1=(int) req.getAttribute("old_ticket_id");
        int ticket_id2=(int)req.getAttribute("new_ticket_id");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ticket_id",ticket_id1);
        ticketService.updateTicketAdd1(map);
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("ticket_id",ticket_id2);
        ticketService.updateTicketMinus1(map1);

        return "true";

    }

    @RequestMapping(value = "/refund",method = RequestMethod.POST)
    public String refund(HttpServletRequest req) throws IOException{
        int ticket_id=(int) req.getAttribute("ticket_id");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ticket_id",ticket_id);
        ticketService.updateTicketAdd1(map);
        return "true";
    }
}
