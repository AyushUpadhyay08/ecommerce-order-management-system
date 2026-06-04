package com.example.E_commerce.controller;

import com.example.E_commerce.dto.CategoryRequestDTO;
import com.example.E_commerce.dto.CategoryResponseDTO;
import jakarta.validation.Valid;
import com.example.E_commerce.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponseDTO addCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.addCategory(categoryRequestDTO);
    }
    @GetMapping
    public List<CategoryResponseDTO> getAllCategories(){

        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }
    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(@PathVariable int id,
                                              @Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.updateCategory(id, categoryRequestDTO);
    }
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id){
        return categoryService.deleteCategory(id);
    }
}
