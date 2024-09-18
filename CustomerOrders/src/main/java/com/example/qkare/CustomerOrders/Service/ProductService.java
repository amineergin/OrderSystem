package com.example.qkare.CustomerOrders.Service;

import com.example.qkare.CustomerOrders.Model.Dto.ProductDTO;
import com.example.qkare.CustomerOrders.Model.Entity.Attributes.Category;
import com.example.qkare.CustomerOrders.Model.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDto);
    List<Product> getAllProducts();
    ProductDTO convertEntityToDto(Product product);
    Optional<Product> findById(long id);
    List<Product> getProductsByCategory(Category category);
}
