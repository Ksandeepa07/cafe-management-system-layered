package lk.ijse.cafe_au_lait.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplierLoadDetail {
    private String supplierLoadId;
    private String itemId;
    private String supplierId;
    private Integer supplierQty;
    private LocalTime supplierLoadTime;
    private LocalDate supplierLoadDate;
    private Double supplierLoadPrice;
}
