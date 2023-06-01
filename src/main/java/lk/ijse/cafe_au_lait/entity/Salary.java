package lk.ijse.cafe_au_lait.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Salary {
    private String empId;
    private String salaryId;
    private String salaryPaymentMethod;
    private Double salaryPayment;
    private Double salaryOt;
}
