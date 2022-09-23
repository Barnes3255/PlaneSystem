package ds.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City {
    private int city_id;
    private String city_name;
    private int x;
    private int y;
    //标识国家
    private String location;
    private String time_adapter;
}
