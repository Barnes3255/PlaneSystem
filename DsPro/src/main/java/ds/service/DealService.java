package ds.service;

import ds.pojo.Deal;
import ds.pojo.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DealService {
    List<Deal> getDealList(Map map);
    int addDeal(Deal deal);
    int addDealTicket(Deal deal);
    List<Deal> getDealListPlus(Map map);
    int updateDeal(Map map);
    int deleteDealTicket(Map map);

}
