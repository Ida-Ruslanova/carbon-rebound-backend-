package se.knowit.iz.carbonrebound.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TripEmissionPrivateVehicleDTO {
    String id;
    String startLoc;
    String endLoc;
    String vehicle;
    String registrationNumber;
    String emission;
    String weight;
    String date;
    String distance;
    String unit;
}
