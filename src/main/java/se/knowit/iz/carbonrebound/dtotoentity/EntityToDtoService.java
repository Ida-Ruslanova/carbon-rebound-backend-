package se.knowit.iz.carbonrebound.dtotoentity;

import se.knowit.iz.carbonrebound.dto.PrivateVehicleDTO;
import se.knowit.iz.carbonrebound.entities.Vehicle;

public class EntityToDtoService {

    public static PrivateVehicleDTO privateVehicleEntityToDto(Vehicle vehicle) {
        PrivateVehicleDTO privateVehicleDTO = new PrivateVehicleDTO();
        privateVehicleDTO.setRegistrationNumber(vehicle.getRegistrationNumber());
        privateVehicleDTO.setBrand(vehicle.getBrand());
        privateVehicleDTO.setModel(vehicle.getModel());
        privateVehicleDTO.setYear(vehicle.getYear());
        privateVehicleDTO.setCo2Emissions(vehicle.getCO2Emissions());
        return privateVehicleDTO;
    }

}
