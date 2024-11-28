package com.rest.rest_example.controller;

import com.rest.rest_example.model.ProductDto;
import com.rest.rest_example.model.ProductResponse;
import com.rest.rest_example.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getProductList()
    {
        return ResponseEntity.ok(productService.getAllProduct());
    }


    @GetMapping("{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") int id, @RequestParam(value = "name",required = false) String name)
    {
        return ResponseEntity.ok(ProductResponse.builder().data(productService.getAllProduct(id, name)).status(HttpStatus.OK.name()).message("Data Fetched!").build());
    }

    @Operation(summary = "Update product details product by Id!" ,
            description = "Returns the product data!",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Success!"),
                    @ApiResponse(responseCode = "404",description = "Record not found!"),
                    @ApiResponse(responseCode = "500",description = "Server error!")
            }
    )
    @PatchMapping("{id}/{price}")
    public ResponseEntity<Object> addProduct(@PathVariable("id") int id, @PathVariable("price") int price){
        return ResponseEntity.ok(ProductResponse.builder().data(productService.updatePrice(id, price)).status(HttpStatus.OK.name()).message("Data modified!").build());
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(ProductResponse.builder().data(productService.addProduct(productDto)).status(HttpStatus.OK.name()).message("Data created!").build());
    }

}
