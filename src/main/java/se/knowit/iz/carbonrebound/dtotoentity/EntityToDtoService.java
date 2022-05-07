package se.knowit.iz.carbonrebound.dtotoentity;

import se.knowit.iz.carbonrebound.dto.PrivateVehicleDTO;
import se.knowit.iz.carbonrebound.entities.PrivateVehicle;

public class EntityToDtoService {

    public static PrivateVehicleDTO privateVehicleEntityToDto(PrivateVehicle privateVehicle) {
        PrivateVehicleDTO privateVehicleDTO = new PrivateVehicleDTO();
        privateVehicleDTO.setRegistrationNumber(privateVehicle.getRegistrationNumber());
        privateVehicleDTO.setBrand(privateVehicle.getBrand());
        privateVehicleDTO.setModel(privateVehicle.getModel());
        privateVehicleDTO.setYear(privateVehicle.getYear());
        privateVehicleDTO.setCo2Emissions(privateVehicle.getCO2Emissions());
        return privateVehicleDTO;
    }

}
