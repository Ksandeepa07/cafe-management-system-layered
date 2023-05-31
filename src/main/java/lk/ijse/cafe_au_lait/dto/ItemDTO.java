package lk.ijse.cafe_au_lait.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemDTO {
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
    private String category;

}

