package com.rest.rest_example.repository;

import com.rest.rest_example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    // JPA Queries
    List<Product> findByName(String name);
    Product findById(int id);
    Product findByNameAndId(String name, int id);
    List<Product> findByNameStartsWith(String prefix);
    List<Product> findByNameEndsWith(String suffix);
    List<Product> findByNameContaining(String keywords);
    List<Product> findByPriceGreaterThan(int price);
    List<Product> findByPriceLessThan(int price);
    List<Product> findByNameOrderByPriceAsc(String name);
    List<Product> findByPriceBetween(int from, int till);

    // Custom Queries

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    List<Product> getByName(String name);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.price > :price")
    int getCountProductPriceGreaterThan(int price);

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:keywords,'%')")
    List<Product> getProductByNameContaining(String keywords);


}
