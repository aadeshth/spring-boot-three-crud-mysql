package com.rest.rest_example.service;

import com.rest.rest_example.entity.Product;
import com.rest.rest_example.exception.ProductNotFoundException;
import com.rest.rest_example.model.ProductDto;
import com.rest.rest_example.repository.ProductRepository;
import com.rest.rest_example.utility.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    private static final ArrayList<ProductDto> PRODUCT_DTOS = new ArrayList<>();

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //   static {
//       PRODUCT_DTOS.add(ProductDto.builder().id(1).name("Mouse").price(500).build());
//       PRODUCT_DTOS.add(ProductDto.builder().id(2).name("Pen").price(100).build());
//       PRODUCT_DTOS.add(ProductDto.builder().id(3).name("Mouse").price(1100).build());
//    }
    @Override
    public List<ProductDto> getAllProduct(){
        return PRODUCT_DTOS;
    }
    @Override
    public ProductDto getAllProduct(int id, String name) {
        if (name == null) {
            Optional<ProductDto> productOptional = PRODUCT_DTOS.stream().filter(productDto -> productDto.getId() == id).findFirst();
            if (productOptional.isPresent())
                return productOptional.get();
        } else {
            Optional<ProductDto> productOptional = PRODUCT_DTOS.stream().filter(productDto -> productDto.getId() == id && productDto.getName().equals(name)).findFirst();
            if (productOptional.isPresent())
                return productOptional.get();
        }
        return null;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
       return Conversion.productToProductDto(productRepository.save(Conversion.productDtoToEntity(productDto)));

    }

    @Override
    public ProductDto updatePrice(int id, int price) {
        Optional<ProductDto> productOptional = PRODUCT_DTOS.stream().filter(p -> p.getId() == id).findFirst();
        ProductDto productDto;
        if (productOptional.isPresent()){
            productDto = productOptional.get();
            productDto.setPrice(price);
        } else
            throw new ProductNotFoundException("Record Not Found!");
         return productDto;
    }
}
