package se.knowit.iz.carbonrebound.dtotoentity;

import se.knowit.iz.carbonrebound.dto.PrivateVehicleDTO;
import se.knowit.iz.carbonrebound.entities.PrivateVehicle;

public class DtoToEntityService {

    public static PrivateVehicle privateVehicleDtoToVehicleEntity(PrivateVehicleDTO privateVehicleDTO) {
            PrivateVehicle privateVehicle = new PrivateVehicle();
            privateVehicle.setRegistrationNumber(privateVehicleDTO.getRegistrationNumber());
            privateVehicle.setBrand(privateVehicleDTO.getBrand());
            privateVehicle.setModel(privateVehicleDTO.getModel());
            privateVehicle.setYear(privateVehicleDTO.getYear());
            privateVehicle.setCO2Emissions(privateVehicleDTO.getCo2Emissions());
            return privateVehicle;
    }
}
