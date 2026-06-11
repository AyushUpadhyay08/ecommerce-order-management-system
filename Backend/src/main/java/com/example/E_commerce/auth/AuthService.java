package com.example.E_commerce.auth;

import com.example.E_commerce.dto.UserResponseDTO;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.enums.Role;
import com.example.E_commerce.repository.UserRepository;
import com.example.E_commerce.security.JWTUtil;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    // Use an internal BCryptPasswordEncoder so no additional bean/config is required here.
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }
    public UserResponseDTO register(RegisterRequestDTO registerRequestDTO){
        User user=new User();
        user.setName(registerRequestDTO.getName());
        // store hashed password
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setEmail(registerRequestDTO.getEmail());
        user.setRole(Role.CUSTOMER);
        User savedUser=userRepository.save(user);
        return new UserResponseDTO(savedUser.getId(),savedUser.getName(),savedUser.getEmail());
    }
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        User user=userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(()->new RuntimeException("User not found"));
        String rawPassword = loginRequestDTO.getPassword();
        String storedPassword = user.getPassword();
        boolean matches = false;
        if(storedPassword != null && !storedPassword.isEmpty()){
            if(storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$") || storedPassword.startsWith("$2y$")){
                matches = passwordEncoder.matches(rawPassword, storedPassword);
            } else {
                // legacy plaintext password - upgrade to bcrypt on successful login
                if(storedPassword.equals(rawPassword)){
                    user.setPassword(passwordEncoder.encode(rawPassword));
                    userRepository.save(user);
                    matches = true;
                }
            }
        }
        if(!matches){
            throw new RuntimeException("Wrong password");
        }
        String token=jwtUtil.generateToken(user.getEmail());
        return new LoginResponseDTO(token);
    }
}
