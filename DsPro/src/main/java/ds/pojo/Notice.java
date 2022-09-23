package ds.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Notice {
    private int notice_id;
    private int plane_id;
    private int id;
    private String notice_time;
}
