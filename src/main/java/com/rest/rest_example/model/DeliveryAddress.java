package com.rest.rest_example.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DeliveryAddress {
    @NotNull(message = "Id should not be null")
    private int id;
    @NotBlank(message = "Country is required!")
    String country;
    @NotBlank(message = "City is required!")
    String city;
    String street;
    @NotBlank(message = "ZipCode is required!")
    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$", message = "ZipCode must be number and 5 digitd!")
    String zipCode;
    int phoneNumber;
}
