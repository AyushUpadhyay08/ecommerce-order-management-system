package com.example.E_commerce.exception;

public class CartEmptyException extends RuntimeException{
    public CartEmptyException(String message){
        super(message);
    }
}
