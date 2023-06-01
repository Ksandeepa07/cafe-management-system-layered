package lk.ijse.cafe_au_lait.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {
    private String id;
    private String name;
    private String contact;
    private String address;
    private String email;
}
