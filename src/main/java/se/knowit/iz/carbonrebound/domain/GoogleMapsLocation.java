package se.knowit.iz.carbonrebound.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class GoogleMapsLocation {

    String latitude;
    String longitude;
}
