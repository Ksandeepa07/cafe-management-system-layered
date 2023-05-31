package lk.ijse.cafe_au_lait.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Salary {
    private String empId;
    private String salaryId;
    private String paymentMethod;
    private Double payment;
    private Double overTime;
}
