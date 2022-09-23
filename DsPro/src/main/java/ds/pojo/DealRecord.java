package ds.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DealRecord {
    private int record_id;
    private int id;
    private String amount;
    private String description;
    private String time;
}
