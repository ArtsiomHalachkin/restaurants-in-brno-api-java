package com.example.webapp.repository;


import com.example.webapp.model.Restaurant;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.geo.Point;
import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant, Long> {
    Restaurant findByName(String name);
    List<Restaurant> findByTypeIgnoreCase(String type);
    List<Restaurant> findByLocationNear(Point location, Distance distance);
    List<Restaurant> findByTypeIgnoreCaseAndLocationNear(String type, Point location, Distance distance);
}

