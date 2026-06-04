package com.example.E_commerce.dto;

public class ProductResponseDTO {
    private int id;
    private String productName;
    private Double price;
    private String categoryName;
    public ProductResponseDTO(int id, String productName, Double price, String categoryName) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
