package ds.service;

import ds.pojo.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TicketService {
    List<Ticket> getTicketList(Map map);
    int updateTicketMinus1 (Map map);
    int updateTicketAdd1 (Map map);
    int addTicket(Ticket t);
    int addTickets(List<Ticket> tickets);
    int deleteTicket(int plane_id);


}
