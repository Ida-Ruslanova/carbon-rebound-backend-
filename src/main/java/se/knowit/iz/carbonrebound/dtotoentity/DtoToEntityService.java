package se.knowit.iz.carbonrebound.dtotoentity;

import se.knowit.iz.carbonrebound.dto.PrivateVehicleDTO;
import se.knowit.iz.carbonrebound.entities.Vehicle;

public class DtoToEntityService {

    public static Vehicle privateVehicleDtoToVehicleEntity(PrivateVehicleDTO privateVehicleDTO) {
            Vehicle vehicle = new Vehicle();
            vehicle.setRegistrationNumber(privateVehicleDTO.getRegistrationNumber());
            vehicle.setBrand(privateVehicleDTO.getBrand());
            vehicle.setModel(privateVehicleDTO.getModel());
            vehicle.setYear(privateVehicleDTO.getYear());
            vehicle.setCO2Emissions(privateVehicleDTO.getCo2Emissions());
            return vehicle;
    }
}
