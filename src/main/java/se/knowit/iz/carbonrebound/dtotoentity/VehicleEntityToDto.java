package se.knowit.iz.carbonrebound.dtotoentity;

import org.springframework.stereotype.Service;
import se.knowit.iz.carbonrebound.dto.PrivateVehicleDTO;
import se.knowit.iz.carbonrebound.entities.Vehicle;

@Service
public interface VehicleEntityToDto {

    default PrivateVehicleDTO vehicleEntityToDto(Vehicle vehicle) {
        return new PrivateVehicleDTO (
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getRegistrationNumber(),
                vehicle.getCO2Emissions()
        );
    }
}
