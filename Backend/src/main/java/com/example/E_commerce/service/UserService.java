package com.example.E_commerce.service;

import com.example.E_commerce.dto.ProductResponseDTO;
import com.example.E_commerce.dto.UserRequestDTO;
import com.example.E_commerce.dto.UserResponseDTO;
import com.example.E_commerce.entity.Cart;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.repository.CartRepository;
import com.example.E_commerce.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    public UserService(UserRepository userRepository, CartRepository cartRepository) {

        this.userRepository = userRepository;
        this.cartRepository=cartRepository;
    }
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        User user=new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());
        User newUser=userRepository.save(user);
        Cart cart=new Cart();
        cart.setUser(newUser);
        cartRepository.save(cart);
        return new UserResponseDTO(newUser.getId(),newUser.getName(),newUser.getEmail());
    }
    public List<UserResponseDTO> getUsers(){
        return userRepository.findAll()
                .stream()
                .map(user-> new UserResponseDTO(user.getId(),user.getName(),user.getEmail()))
                .toList();
    }
    public UserResponseDTO getUserById(Long id){
        User user=userRepository.findById(id).
                orElseThrow(()->new RuntimeException("User not found"));
        return new UserResponseDTO(user.getId(),user.getName(),user.getEmail());
    }

    public UserResponseDTO updateUser(Long id,UserRequestDTO userRequestDTO){
        User user=userRepository.findById(id).
                orElseThrow(()->new RuntimeException("User not found"));
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());
        User newUser=userRepository.save(user);
        return new UserResponseDTO(newUser.getId(),newUser.getName(),newUser.getEmail());
    }
    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "User deleted";
    }
}
