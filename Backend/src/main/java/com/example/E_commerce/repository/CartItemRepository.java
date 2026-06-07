package com.example.E_commerce.repository;


import com.example.E_commerce.entity.Cart;
import com.example.E_commerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    List<CartItem> findByCart(Cart cart);
}
