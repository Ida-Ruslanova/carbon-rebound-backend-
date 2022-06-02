package se.knowit.iz.carbonrebound.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.knowit.iz.carbonrebound.dto.EmissionPrivateVehicleDTO;
import se.knowit.iz.carbonrebound.services.EmissionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/auth/v1/emissions")
public class EmissionController {

    EmissionService emissionService;

    public EmissionController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    @PostMapping("/createemissionforprivatevehicle")
    public ResponseEntity< ? > createEmissionForPrivateVehicle(@RequestBody EmissionPrivateVehicleDTO emissionPrivateVehicleDTO,
                                                               @RequestParam Long userId) {
        return emissionService.createEmissionForPrivateVehicle(emissionPrivateVehicleDTO, userId);
    }

    @GetMapping("/getalltripsforuser")
    public ResponseEntity< ? > getAllTripsForUser(@RequestParam Long userId) {
        return emissionService.getAllTripsForUser(userId);
    }

    @GetMapping("/gettotalemissionstodayforuser")
    public ResponseEntity< ? > getTotalEmissionsTodayForUser(@RequestParam Long userId) {
        return emissionService.getTotalEmissionsTodayForUser(userId);
    }
}
