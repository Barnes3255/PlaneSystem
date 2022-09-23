package ds.mapper;

import ds.pojo.Passenger;

import java.util.List;
import java.util.Map;

public interface PassengerMapper {

    //获得乘客信息
    List<Passenger> getPassengerList(Map map);
    //通过用户id获得所存乘客信息
    List<Passenger> getPassengerList2(Map map);
    int addPassenger(Passenger passenger);
    int addPassengerUser(Passenger passenger);
    int deletePassenger(String id_number);
    int deleteUserPassenger(Map map);
}
