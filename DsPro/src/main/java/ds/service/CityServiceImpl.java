package ds.service;

import ds.mapper.CityMapper;
import ds.pojo.City;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CityServiceImpl implements CityService{

    CityMapper cityMapper;
    public void setCityMapper(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    @Override
    public List<City> getCityList(Map map) {
        return cityMapper.getCityList(map);
    }

    @Override
    public String getTransitCity(Map map,String start_city,String end_city){
        List<City> cities= cityMapper.getCityList(map);
        int s=cities.size();
        int[][] distance=new int[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                int xi=cities.get(i).getX();
                int yi=cities.get(i).getY();
                int xj=cities.get(j).getX();
                int yj=cities.get(j).getY();
                distance[i][j]=Math.abs(xj-xi)*Math.abs(xj-xi)+Math.abs(yj-yi)*Math.abs(yj-yi);
            }
        }
        Map<String,Object> startM=new HashMap();
        startM.put("city_name",start_city);
        List<City> cc=cityMapper.getCityList(startM);

        Map<String,Object> endM=new HashMap();
        endM.put("city_name",end_city);
        List<City> ccc=cityMapper.getCityList(endM);
        int end=ccc.get(0).getCity_id();

        if (cc.size()>0&&ccc.size()>0){
            int start=cc.get(0).getCity_id();
            for (int i = 0; i < s; i++) {
                if(cities.get(i).getCity_id()==start){
                    start=i;
                    break;
                }
            }
            for (int i = 0; i < s; i++) {
                if(cities.get(i).getCity_id()==end){
                    end=i;
                    break;
                }
            }
            int minD=1000000;
            int flag=0;
            for (int i = 0; i < s; i++) {
                if(cities.get(i).getLocation().equals("china")&&i!=start&&i!=end){
                    if(distance[start][i]+distance[i][end]<minD){
                        minD=distance[start][i]+distance[i][end];
                        flag=i;
                    }
                }
            }
            return cities.get(flag).getCity_name();
        }
        else return "？";

    }
}
