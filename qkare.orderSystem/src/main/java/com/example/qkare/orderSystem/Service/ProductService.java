package com.example.qkare.orderSystem.Service;

import com.example.qkare.orderSystem.Dto.ProductDto;
import com.example.qkare.orderSystem.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);
    ProductDto convertEntityToDto(Product product);
    Product convertDtoToEntity(ProductDto productDto);
    List<ProductDto> listAllProducts();
    List<Product> listProductsByName(String productName);
    Optional<Product> findProductById(Integer id);
    String deleteProductById(Integer id);
}
