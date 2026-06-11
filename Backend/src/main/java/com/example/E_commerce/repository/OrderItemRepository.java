package com.example.E_commerce.repository;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    java.util.List<OrderItem> findByOrder(Order order);
}
