package lk.ijse.cafe_au_lait.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SupplyLoadDTO {
   private String supplieId;
   private String supplierLoadId;
   private Double payment;
   List<SupplierLoadDetailDTO> supplierLoadDetailDTOS;


}
