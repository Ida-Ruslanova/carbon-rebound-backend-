package se.knowit.iz.carbonrebound.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.knowit.iz.carbonrebound.dto.PrivateVehicleDTO;
import se.knowit.iz.carbonrebound.exceptions.NotFoundVehicleException;
import se.knowit.iz.carbonrebound.services.TransportService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/auth/v1/transports")
public class TransportController {

    TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @GetMapping(path = "/get/{registration-number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< PrivateVehicleDTO > getPrivateVehicleByRegistrationNumber(
            @PathVariable(value = "registration-number") String registrationNumber) {
        return transportService.getPrivateVehicleByRegistrationNumber(registrationNumber);
    }

    @PostMapping("/registervehicle")
    public ResponseEntity< ? > registerVehicle(@RequestBody PrivateVehicleDTO privateVehicleDTO, @RequestParam Long userId) {
        return transportService.registrateVehicle(privateVehicleDTO, userId);
    }

    @GetMapping("/vehicles")
    public List<PrivateVehicleDTO> getVehicles(@RequestParam Long userId) {
        return transportService.getAllVehicles(userId);
    }

    @DeleteMapping("/deletevehicle")
    public void deleteVehicleByRegNumber(@RequestParam String regNumber) throws NotFoundVehicleException {
        transportService.deleteVehicle(regNumber);
    }
}
