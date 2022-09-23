import com.alibaba.fastjson.JSON;
import ds.mapper.UserMapper;
import ds.pojo.Deal;
import ds.pojo.Passenger;
import ds.pojo.Plane;
import ds.pojo.User;
import ds.service.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;


public class MyTest {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService mapper = (UserService) context.getBean("userServiceImpl");
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<User> user = mapper.getUserList(map);
        for (User u : user) {
            System.out.println(u);
        }
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper mapper = (UserMapper) context.getBean("userMapperImpl");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name","cili");
        List<User> user = mapper.getUserList(map);
        for (User u : user) {
            System.out.println(u);
        }
    }

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PlaneService mapper = (PlaneService) context.getBean("planeServiceImpl");
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Plane> user = mapper.getPlaneList(map);
        for (Plane u : user) {
            System.out.println(u);
        }
    }

    @Test
    public void test4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CityService mapper = (CityService) context.getBean("cityServiceImpl");
        HashMap<String, Object> map = new HashMap<String, Object>();
        String s=mapper.getTransitCity(map,"北京","华盛顿");
        System.out.println(s);
    }

    @Test
    public void test5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DealService mapper = (DealService) context.getBean("dealServiceImpl");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id",90000001);
        map.put("pay","YES");
        List<Deal> user = mapper.getDealListPlus(map);
        for (Deal u : user) {
            System.out.println(JSON.toJSONString(u));
        }
    }

    @Test
    public void test6(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PassengerService mapper = (PassengerService)  context.getBean("passengerServiceImpl");
        Passenger passenger=new Passenger("152635201405185268", "王咚咚", "12345678910",90000005);
        mapper.addPassenger(passenger);

    }
}
