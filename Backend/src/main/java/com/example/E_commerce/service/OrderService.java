package com.example.E_commerce.service;

import com.example.E_commerce.exception.CartEmptyException;
import com.example.E_commerce.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.E_commerce.dto.OrderResponseDTO;
import com.example.E_commerce.entity.*;
import com.example.E_commerce.enums.OrderStatus;


import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import com.example.E_commerce.dto.OrderItemResponseDTO;

@Service
public class OrderService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    public OrderService(UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                        ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.cartRepository= cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderResponseDTO checkOut(Long user_id){
        User user= userRepository.findById(user_id)
                .orElseThrow(()->new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUserId(user_id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        List<CartItem> cartItem=cartItemRepository.findByCart(cart);
        if(cartItem.isEmpty()){
            throw new CartEmptyException("Cart is empty");
        }
        double total=0.0;
        for(CartItem cartItem1:cartItem){
            total +=cartItem1.getQuantity()*cartItem1.getProduct().getPrice();
        }
        Order order=new Order();
        order.setUser(user);
        order.setTotalAmount(total);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        // persist order to obtain generated id
        Order savedOrder = orderRepository.save(order);

        java.util.List<OrderItemResponseDTO> items = new ArrayList<>();
        for(CartItem ci : cartItem){
            Product product = ci.getProduct();
            Integer stock = product.getStock() != null ? product.getStock() : 0;
            if(stock < ci.getQuantity()){
                throw new RuntimeException("Insufficient stock for product: " + product.getProductName());
            }
            product.setStock(stock - ci.getQuantity());
            // persist updated product stock
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(ci.getQuantity());
            orderItem.setPrice(product.getPrice());
            // persist each order item
            orderItemRepository.save(orderItem);
            items.add(new OrderItemResponseDTO(product.getProductName(), ci.getQuantity(), product.getPrice()));

        }
        cartItemRepository.deleteAll(cartItem);
        return new OrderResponseDTO(savedOrder.getId(), savedOrder.getTotalAmount(), savedOrder.getStatus().name(), items);
    }
    @Transactional(readOnly = true)
    public java.util.List<OrderResponseDTO> getOrdersByUserId(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        java.util.List<Order> orders = orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return orders.stream().map(o -> {
            java.util.List<OrderItem> orderItems = orderItemRepository.findByOrder(o);
            java.util.List<OrderItemResponseDTO> itemsDto = orderItems.stream()
                    .map(oi -> new OrderItemResponseDTO(oi.getProduct().getProductName(), oi.getQuantity(), oi.getPrice()))
                    .collect(java.util.stream.Collectors.toList());
            return new OrderResponseDTO(o.getId(), o.getTotalAmount(), o.getStatus().name(), itemsDto);
        }).collect(java.util.stream.Collectors.toList());
    }
    public OrderResponseDTO getOrdersById(Long order_id){
        Order order=orderRepository.findById(order_id).
                orElseThrow(() -> new RuntimeException("Order not found"));
        List<OrderItem> orderItems=orderItemRepository.findByOrder(order);
        List<OrderItemResponseDTO> orderItemResponseDTOList=new ArrayList<>();
        for(OrderItem orderItem:orderItems){
            orderItemResponseDTOList.add(new OrderItemResponseDTO(orderItem.getProduct().getProductName(),orderItem.getQuantity(),orderItem.getPrice()));
        }
        return new OrderResponseDTO(order.getId(),order.getTotalAmount(),order.getStatus().name(),orderItemResponseDTOList);
    }
}
