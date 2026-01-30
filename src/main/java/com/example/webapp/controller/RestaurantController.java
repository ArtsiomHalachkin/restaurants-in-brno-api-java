package com.example.webapp.controller;
import com.example.webapp.dto.RestaurantDto;
import com.example.webapp.model.Restaurant;
import com.example.webapp.repository.RestaurantRepository;
import jakarta.validation.Valid;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@Valid @RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();

        restaurant.setName(restaurantDto.getName());
        restaurant.setRating(restaurantDto.getRating());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setType(restaurantDto.getType());
        restaurant.setLocation(new GeoJsonPoint(restaurantDto.getLongitude(), restaurantDto.getLatitude()));

        restaurantRepository.save(restaurant);

        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getRestaurants() {
        List<RestaurantDto> restaurants = restaurantRepository.findAll()
                .stream()
                .map(restaurant -> this.convertToDto(restaurant))
                .collect(Collectors.toList());

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantbyId(
            @PathVariable Long id,
            @Valid RestaurantDto restaurantDto) {

        RestaurantDto restaurant = restaurantRepository.findById(id)
                .map(res -> this.convertToDto(res))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

        return ResponseEntity.ok(restaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantDto restaurantDto) {

        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

        existingRestaurant.setName(restaurantDto.getName());
        existingRestaurant.setRating(restaurantDto.getRating());
        existingRestaurant.setAddress(restaurantDto.getAddress());
        existingRestaurant.setType(restaurantDto.getType());
        existingRestaurant.setLocation(new GeoJsonPoint(restaurantDto.getLongitude(), restaurantDto.getLatitude()));

        restaurantRepository.save(existingRestaurant);

        return ResponseEntity.ok(existingRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Long id) {

        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

        restaurantRepository.delete(existingRestaurant);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/near")
    public ResponseEntity<List<RestaurantDto>> getNearby(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam double distanceKm) {

        Point currentLoc = new Point(lon, lat);
        Distance radius = new Distance(distanceKm, Metrics.KILOMETERS);

        List<RestaurantDto> restaurants = restaurantRepository.findByLocationNear(currentLoc, radius)
                .stream()
                .map(restaurant -> this.convertToDto(restaurant))
                .collect(Collectors.toList());

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/type/{category}")
    public ResponseEntity<List<RestaurantDto>> getByTypeName(@PathVariable String category) {

        List<RestaurantDto> restaurants = restaurantRepository.findByTypeIgnoreCase(category)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("near/type/{category}")
    public ResponseEntity<List<RestaurantDto>> getByTypeAndNearby(
            @PathVariable String category,
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam double distanceKm) {

        Point currentLoc = new Point(lon, lat);
        Distance radius = new Distance(distanceKm, Metrics.KILOMETERS);

        List<RestaurantDto> restaurants = restaurantRepository.findByTypeIgnoreCaseAndLocationNear(
                category, currentLoc, radius)
                .stream()
                .map(restaurant -> this.convertToDto(restaurant))
                .collect(Collectors.toList());

        return ResponseEntity.ok(restaurants);
    }

    private RestaurantDto convertToDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setRating(restaurant.getRating());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setType(restaurant.getType());

        if (restaurant.getLocation() != null) {
            List<Double> coordinates = restaurant.getLocation().getCoordinates();
            restaurantDto.setLongitude(coordinates.get(0));
            restaurantDto.setLatitude(coordinates.get(1));
        }
        return restaurantDto;
    }
}
