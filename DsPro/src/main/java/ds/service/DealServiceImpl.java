package ds.service;

import ds.mapper.DealMapper;
import ds.pojo.Deal;
import ds.pojo.Ticket;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
@Setter
public class DealServiceImpl implements DealService {

    DealMapper dealMapper;
    @Override
    public List<Deal> getDealList(Map map) {
        return dealMapper.getDealList(map);
    }

    @Override
    public int addDeal(Deal deal) {

        int deal_id=(int)(Math.random()*10000000);
        Map<String,Object> map=new HashMap<>();
        map.put("deal_id",deal_id);
        while (!dealMapper.getDealList(map).isEmpty())
        {
            deal_id=(int)(Math.random()*10000000);
            map=new HashMap<>();
            map.put("deal_id",deal_id);
        }
        deal.setDeal_id(deal_id);
        dealMapper.addDeal(deal);
        return deal_id;
    }

    @Override
    public int addDealTicket(Deal deal) {
        return dealMapper.addDealTicket(deal);
    }

    @Override
    public List<Deal> getDealListPlus(Map map) {
        return dealMapper.getDealListPlus(map);
    }

    @Override
    public int updateDeal(Map map) {
        return dealMapper.updateDeal(map);
    }

    @Override
    public int deleteDealTicket(Map map) {
        return dealMapper.deleteDealTicket(map);
    }


}
