package com.example.webapp.utils;

import com.example.webapp.dto.RestaurantDto;
import com.example.webapp.model.Restaurant;
import com.example.webapp.model.RestaurantWrapper;
import com.example.webapp.repository.RestaurantRepository;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;


//Component Tells Spring Boot to create this class automatically when the app starts.
// implements CommandLineRunner - A special Spring interface.
// Anything inside the run() method executes immediately after the server starts up

@Component
public class JsonDataSeeder implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;
    private final ObjectMapper objectMapper;

    public JsonDataSeeder(RestaurantRepository repository, ObjectMapper objectMapper) {
        this.restaurantRepository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        restaurantRepository.deleteAll();
        if (restaurantRepository.count() == 0) {

            InputStream inputStream = TypeReference.class.getResourceAsStream("/restaurants.json");

            try {
                RestaurantWrapper wrapper = objectMapper.readValue(inputStream, RestaurantWrapper.class);

                List<Restaurant> restaurants = wrapper.getRestaurants()
                        .stream()
                        .map(restaurant -> this.convertToCollection(restaurant))
                        .collect(Collectors.toList());

                restaurantRepository.saveAll(restaurants);

                System.out.println("Brno Restaurant Database Seeded!");

            } catch (JacksonException e) {
                System.out.println("Unable to save restaurants: " + e.getMessage());
            }
        }
    }

    private Restaurant convertToCollection(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setName(restaurantDto.getName());
        restaurant.setRating(restaurantDto.getRating());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setType(restaurantDto.getType());
        restaurant.setLocation(new GeoJsonPoint(restaurantDto.getLongitude(), restaurantDto.getLatitude()));

        return restaurant;
    }
}