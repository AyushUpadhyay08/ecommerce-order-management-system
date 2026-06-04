package com.example.E_commerce.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequestDTO {
    @NotBlank(message="Category cannot be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
