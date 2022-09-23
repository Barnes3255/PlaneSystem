package ds.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Passenger {
    private String id_number;
    private String name;
    private String phone_number;
    //绑定用户id
    private int id;
}
