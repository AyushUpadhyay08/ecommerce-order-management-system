package com.example.E_commerce.dto;

import com.example.E_commerce.enums.Role;

public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
    public void setName(String name){
        this.name=name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setRole(Role role){
        this.role=role;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public Role getRole(){
        return this.role;
    }
}
