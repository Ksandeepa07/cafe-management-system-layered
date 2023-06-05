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

    public OrdersDTO(String orderId, String custId, LocalDate orderDate, LocalTime orderTime, Double orderPayment, String delivery) {
        this.orderId = orderId;
        this.custId = custId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderPayment = orderPayment;
        this.delivery = delivery;
    }
}
