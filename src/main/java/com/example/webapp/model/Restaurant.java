package com.example.webapp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Restaurant {
    @Id
    private long id;

    private String name;

    private String address;

    private double rating;

    private double latitude;

    private double longitude;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public double getRating() {return rating;}
    public void setRating(double rating) {this.rating = rating;}

    public double getLatitude() {return latitude;}
    public void setLatitude(double latitude) {this.latitude = latitude;}

    public double getLongitude() {return longitude;}
    public void setLongitude(double longitude) {this.longitude = longitude;}
}
