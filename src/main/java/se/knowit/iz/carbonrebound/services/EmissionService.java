package se.knowit.iz.carbonrebound.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.knowit.iz.carbonrebound.constants.GoogleMaps;
import se.knowit.iz.carbonrebound.domain.GoogleMapsDistance;
import se.knowit.iz.carbonrebound.domain.GoogleMapsLocation;
import se.knowit.iz.carbonrebound.dto.EmissionPrivateVehicleDTO;
import se.knowit.iz.carbonrebound.entities.Emission;
import se.knowit.iz.carbonrebound.entities.PrivateVehicle;
import se.knowit.iz.carbonrebound.entities.User;
import se.knowit.iz.carbonrebound.exceptions.NotFoundUserException;
import se.knowit.iz.carbonrebound.exceptions.NotFoundVehicleException;
import se.knowit.iz.carbonrebound.repositories.EmissionRepository;
import se.knowit.iz.carbonrebound.repositories.UserRepository;
import se.knowit.iz.carbonrebound.repositories.VehicleRepository;
import se.knowit.iz.carbonrebound.vehiclesdetails.VehiclesDetails;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class EmissionService {

    private GoogleMapsDistance googleMapsDistance;
    private GoogleMapsLocation googleMapsLocation;
    private final GoogleMaps googleMaps;
    private final UserRepository userRepository;
    private final EmissionRepository emissionRepository;
    private final VehicleRepository vehicleRepository;

    public EmissionService(GoogleMapsDistance googleMapsDistance,
                           GoogleMapsLocation googleMapsLocation,
                           GoogleMaps googleMaps,
                           UserRepository userRepository,
                           EmissionRepository emissionRepository,
                           VehicleRepository vehicleRepository) {
        this.googleMapsDistance = googleMapsDistance;
        this.googleMapsLocation = googleMapsLocation;
        this.googleMaps = googleMaps;
        this.userRepository = userRepository;
        this.emissionRepository = emissionRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public ResponseEntity< ? > createEmissionForPrivateVehicle(EmissionPrivateVehicleDTO emissionPrivateVehicleDTO, Long userId) {

        try {
            Optional< User > foundUser = Optional.ofNullable(userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundUserException("User is not found with userId: " + userId)));
            Optional< PrivateVehicle > foundPrivateVehicle = Optional.ofNullable(vehicleRepository.getByRegistrationNumber(emissionPrivateVehicleDTO.getRegistrationNumber())
                    .orElseThrow(() -> new NotFoundVehicleException("Private Vehicle is not found with registration number: " + emissionPrivateVehicleDTO.getRegistrationNumber())));
            if (foundUser.isPresent() && foundPrivateVehicle.isPresent()) {

                double distanceBetweenToPoints = getDistanceBetweenToPoints(emissionPrivateVehicleDTO);
                double countTotalEmissionForTrip = countTotalEmissionForTrip(foundPrivateVehicle.get().getCO2Emissions(), distanceBetweenToPoints);

                log.info("Trying to save emission...");
                emissionRepository.save(new Emission(
                        VehiclesDetails.typeOfVehicles.CAR,
                        emissionPrivateVehicleDTO.getDateOfTrip(),
                        emissionPrivateVehicleDTO.getDistanceFrom(),
                        emissionPrivateVehicleDTO.getDistanceTo(),
                        foundPrivateVehicle.get().getId(),
                        distanceBetweenToPoints,
                        countTotalEmissionForTrip,
                        foundUser.get()));
                log.info("Successfully saved emission...");
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NotFoundUserException e) {
            log.error("User is not found, error: ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NotFoundVehicleException e) {
            log.error("Private vehicle is not found, error: ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Didn't get from service! Error: ", e);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public double countTotalEmissionForTrip(int co2Emission, double distanceBetweenToPoints) {
        return ((double) co2Emission / 1000) * distanceBetweenToPoints;
    }

    public double getDistanceBetweenToPoints(EmissionPrivateVehicleDTO emissionPrivateVehicleDTO) throws Exception {

        ResponseBody responseBodyForPlaceIdFrom = responseBodyForPlaceId(emissionPrivateVehicleDTO.getPlaceIdFrom());
        ResponseBody responseBodyForPlaceIdTo = responseBodyForPlaceId(emissionPrivateVehicleDTO.getPlaceIdTo());

        String jsonResponseBodyForPlaceIdFrom = responseBodyForPlaceIdFrom.string();
        String jsonResponseBodyForPlaceIdTo = responseBodyForPlaceIdTo.string();

        GoogleMapsLocation googleMapsLocationForPlaceIdFrom = parseStringToJsonObjectForPlaceId(jsonResponseBodyForPlaceIdFrom);
        GoogleMapsLocation googleMapsLocationForPlaceIdTo = parseStringToJsonObjectForPlaceId(jsonResponseBodyForPlaceIdTo);

        ResponseBody responseBodyForDistance = responseBodyForDistance(googleMapsLocationForPlaceIdFrom, googleMapsLocationForPlaceIdTo);

        GoogleMapsDistance googleMapsDistance = parseStringToJsonObjectForDistance(responseBodyForDistance.string());

        return Double.parseDouble(googleMapsDistance.getDistance());
    }

    private ResponseBody responseBodyForDistance(GoogleMapsLocation googleMapsLocationForPlaceIdFrom, GoogleMapsLocation googleMapsLocationForPlaceIdTo) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request requestForDistance = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=" +
                        googleMapsLocationForPlaceIdFrom.getLatitude() + "," + googleMapsLocationForPlaceIdFrom.getLongitude() + "&destinations=" +
                        googleMapsLocationForPlaceIdTo.getLatitude() + "%2C" + googleMapsLocationForPlaceIdTo.getLongitude() + "&key=" + googleMaps.API_KEY)
                .get()
                .build();
        return client.newCall(requestForDistance).execute().body();
    }


    private ResponseBody responseBodyForPlaceId(String placeId) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request requestForPlaceId = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/place/details/json?placeid=" + placeId + "&key=" + googleMaps.API_KEY)
                .get()
                .build();
        return client.newCall(requestForPlaceId).execute().body();
    }

    private GoogleMapsLocation parseStringToJsonObjectForPlaceId(String jsonResponseBodyForPlaceId) {

        googleMapsLocation = new GoogleMapsLocation();

        JsonParser jsonObjectForPlaceIdFrom = new JsonParser();
        JsonElement jsonElement = jsonObjectForPlaceIdFrom.parse(jsonResponseBodyForPlaceId)
                .getAsJsonObject().get("result")
                .getAsJsonObject().get("geometry")
                .getAsJsonObject().get("location");

        String lat = jsonElement.getAsJsonObject().get("lat").getAsString();
        String lng = jsonElement.getAsJsonObject().get("lng").getAsString();

        googleMapsLocation.setLatitude(lat);
        googleMapsLocation.setLongitude(lng);

        return googleMapsLocation;
    }

    private GoogleMapsDistance parseStringToJsonObjectForDistance(String jsonResponseBodyForDistance) {

        googleMapsDistance = new GoogleMapsDistance();

        JsonParser jsonObjectForDistance = new JsonParser();
        String distance = jsonObjectForDistance.parse(jsonResponseBodyForDistance)
                .getAsJsonObject().getAsJsonArray("rows").get(0)
                .getAsJsonObject().getAsJsonArray("elements").get(0)
                .getAsJsonObject().get("distance")
                .getAsJsonObject().get("text").getAsString();

        googleMapsDistance.setDistance(distance.replaceAll("\\s.*", ""));

        return googleMapsDistance;
    }
}
