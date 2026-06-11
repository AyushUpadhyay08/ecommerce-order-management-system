package com.example.E_commerce.auth;

import com.example.E_commerce.dto.UserResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody RegisterRequestDTO registerRequestDTO){
        return authService.register(registerRequestDTO);
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        return authService.login(loginRequestDTO);
    }
}
