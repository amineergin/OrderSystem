package com.example.qkare.CustomerOrders.Mapper;

import com.example.qkare.CustomerOrders.Model.Dto.ProductDTO;
import com.example.qkare.CustomerOrders.Model.Entity.Product;

public class ProductMapper {
    public static ProductDTO convertEntityToDto(Product product) {
        ProductDTO productDto = new ProductDTO();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setBrand(product.getBrand());
        productDto.setStock(product.getStock());
        productDto.setCategory(product.getCategory());
        productDto.setAttributes(product.getAttributes());
        return productDto;
    }

    public static Product convertDtoToEntity(ProductDTO productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setBrand(productDto.getBrand());
        product.setStock(productDto.getStock());
        product.setCategory(productDto.getCategory());
        product.setAttributes(productDto.getAttributes());
        return product;
    }
}