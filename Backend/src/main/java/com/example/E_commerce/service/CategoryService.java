package com.example.E_commerce.service;

import com.example.E_commerce.dto.CategoryRequestDTO;
import com.example.E_commerce.dto.CategoryResponseDTO;
import com.example.E_commerce.entity.Category;
import com.example.E_commerce.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO addCategory(CategoryRequestDTO requestDTO) {
        Category category = new Category();
        category.setName(requestDTO.getName());

        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponseDTO((long) savedCategory.getId(), savedCategory.getName());
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryResponseDTO((long) category.getId(), category.getName()))
                .toList();
    }
    public CategoryResponseDTO getCategoryById(int id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return new CategoryResponseDTO((long) category.getId(), category.getName());
    }
    public CategoryResponseDTO updateCategory(int id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        categoryRepository.findByNameIgnoreCase(categoryRequestDTO.getName())
                .filter(existingCategory -> existingCategory.getId() != id)
                .ifPresent(existingCategory -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Category already exists. Change the request body."
                    );
                });

        category.setName(categoryRequestDTO.getName());
        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponseDTO((long) savedCategory.getId(), savedCategory.getName());
    }
    public String deleteCategory(int id){
        Category category=categoryRepository.findById(id).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
        categoryRepository.delete(category);
        return "Category has been deleted";
    }
}
