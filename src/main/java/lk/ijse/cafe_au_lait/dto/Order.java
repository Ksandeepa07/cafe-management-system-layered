package lk.ijse.cafe_au_lait.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private String itemId;
    private Integer qty;
    private String delivery;
}
