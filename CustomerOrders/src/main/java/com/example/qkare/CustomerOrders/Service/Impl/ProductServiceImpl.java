package com.example.qkare.CustomerOrders.Service.Impl;

import com.example.qkare.CustomerOrders.Model.Dto.ProductDTO;
import com.example.qkare.CustomerOrders.Model.Entity.Attributes.AttributeValidator;
import com.example.qkare.CustomerOrders.Model.Entity.Attributes.Category;
import com.example.qkare.CustomerOrders.Model.Entity.Product;
import com.example.qkare.CustomerOrders.Model.Entity.ProductAttribute;
import com.example.qkare.CustomerOrders.Repository.ProductRepository;
import com.example.qkare.CustomerOrders.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDto) {
        Product createdProduct = new Product();
        createdProduct.setTitle(productDto.getTitle());
        createdProduct.setDescription(productDto.getDescription());
        createdProduct.setPrice(productDto.getPrice());
        createdProduct.setBrand(productDto.getBrand());
        createdProduct.setStock(productDto.getStock());
        createdProduct.setCategory(productDto.getCategory());

        for (ProductAttribute attribute : productDto.getAttributes()) {
            boolean isValid = AttributeValidator.validateAttribute(attribute.getAttributeName(), attribute.getAttributeValue(),
                    createdProduct.getCategory());
            if (!isValid) {
                throw new IllegalArgumentException("Invalid attribute for category " + createdProduct.getCategory());
            }
        }

        productRepository.save(createdProduct);
        return convertEntityToDto(createdProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findProductsByCategory(category);
    }

    @Override
    public ProductDTO convertEntityToDto(Product product) {
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
}
