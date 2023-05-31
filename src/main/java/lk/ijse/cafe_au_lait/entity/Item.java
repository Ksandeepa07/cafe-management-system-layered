package lk.ijse.cafe_au_lait.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
    private String category;
}
