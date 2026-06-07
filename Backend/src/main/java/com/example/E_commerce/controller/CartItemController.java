package com.example.E_commerce.controller;

import com.example.E_commerce.dto.CartItemRequestDTO;
import com.example.E_commerce.dto.CartItemResponseDTO;
import com.example.E_commerce.dto.UpdateCartDTO;
import com.example.E_commerce.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart-items")
public class CartItemController {
    public final CartItemService cartItemService;
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService=cartItemService;
    }
    @PostMapping
    public CartItemResponseDTO addItem(@RequestBody CartItemRequestDTO cartItemRequestDTO){
        return cartItemService.addItem(cartItemRequestDTO);
    }
    @GetMapping("/{id}")
    public List<CartItemResponseDTO> getCartItem(@PathVariable Long id){
        return cartItemService.getCartItem(id);
    }
    @PutMapping("/{cartItem_id}")
    public String updateCartItem(@PathVariable Integer cartItem_id, @RequestBody UpdateCartDTO updateCartDT0){
        return cartItemService.updateCartItem(cartItem_id,updateCartDT0);
    }
    @DeleteMapping("/{cartItem_id}")
    public String deleteCartItem(@PathVariable Integer cartItem_id){
        return cartItemService.deleteCartItem(cartItem_id);
    }
}
