package com.example.E_commerce.service;

import com.example.E_commerce.dto.ProductRequestDTO;
import com.example.E_commerce.dto.ProductResponseDTO;
import com.example.E_commerce.entity.Category;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.CategoryRepository;
import com.example.E_commerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository=productRepository;
        this.categoryRepository = categoryRepository;
    }
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO){
        Category category=categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        Product product=new Product();
        product.setProductName(productRequestDTO.getProductName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setStock(productRequestDTO.getStock());
        product.setCategory(category);
        Product saveProduct=productRepository.save(product);
        return new ProductResponseDTO(saveProduct.getId(),saveProduct.getProductName(),saveProduct.getPrice(),saveProduct.getCategory().getName());
    }
    public List<ProductResponseDTO> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponseDTO(product.getId(),product.getProductName(),product.getPrice(),product.getCategory().getName()))
                .toList();
    }
    public ProductResponseDTO getProductById(int id){
        Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found"));
        return new ProductResponseDTO(product.getId(),product.getProductName(),product.getPrice(),product.getCategory().getName());
    }
    public ProductResponseDTO updateProduct(int id,ProductRequestDTO productRequestDTO){
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found"));
        Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found"));
        product.setProductName(productRequestDTO.getProductName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setStock(productRequestDTO.getStock());
        product.setCategory(category);
        Product saveProduct=productRepository.save(product);
        return new ProductResponseDTO(saveProduct.getId(),saveProduct.getProductName(),saveProduct.getPrice(),saveProduct.getCategory().getName());
    }
    public String deleteProduct(int id){
        productRepository.deleteById(id);
        return "Product deleted";
    }
}
