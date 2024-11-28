package com.rest.rest_example.utility;

import com.rest.rest_example.entity.Product;
import com.rest.rest_example.model.ProductDto;

public class Conversion {

    public static Product productDtoToEntity(ProductDto productDto){
        return Product.builder().name(productDto.getName()).price(productDto.getPrice()).build();
    }
    public static ProductDto productToProductDto(Product product){
        return ProductDto.builder().id(product.getId()).name(product.getName()).price(product.getPrice()).build();
    }

}
