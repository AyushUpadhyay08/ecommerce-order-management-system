package com.example.E_commerce.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity

@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
