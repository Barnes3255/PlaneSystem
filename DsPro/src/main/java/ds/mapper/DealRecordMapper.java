package ds.mapper;

import ds.pojo.DealRecord;

import java.util.List;
import java.util.Map;

public interface DealRecordMapper {
    List<DealRecord> getDealRecordList(Map map);
    int addDealRecord(DealRecord dealRecord);
}
