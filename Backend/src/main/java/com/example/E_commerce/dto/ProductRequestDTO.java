package com.example.E_commerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ProductRequestDTO {
    @NotBlank
    private String productName;
    private String description;
    @NotNull
    private Double price;
    @Min(0)
    private Integer stock;
    private int categoryId;

    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){

        this.productName=productName;
    }
    public Double getPrice(){

        return price;
    }
    public void setPrice(Double price){
        this.price=price;
    }
    public int getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(int categoryId){
        this.categoryId=categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
