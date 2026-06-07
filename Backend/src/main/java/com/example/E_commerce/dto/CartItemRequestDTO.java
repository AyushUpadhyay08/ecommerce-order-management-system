package com.example.E_commerce.dto;

public class CartItemRequestDTO {
    private Integer quantity;
    private Long userId;
    private Integer productId;

    public CartItemRequestDTO() {
    }

    public CartItemRequestDTO(Long userId, Integer productId, Integer quantity) {
        this.userId = userId;
        this.productId=productId;
        this.quantity=quantity;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getUserId(){
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getProductId(){
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
