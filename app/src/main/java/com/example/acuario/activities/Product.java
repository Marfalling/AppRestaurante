package com.example.acuario.activities;

public class Product {
    private String name;
    private double price;
    private int imageResource;
    private String category;

    public Product(String name, double price, int imageResource, String category) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getCategory() {
        return category;
    }
}
