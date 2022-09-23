package ds.service;

import ds.mapper.TicketMapper;
import ds.pojo.Ticket;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Setter
public class TicketServiceImpl implements TicketService{
    TicketMapper ticketMapper;

    @Override
    public List<Ticket> getTicketList(Map map) {
        List<Ticket> tickets=ticketMapper.getTicketList(map);
        for (int i = 0; i < tickets.size() ; i++) {
            int cc=tickets.get(i).getCoun();
            if (cc<100){
                tickets.get(i).setLevel("经济舱");
            }else if(cc<150){
                tickets.get(i).setLevel("商务舱");
            }else {
                tickets.get(i).setLevel("头等舱");
            }
        }
        return tickets;
    }

    @Override
    public int updateTicketMinus1(Map map) {
        return ticketMapper.updateTicketMinus1(map);
    }

    @Override
    public int updateTicketAdd1(Map map) {
        return ticketMapper.updateTicketAdd1(map);
    }

    @Override
    public int addTicket(Ticket t) {
        return ticketMapper.addTicket(t);
    }

    @Override
    public int addTickets(List<Ticket> tickets) {

        for(Ticket ticket:tickets){
            ticketMapper.addTicket(ticket);
        }
        return 1;
    }

    public int deleteTicket(int plane_id){
        return ticketMapper.deleteTicket(plane_id);
    };

}
