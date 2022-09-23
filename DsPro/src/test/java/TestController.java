import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ds.pojo.User;
import ds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class TestController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;
    @RequestMapping("/tt")
    public String list(Model m){
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> l=userService.getUserList(map);
        m.addAttribute("list",l);
        return "kk";
    }
    @RequestMapping("/user")
    public void uu(HttpServletResponse resp) throws IOException {
        PrintWriter writer=resp.getWriter();
        writer.write("true");
    }
//    @RequestMapping("/aa")
//    @ResponseBody
//    public String dv() throws JsonProcessingException {
//        ObjectMapper mapper=new ObjectMapper();
//        User u=new User(5156,"诺夫阿布贾","法尔v");
//        String uu=mapper.writeValueAsString(u);
//        return uu;
//    }//解决乱码问题
}
