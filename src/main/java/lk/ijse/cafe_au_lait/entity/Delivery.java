package lk.ijse.cafe_au_lait.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Delivery {
    private String deliverId;
    private String deliveryLocation;
    private String orderId;
    private String empId;
}
