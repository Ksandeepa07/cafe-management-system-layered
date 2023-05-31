package lk.ijse.cafe_au_lait.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SupplyLoadTM {
    private String itemId;
    private String itemName;
    private String category;
    private Integer quantity;
    private Button remove;
}
