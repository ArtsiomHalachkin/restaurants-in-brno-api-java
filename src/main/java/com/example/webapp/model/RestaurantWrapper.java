package com.example.webapp.model;

import com.example.webapp.dto.RestaurantDto;

import java.util.List;

public class RestaurantWrapper {
    private List<RestaurantDto> restaurants;

    public List<RestaurantDto> getRestaurants() { return restaurants; }
    public void setRestaurants(List<RestaurantDto> restaurants) { this.restaurants = restaurants; }
}