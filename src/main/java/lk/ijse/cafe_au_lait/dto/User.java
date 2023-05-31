package lk.ijse.cafe_au_lait.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String username;
    private String password;
    private String emial;
    private String jobTitle;
}
