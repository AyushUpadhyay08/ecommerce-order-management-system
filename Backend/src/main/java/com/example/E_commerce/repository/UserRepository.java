package com.example.E_commerce.repository;

import com.example.E_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByNameIgnoreCase(String name);
}
