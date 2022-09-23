package ds.mapper;
import ds.pojo.Notice;
import ds.pojo.Plane;
import java.util.List;
import java.util.Map;

public interface PlaneMapper {

    List<Plane> getPlaneList(Map map);
    List<Plane> getPlaneListWithLowestPrice(Map map);
    int addPlane(Plane p);
    int updatePlane(Map map);
    int deletePlane(int plane_id);
    Plane getCancelPlane(int plane_id);
    int addCancelNotice(Map map);
    List<Notice> getNoticeList(int id);
}
