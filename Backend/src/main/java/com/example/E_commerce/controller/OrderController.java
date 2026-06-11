package com.example.E_commerce.controller;

import com.example.E_commerce.dto.OrderResponseDTO;
import com.example.E_commerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/checkout/{user_id}")
    public OrderResponseDTO checkOut(@PathVariable Long user_id){
        return orderService.checkOut(user_id);
    }

    @GetMapping("/checkout/{user_id}")
    public List<OrderResponseDTO> getOrdersByUserId(@PathVariable Long user_id){
        return orderService.getOrdersByUserId(user_id);
    }
    @GetMapping("/order_id")
    public OrderResponseDTO getOrderById(@PathVariable("order_id") Long order_id){
        return orderService.getOrdersById(order_id);
    }
}
