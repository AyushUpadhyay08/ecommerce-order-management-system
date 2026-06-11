package com.example.E_commerce.repository;

import com.example.E_commerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    java.util.List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);
}
