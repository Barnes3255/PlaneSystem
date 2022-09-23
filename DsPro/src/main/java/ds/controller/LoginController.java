package ds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import ds.pojo.User;
import ds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest req) throws IOException {

        //获得对象
        String s = new BufferedReader(new InputStreamReader(req.getInputStream())).readLine();
        String str=s.substring(8,s.length()-1);
        User f_user= JSON.parseObject(str,User.class,Feature.InitStringFieldAsEmpty);

        //解码
        try {
            f_user.setName(java.net.URLDecoder.decode(f_user.getName(), "UTF-8"));
            f_user.setPwd(java.net.URLDecoder.decode(f_user.getPwd(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name",f_user.getName());
        map.put("pwd",f_user.getPwd());
        List<User> user = userService.getUserList(map);

        if (user.isEmpty()){
            return "false";
        }
        else{
            HttpSession session=req.getSession();
            for (User u:user){
                System.out.println(u.getName());
                session.setAttribute("userId",u.getId());
            }
            return JSON.toJSONString(user.get(0));
        }
    }
}
