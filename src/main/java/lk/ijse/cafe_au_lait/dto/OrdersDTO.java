package lk.ijse.cafe_au_lait.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrdersDTO {
    private String orderId;
    private String custId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private Double orderPayment;
    private String delivery;
    List<OrderDetailDTO> orderDetaisList;
}
