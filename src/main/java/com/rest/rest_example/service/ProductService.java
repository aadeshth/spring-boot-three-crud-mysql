package com.rest.rest_example.service;

import com.rest.rest_example.model.ProductDto;
import java.util.*;
public interface ProductService {
    List<ProductDto> getAllProduct();
    ProductDto getAllProduct(int id, String name);
   ProductDto addProduct(ProductDto productDto);
    ProductDto updatePrice(int id, int price);
}
