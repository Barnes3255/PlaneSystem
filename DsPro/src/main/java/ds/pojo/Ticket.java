package ds.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Ticket {

    private int ticket_id;
    private int plane_id;
    private int coun;
    private int number_all;
    private int number_rest;
    private String level;
    private Plane plane;
    private List<Deal> deals;
}
