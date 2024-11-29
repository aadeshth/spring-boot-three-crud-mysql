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

    @Override
    public List<ProductDto> getProductAll(){
        return getProductDtos(productRepository.findAll());
    }
    @Override
    public ProductDto getProductByIdAndName(int id, String name) {

        if (name == null) {
            Optional<ProductDto> productOptional = getProductAll().stream().filter(productDto -> productDto.getId() == id).findFirst();
            if (productOptional.isPresent())
                return productOptional.get();
        } else {
            Optional<ProductDto> productOptional = getProductAll().stream().filter(productDto -> productDto.getId() == id && productDto.getName().equals(name)).findFirst();
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

    @Override
    public List<ProductDto> getProductByName(String name) {
       return getProductDtos(productRepository.findByName(name));
    }

    private List<ProductDto> getProductDtos(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p : products)
            productDtos.add(Conversion.productToProductDto(p));
        return productDtos;
    }
}
