package com.example.E_commerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @Positive
    private Double price;
    @ManyToOne
    @JoinColumn
    private Order order;
    @ManyToOne
    @JoinColumn
    private Product product;

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() { return id; }

    public Integer getQuantity() { return quantity; }

    public Double getPrice() { return price; }

    public Order getOrder() { return order; }

    public Product getProduct() { return product; }
}
