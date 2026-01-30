package com.example.webapp.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class RestaurantDto {

    @NotBlank
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    private String type;

    @NotNull
    @Min(0)
    @Max(5)
    private Double rating;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    public Long getId() {return this.id; }
    public void setId(long id) {this.id = id;}

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name;}

    public String getAddress() { return this.address; }
    public void setAddress(String address) { this.address = address; }

    public String getType() { return this.type; }
    public void setType(String type) { this.type = type; }

    public Double getRating() { return this.rating;}
    public void setRating(Double rating) { this.rating = rating;}

    public double getLatitude() { return this.latitude;}
    public void setLatitude(double latitude) { this.latitude = latitude;}

    public double getLongitude() { return this.longitude;}
    public void setLongitude(double longitude) { this.longitude = longitude;}
}
