package ds.service;

import ds.mapper.PassengerMapper;
import ds.pojo.Passenger;
import jdk.jshell.spi.ExecutionControl;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Setter
public class PassengerServiceImpl implements PassengerService {

    PassengerMapper passengerMapper;
    @Override
    public List<Passenger> getPassengerList(Map map) {
        return passengerMapper.getPassengerList(map);
    }

    @Override
    public List<Passenger> getPassengerList2(Map map) {
        return passengerMapper.getPassengerList2(map);
    }

    @Override
    public int addPassenger(Passenger passenger) {
        passengerMapper.addPassengerUser(passenger);
        try{passengerMapper.addPassenger(passenger);}
        catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!Exception:");
            System.out.println("!!!!!!!!!!!!!!!!Message:"+e.getMessage());
            System.out.println("!!!!!!!!!!!!!!!!Cause:"+e.getCause());

        }
        return 1;
    }

    @Override
    public int addPassengerUser(Passenger passenger) {
        return passengerMapper.addPassengerUser(passenger);
    }

    @Override
    public int deletePassenger(String id_number) {
        return passengerMapper.deletePassenger(id_number);
    }

    @Override
    public int deleteUserPassenger(Map map) {
        return passengerMapper.deleteUserPassenger(map);
    }
}
