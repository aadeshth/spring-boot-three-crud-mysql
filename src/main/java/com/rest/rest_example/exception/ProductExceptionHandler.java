package com.rest.rest_example.exception;


import com.rest.rest_example.model.ProductResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> productNotFoundException(ProductNotFoundException e)
    {
        return new ResponseEntity<>(ProductResponse.builder().error(e.getMessage()).status(HttpStatus.NOT_FOUND.name()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> exception(RuntimeException e)
    {
        return new ResponseEntity<>(ProductResponse.builder().error(e.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR.name()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> exception(MethodArgumentNotValidException e)
    {
        List<FieldError> rr  = e.getFieldErrors();
        List<String> errors =  rr.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(ProductResponse.builder().errors(Collections.singletonList(errors)).status(HttpStatus.BAD_REQUEST.name()).build(), HttpStatus.BAD_REQUEST);
    }




}
