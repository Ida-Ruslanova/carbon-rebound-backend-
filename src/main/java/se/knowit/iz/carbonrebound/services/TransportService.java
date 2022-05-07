package se.knowit.iz.carbonrebound.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.knowit.iz.carbonrebound.constants.Urls;
import se.knowit.iz.carbonrebound.dto.PrivateVehicleDTO;
import se.knowit.iz.carbonrebound.entities.User;
import se.knowit.iz.carbonrebound.entities.PrivateVehicle;
import se.knowit.iz.carbonrebound.exceptions.NotFoundVehicleException;
import se.knowit.iz.carbonrebound.repositories.UserRepository;
import se.knowit.iz.carbonrebound.repositories.VehicleRepository;
import se.knowit.iz.carbonrebound.security.payload.response.MessageResponse;
import se.knowit.iz.carbonrebound.utils.Crawler;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static se.knowit.iz.carbonrebound.dtotoentity.DtoToEntityService.privateVehicleDtoToVehicleEntity;

@Service
@Slf4j
public class TransportService {

    private Crawler crawler;
    private Urls urls;
    private VehicleRepository vehicleRepository;
    private UserRepository userRepository;

    public TransportService(Crawler crawler, Urls urls, VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.crawler = crawler;
        this.urls = urls;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity< PrivateVehicleDTO > getPrivateVehicleByRegistrationNumber(String registrationNumber) {
        try {
            return new ResponseEntity<>(crawler.findVehiclesDetails(urls.getBASE_URL() + registrationNumber), HttpStatus.OK);
        } catch (NotFoundVehicleException e) {
            log.error("Vehicle with registration-number: {} didn't found from service! Error: {}", registrationNumber, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            log.error("Vehicle with registration-number: {} didn't get from service! Error: {}", registrationNumber, e);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity< ? > registrateVehicle(PrivateVehicleDTO privateVehicleDTO, Long userId) {
        User foundUser = userRepository.getById(userId);
        PrivateVehicle privateVehicleToRegister = privateVehicleDtoToVehicleEntity(privateVehicleDTO);
        if (foundUser.getPrivateVehicles().stream().noneMatch(vehicle -> vehicle.getRegistrationNumber().equals(privateVehicleDTO.getRegistrationNumber())) || userRepository.findAll().stream().noneMatch(user -> user.getPrivateVehicles().stream().noneMatch(vehicle -> vehicle.getRegistrationNumber().equals(privateVehicleDTO.getRegistrationNumber())))) {
            foundUser.getPrivateVehicles().add(privateVehicleToRegister);
            privateVehicleToRegister.setUser(foundUser);
            vehicleRepository.save(privateVehicleToRegister);
            userRepository.save(foundUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.error("The vehicle with registration number " + privateVehicleToRegister.getRegistrationNumber() + " already exists! ");
            return new ResponseEntity<>(
                    new MessageResponse("The vehicle with registration number " + privateVehicleToRegister.getRegistrationNumber() + " already exists! "),
                    HttpStatus.BAD_GATEWAY);
        }
    }

    public List<PrivateVehicleDTO> getAllVehicles(Long userId) {
        return userRepository.getById(userId).getPrivateVehicles().stream().map(this::privateVehicleEntityToDto).collect(Collectors.toList());
    }

    public void deleteVehicle(String regNumber) throws NotFoundVehicleException {
        PrivateVehicle privateVehicleShouldBeDeleted = vehicleRepository.findAll().stream().filter(vehicle -> vehicle.getRegistrationNumber().equals(regNumber)).findAny().orElseThrow(NotFoundVehicleException::new);
        userRepository.findAll().stream().map(user -> user.getPrivateVehicles().remove(privateVehicleShouldBeDeleted));
        vehicleRepository.delete(privateVehicleShouldBeDeleted);
    }

    public PrivateVehicleDTO privateVehicleEntityToDto(PrivateVehicle privateVehicle) {
        PrivateVehicleDTO privateVehicleDTO = new PrivateVehicleDTO();
        privateVehicleDTO.setRegistrationNumber(privateVehicle.getRegistrationNumber());
        privateVehicleDTO.setBrand(privateVehicle.getBrand());
        privateVehicleDTO.setModel(privateVehicle.getModel());
        privateVehicleDTO.setYear(privateVehicle.getYear());
        privateVehicleDTO.setCo2Emissions(privateVehicle.getCO2Emissions());
        return privateVehicleDTO;
    }

}
