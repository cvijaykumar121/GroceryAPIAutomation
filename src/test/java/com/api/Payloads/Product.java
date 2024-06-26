package com.api.Payloads;

public class Product {
    private int id;
    private String category;
    private String name;
    private boolean inStock;

    // Default constructor (required by Jackson)
    public Product() {
    }

    // Constructor with all fields
    public Product(int id, String category, String name, boolean inStock) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.inStock = inStock;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
