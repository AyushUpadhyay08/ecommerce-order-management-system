package com.example.E_commerce.controller;

import com.example.E_commerce.dto.ProductRequestDTO;
import com.example.E_commerce.dto.ProductResponseDTO;
import com.example.E_commerce.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("products")

public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @PostMapping
    public ProductResponseDTO addProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return productService.addProduct(productRequestDTO);
    }
    @GetMapping
    public List<ProductResponseDTO> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }
    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Integer id, @RequestBody ProductRequestDTO productRequestDTO){
        return productService.updateProduct(id,productRequestDTO);
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }

}
