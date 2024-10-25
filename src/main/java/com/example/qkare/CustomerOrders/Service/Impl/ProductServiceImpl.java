package com.example.qkare.CustomerOrders.Service.Impl;

import com.example.qkare.CustomerOrders.Exception.Exceptions;
import com.example.qkare.CustomerOrders.Exception.MessageKey;
import com.example.qkare.CustomerOrders.Mapper.ProductMapper;
import com.example.qkare.CustomerOrders.Model.Dto.ProductDTO;
import com.example.qkare.CustomerOrders.Model.Entity.Attributes.AttributeValidator;
import com.example.qkare.CustomerOrders.Model.Entity.Attributes.Category;
import com.example.qkare.CustomerOrders.Model.Entity.Product;
import com.example.qkare.CustomerOrders.Model.Entity.Attributes.ProductAttribute;
import com.example.qkare.CustomerOrders.Repository.ProductRepository;
import com.example.qkare.CustomerOrders.Service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean save(ProductDTO productDto) {
        for (ProductAttribute attribute : productDto.getAttributes()) {
            if (!AttributeValidator.validateAttribute(attribute.getAttributeName(), attribute.getAttributeValue(),
                    attribute.getProduct().getCategory())) {
                throw new Exceptions(MessageKey.INVALID_ATTRIBUTE_FOR_CATEGORY, attribute.getProduct().getCategory());
            }
        }
        Product createdProduct = ProductMapper.convertDtoToEntity(productDto);
        productRepository.save(createdProduct);
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new Exceptions(MessageKey.PRODUCT_NOT_FOUND, id));
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findProductsByCategory(category);
    }

    //TODO: Burada response kullanılarak product listesinde her fieldın dönmemesi sağlanmalı ve bu role göre olmalı

}
