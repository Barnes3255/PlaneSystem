package ds.mapper;

import ds.pojo.Deal;
import java.util.List;
import java.util.Map;

public interface DealMapper {
    List<Deal> getDealList(Map map);
    List<Deal> getDealListPlus(Map map);
    int addDeal(Deal deal);
    int addDealTicket(Deal deal);
    int updateDeal(Map map);
    int deleteDealTicket(Map map);


}
