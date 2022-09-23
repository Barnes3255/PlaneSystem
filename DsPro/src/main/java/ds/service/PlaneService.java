package ds.service;

import ds.pojo.Notice;
import ds.pojo.Plane;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PlaneService {
    List<Plane> getPlaneList(Map map);
    List<Plane> getPlaneListWithLowestPrice(Map map);
    int addPlane(Plane p);
    int updatePlane(Map map);
    int deletePlane(int plane_id);
    Plane getCancelPlane(int plane_id);
    int addCancelNotice(int id,int plane_id,String notice_time);
    List<Notice> getNoticeList(int id);
}
