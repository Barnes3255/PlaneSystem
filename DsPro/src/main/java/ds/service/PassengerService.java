package ds.service;

import ds.pojo.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PassengerService {
    List<Passenger> getPassengerList(Map map);
    List<Passenger> getPassengerList2(Map map);
    int addPassenger(Passenger passenger);
    int addPassengerUser(Passenger passenger);
    int deletePassenger(String id_number);
    int deleteUserPassenger(Map map);
}
