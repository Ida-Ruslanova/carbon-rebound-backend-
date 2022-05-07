package se.knowit.iz.carbonrebound.dtotoentity;

import org.springframework.stereotype.Service;
import se.knowit.iz.carbonrebound.dto.PrivateVehicleDTO;
import se.knowit.iz.carbonrebound.entities.PrivateVehicle;

@Service
public interface VehicleEntityToDto {

    default PrivateVehicleDTO vehicleEntityToDto(PrivateVehicle privateVehicle) {
        return new PrivateVehicleDTO (
                privateVehicle.getBrand(),
                privateVehicle.getModel(),
                privateVehicle.getYear(),
                privateVehicle.getRegistrationNumber(),
                privateVehicle.getCO2Emissions()
        );
    }
}
