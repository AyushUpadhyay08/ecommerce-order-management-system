package com.example.E_commerce.dto;

public class UpdateCartDTO {
    private Integer quantity;
    public UpdateCartDTO(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getQuantity() {
        return quantity;
    }
}
