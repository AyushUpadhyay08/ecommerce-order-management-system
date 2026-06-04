package com.example.E_commerce.entity;

import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
//import lombok.NoArgsConstructor;

@Entity

@Data

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String productName;
    private String description;
    private Double price;
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
