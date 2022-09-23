package ds.service;

import ds.pojo.City;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CityService {
    List<City> getCityList(Map map);
    public String getTransitCity(Map map,String start_city,String end_city);

}
