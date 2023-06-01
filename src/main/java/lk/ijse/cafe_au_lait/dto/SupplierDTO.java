package lk.ijse.cafe_au_lait.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SupplierDTO {
    private String id;
    private String name;
    private String contact;
    private String address;
    private String email;
}
