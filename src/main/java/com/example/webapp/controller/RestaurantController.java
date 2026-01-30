package com.example.webapp.controller;
import com.example.webapp.dto.RestaurantDto;
import com.example.webapp.model.Restaurant;
import com.example.webapp.repository.RestaurantRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setLatitude(restaurantDto.getLatitude());
        restaurant.setLongitude(restaurantDto.getLongitude());

        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantRepository.save(restaurant));

    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantbyId(
            @PathVariable Long id,
            @Valid RestaurantDto restaurantDto) {

        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        return ResponseEntity.ok(restaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantDto restaurantDto) {

        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

        existingRestaurant.setName(restaurantDto.getName());
        existingRestaurant.setAddress(restaurantDto.getAddress());
        existingRestaurant.setLatitude(restaurantDto.getLatitude());
        existingRestaurant.setLongitude(restaurantDto.getLongitude());

        return ResponseEntity.ok(restaurantRepository.save(existingRestaurant));
    }
}
