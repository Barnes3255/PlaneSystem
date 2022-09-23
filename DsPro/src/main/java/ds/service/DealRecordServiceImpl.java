package ds.service;

import ds.mapper.DealRecordMapper;
import ds.pojo.DealRecord;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Setter
public class DealRecordServiceImpl implements DealRecordService{

    DealRecordMapper dealRecordMapper;
    @Override
    public List<DealRecord> getDealRecordList(Map map) {
        return dealRecordMapper.getDealRecordList(map);
    }

    @Override
    public int addDealRecord(DealRecord dealRecord) {
        return dealRecordMapper.addDealRecord(dealRecord);
    }
}
