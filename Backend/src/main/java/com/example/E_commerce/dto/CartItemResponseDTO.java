package com.example.E_commerce.dto;

public class CartItemResponseDTO {
    private int cartItemId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Double totalPrice;
    public CartItemResponseDTO() {

    }
    public CartItemResponseDTO(int cartItemId, String productName, Double productPrice, Integer quantity, Double totalPrice) {
        this.cartItemId = cartItemId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
