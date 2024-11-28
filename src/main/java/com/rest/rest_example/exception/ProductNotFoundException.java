package com.rest.rest_example.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){
       super(message);
    }
}
