package se.knowit.iz.carbonrebound.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmissionPrivateVehicleDTO {
    String registrationNumber;
    String dateOfTrip;
    String placeIdFrom;
    String placeIdTo;
    String distanceFrom;
    String distanceTo;
}
