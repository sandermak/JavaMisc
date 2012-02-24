package com.infosupport.beans;

import org.primefaces.model.DefaultStreamedContent;

public class Sandwich {

    private int id;
    
    private String name;
    
    private String description;
    
    private int price;
    
    private String imageLocation;
    
    private DefaultStreamedContent image;

    public Sandwich(int id, String name, String description, int price, String imageLocation) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.imageLocation = imageLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
    
    
}