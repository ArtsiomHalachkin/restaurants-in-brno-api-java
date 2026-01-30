package com.example.webapp.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class RestaurantDto {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private Double rating;

    @NotBlank
    private Double latitude;

    @NotBlank
    private Double longitude;

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name;}

    public String getAddress() { return this.address; }
    public void setAddress(String address) { this.address = address; }

    public Double getRating() { return this.rating;}
    public void setRating(Double rating) { this.rating = rating;}

    public Double getLatitude() { return this.latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude;}

    public Double getLongitude() { return this.longitude;}
    public void setLongitude(Double longitude) { this.longitude = longitude;}
}
