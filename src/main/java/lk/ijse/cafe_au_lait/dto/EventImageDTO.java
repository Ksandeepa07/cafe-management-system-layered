package lk.ijse.cafe_au_lait.dto;

import lombok.*;

import java.io.FileInputStream;
import java.io.InputStream;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventImageDTO {
    private String eventId;
    private InputStream eventImage;
}
