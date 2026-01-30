package com.example.webapp.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Restaurant {
    @Id
    private long id;

    private String name;

    private String address;

    private String type;

    private double rating;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    public Long getId() {return this.id; }
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public double getRating() {return rating;}
    public void setRating(double rating) {this.rating = rating;}

    public GeoJsonPoint getLocation() {return location;}
    public void setLocation(GeoJsonPoint location) {this.location = location;}
}
