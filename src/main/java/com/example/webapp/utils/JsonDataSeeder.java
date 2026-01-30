package com.example.webapp.utils;

import com.example.webapp.model.RestaurantWrapper;
import com.example.webapp.repository.RestaurantRepository;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;


//Component Tells Spring Boot to create this class automatically when the app starts.

// implements CommandLineRunner - A special Spring interface.
// Anything inside the run() method executes immediately after the server starts up


@Component
public class JsonDataSeeder implements CommandLineRunner {

    private final RestaurantRepository repository;
    private final ObjectMapper objectMapper;

    public JsonDataSeeder(RestaurantRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/restaurants.json");
            try {
                RestaurantWrapper wrapper = objectMapper.readValue(inputStream, RestaurantWrapper.class);
                repository.saveAll(wrapper.getRestaurants());
                System.out.println("Brno Restaurant Database Seeded!");
            } catch (JacksonException e) {
                System.out.println("Unable to save restaurants: " + e.getMessage());
            }
        }
    }
}