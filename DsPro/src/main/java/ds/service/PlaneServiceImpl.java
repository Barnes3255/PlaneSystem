package ds.service;

import ds.mapper.PlaneMapper;
import ds.pojo.Notice;
import ds.pojo.Plane;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Setter
public class PlaneServiceImpl implements PlaneService {

    PlaneMapper planeMapper;
    @Override
    public List<Plane> getPlaneList(Map map) {
        return planeMapper.getPlaneList(map);
    }

    @Override
    public List<Plane> getPlaneListWithLowestPrice(Map map) {
        return planeMapper.getPlaneListWithLowestPrice(map);
    }

    @Override
    public int addPlane(Plane p) {
        return planeMapper.addPlane(p);
    }

    @Override
    public int updatePlane(Map map) {
        return planeMapper.updatePlane(map);
    }

    @Override
    public int deletePlane(int plane_id) {
        return planeMapper.deletePlane(plane_id);
    }

    @Override
    public Plane getCancelPlane(int plane_id) {
        return planeMapper.getCancelPlane(plane_id);
    }

    @Override
    public int addCancelNotice(int id, int plane_id, String notice_time) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("plane_id",plane_id);
        map.put("notice_time",notice_time);
        return planeMapper.addCancelNotice(map);
    }

    @Override
    public List<Notice> getNoticeList(int id) {
        return planeMapper.getNoticeList(id);
    }
}
