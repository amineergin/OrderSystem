package com.example.qkare.orderSystem.Repository;

import com.example.qkare.orderSystem.Dto.ProductDto;
import com.example.qkare.orderSystem.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findByProductName(String productName);
}

