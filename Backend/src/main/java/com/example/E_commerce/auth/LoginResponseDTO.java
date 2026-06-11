package com.example.E_commerce.auth;

public class LoginResponseDTO {

    String token;
    public LoginResponseDTO(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
