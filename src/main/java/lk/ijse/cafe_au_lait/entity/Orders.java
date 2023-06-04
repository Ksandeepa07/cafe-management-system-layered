package lk.ijse.cafe_au_lait.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Orders {
    private String orderId;
    private String custId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private Double orderPayment;
    private String delivery;
}
