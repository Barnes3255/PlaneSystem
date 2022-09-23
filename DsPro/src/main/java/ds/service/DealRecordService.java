package ds.service;

import ds.pojo.DealRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DealRecordService {
    List<DealRecord> getDealRecordList(Map map);
    int addDealRecord(DealRecord dealRecord);
}
