package ds.mapper;
import ds.pojo.Ticket;
import java.util.List;
import java.util.Map;

public interface TicketMapper {

    //通过航班号（n）或票号（1）获得飞机票对象
    List<Ticket> getTicketList(Map map);
    int updateTicketMinus1 (Map map);
    int updateTicketAdd1 (Map map);
    int addTicket(Ticket t);
    int deleteTicket(int plane_id);
}
