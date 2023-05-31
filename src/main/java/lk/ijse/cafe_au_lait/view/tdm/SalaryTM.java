package lk.ijse.cafe_au_lait.view.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SalaryTM {
    private String empId;
    private String salaryId;
    private String paymentMethod;
    private Double payment;
    private Double overTime;
}
