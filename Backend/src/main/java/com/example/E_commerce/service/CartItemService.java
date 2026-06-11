package com.example.E_commerce.service;

import com.example.E_commerce.dto.CartItemRequestDTO;
import com.example.E_commerce.dto.CartItemResponseDTO;
import com.example.E_commerce.dto.UpdateCartDTO;
import com.example.E_commerce.entity.Cart;
import com.example.E_commerce.entity.CartItem;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.exception.InsufficientStockException;
import com.example.E_commerce.repository.CartItemRepository;
import com.example.E_commerce.repository.CartRepository;
import com.example.E_commerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {
    public final CartItemRepository cartItemRepository;
    public final ProductRepository productRepository;
    public final CartRepository cartRepository;
    public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository,CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }
    private CartItemResponseDTO mapToResponseDTO(CartItem cartItem) {

        CartItemResponseDTO dto = new CartItemResponseDTO();

        dto.setCartItemId(cartItem.getId());

        dto.setProductName(cartItem.getProduct().getProductName());

        dto.setProductPrice(cartItem.getProduct().getPrice());

        dto.setQuantity(cartItem.getQuantity());

        dto.setTotalPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());

        return dto;
    }
    public CartItemResponseDTO addItem(CartItemRequestDTO cartItemRequestDTO){
        Cart cart=cartRepository.findByUserId(cartItemRequestDTO.getUserId())
                .orElseThrow(()->new RuntimeException("User cart not found"));
        Product product=productRepository.findById(cartItemRequestDTO.getProductId())
                .orElseThrow(()->new RuntimeException("Product not found"));
        if(product.getStock() < cartItemRequestDTO.getQuantity()) {
            throw new RuntimeException(
                    "Insufficient stock");
        }
        CartItem cartItem=new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemRequestDTO.getQuantity());
        CartItem newcartItem=cartItemRepository.save(cartItem);
        Double totalPrice=newcartItem.getProduct().getPrice() * newcartItem.getQuantity();
        return new CartItemResponseDTO(newcartItem.getId(),newcartItem.getProduct().getProductName(),newcartItem.getProduct().getPrice(),newcartItem.getQuantity(),totalPrice);
    }
    public List<CartItemResponseDTO> getCartItem(Long id){
        Cart cart=cartRepository.findByUserId(id)
                .orElseThrow(()->new RuntimeException("Cart not found"));
        List<CartItem> cartItemResponseDTOList=cartItemRepository.findByCart(cart);
        return cartItemResponseDTOList
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }
    public String updateCartItem(Integer cartItem_id, UpdateCartDTO updateCartDTO){
        CartItem cartItem=cartItemRepository.findById(cartItem_id)
                .orElseThrow(()->new RuntimeException("Cart not found"));
        Product product=cartItem.getProduct();
        if(product.getStock()<updateCartDTO.getQuantity()){
            throw new InsufficientStockException("Insufficient stock");
        }
        cartItem.setQuantity(updateCartDTO.getQuantity());
        cartItemRepository.save(cartItem);
        return "CartItem has been updated";
    }
    public String deleteCartItem(Integer cartItem_id){
        cartItemRepository.deleteById(cartItem_id);
        return "CartItem has been deleted";
    }
}
