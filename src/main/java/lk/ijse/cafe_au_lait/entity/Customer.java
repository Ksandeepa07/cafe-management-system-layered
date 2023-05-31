package lk.ijse.cafe_au_lait.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String custId;
    private String custName;
    private String custContact;
    private String custEmail;
}
