package com.example.webapp.repository;


import com.example.webapp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, Long> {
    Restaurant findByName(String name);
}

