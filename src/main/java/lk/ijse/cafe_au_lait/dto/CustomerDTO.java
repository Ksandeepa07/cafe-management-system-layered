package lk.ijse.cafe_au_lait.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {
    private String custId;
    private String custName;
    private String custContact;
    private String custEmail;
}
