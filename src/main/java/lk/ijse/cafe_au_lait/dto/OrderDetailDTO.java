package lk.ijse.cafe_au_lait.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailDTO {
    private String OrderId;
    private String itemId;
    private Integer orderQuantity;

    public OrderDetailDTO(String itemId, Integer orderQuantity) {
        this.itemId = itemId;
        this.orderQuantity = orderQuantity;
    }
}
