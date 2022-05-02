package se.knowit.iz.carbonrebound.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDTO {
    String firstName;
    String lastName;
    String email;
    String userName;
}
