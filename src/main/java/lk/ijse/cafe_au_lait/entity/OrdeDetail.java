package lk.ijse.cafe_au_lait.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrdeDetail {
    private String OrderId;
    private String itemId;
    private Integer orderQuantity;
}
