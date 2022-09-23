package ds.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Deal {

    private int deal_id;
    private  int price;
    //’direct‘或’transit‘
    private String attribute;
    //绑定用户id
    private int id;
    //绑定身份证
    private String id_number;
    //是否已退款
    private String pay;
    //订单完成时间
    private String time;
    //绑定订单信息
    private int ticket_id;
    //乘客信息
    private Passenger passenger;
    private List<Ticket> tickets;

}