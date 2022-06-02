package se.knowit.iz.carbonrebound.dtotoentity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import se.knowit.iz.carbonrebound.dto.PrivateVehicleDTO;
import se.knowit.iz.carbonrebound.dto.TripEmissionPrivateVehicleDTO;
import se.knowit.iz.carbonrebound.entities.Emission;
import se.knowit.iz.carbonrebound.entities.PrivateVehicle;
import se.knowit.iz.carbonrebound.exceptions.NotFoundVehicleException;
import se.knowit.iz.carbonrebound.repositories.VehicleRepository;

import java.util.Optional;

@Slf4j
@Component
public class EntityToDtoService {

    private VehicleRepository vehicleRepository;

    public EntityToDtoService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public static PrivateVehicleDTO privateVehicleEntityToDto(PrivateVehicle privateVehicle) {
        PrivateVehicleDTO privateVehicleDTO = new PrivateVehicleDTO();
        privateVehicleDTO.setRegistrationNumber(privateVehicle.getRegistrationNumber());
        privateVehicleDTO.setBrand(privateVehicle.getBrand());
        privateVehicleDTO.setModel(privateVehicle.getModel());
        privateVehicleDTO.setYear(privateVehicle.getYear());
        privateVehicleDTO.setCo2Emissions(privateVehicle.getCO2Emissions());
        return privateVehicleDTO;
    }

    public TripEmissionPrivateVehicleDTO emissionEntityToDto(Emission emission){

        Optional< PrivateVehicle > foundVehicle;

        try {
            foundVehicle = Optional.ofNullable(vehicleRepository.findById(emission.getVehicleId()).orElseThrow(() -> new NotFoundVehicleException("Vehicle is not found with id: " + emission.getVehicleId())));
        } catch (NotFoundVehicleException e) {
            log.error("Private vehicle is not found, error: ", e);
            return null;
        }
        return new TripEmissionPrivateVehicleDTO(
                Long.toString(emission.getId()),
                emission.getDistanceFrom(),
                emission.getDistanceTo(),
                foundVehicle.get().getBrand(),
                foundVehicle.get().getRegistrationNumber(),
                Double.toString(emission.getTotalEmission()),
                "kg",
                emission.getDateOfTrip(),
                Double.toString(emission.getDistance()),
                "km");
    }
}
