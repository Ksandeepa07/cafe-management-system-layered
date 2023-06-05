package lk.ijse.cafe_au_lait.view.tdm;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CheckOrdersTM {
    private String orderId;
    private String custId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private Double orderPayment;
    private String delivery;

}
