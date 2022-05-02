package se.knowit.iz.carbonrebound.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResponseAuthDTO {

    private long userId;
    private String jwt;
}
