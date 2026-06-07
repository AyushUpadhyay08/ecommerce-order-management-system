package com.example.E_commerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setCart(Cart cart)
    {
        this.cart=cart;
    }
    public void setProduct(Product product)
    {
        this.product = product;
    }
    public int getId() {
        return id;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public Cart getCart() {
        return cart;
    }
    public Product getProduct() {
        return product;
    }


}
