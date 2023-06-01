package lk.ijse.cafe_au_lait.entity;

import lombok.*;

import java.io.FileInputStream;
import java.io.InputStream;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventImage {
    private String eventId;
    private InputStream eventImage;
}
