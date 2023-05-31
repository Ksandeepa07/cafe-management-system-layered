package lk.ijse.cafe_au_lait.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartTM {
    private String itemId;
    private String itemName;
    private String category;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private String Delivery;
    private Button remove;
}
