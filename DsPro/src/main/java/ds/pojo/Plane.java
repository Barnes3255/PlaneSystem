package ds.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Plane {
    private int plane_id;
    private String start_city;
    private String end_city;
    private String start_time;
    private String end_time;
    private String start_day;
    private String end_day;
    private int price;
    //最低价，通过计算获得
    private int lowest_price;
    private String departure_airfield;
    private String arrival_airfield;
    private String exist;
    private List<Ticket> tickets;
}
