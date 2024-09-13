package com.example.qkare.orderSystem.Service.Impl;

import com.example.qkare.orderSystem.Dto.ProductDto;
import com.example.qkare.orderSystem.Entity.Comment;
import com.example.qkare.orderSystem.Entity.Product;
import com.example.qkare.orderSystem.Repository.ProductRepository;
import com.example.qkare.orderSystem.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDto saveProduct(ProductDto productDto_) {
        Product product = new Product();
        product.setProductName(productDto_.getProductName());
        product.setProductPrice(productDto_.getPrice());
        product.setProductDescription(productDto_.getDescription());
        Product savedProduct = productRepository.save(product);
        return convertEntityToDto(savedProduct);
    }

    @Override
    public List<ProductDto> listAllProducts() {
        List<Product> productsList = productRepository.findAll();
        List<ProductDto> productDtoList = productsList.stream().map(this::convertEntityToDto).toList();
        return productDtoList;

        /*
        * return productRepository.findAll().stream()
        * .map(this::convertEntityToDto)
        * .collect(Collectors.toList());
        * */
    }

    @Override
    public List<Product> listProductsByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public String deleteProductById(Integer id) {
        Optional<Product> deletedProduct = productRepository.findById(id);
        if(deletedProduct.isPresent()){
            productRepository.delete(deletedProduct.get());
            return "Product Deleted";
        }
        return "Product Not Found!";
    }

    @Override
    public ProductDto convertEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getProductPrice());
        productDto.setDescription(product.getProductDescription());
        return productDto;
    }

    @Override
    public Product convertDtoToEntity(ProductDto productDto_) {
        Product product = new Product();
        product.setProductName(productDto_.getProductName());
        product.setProductPrice(productDto_.getPrice());
        product.setProductDescription(productDto_.getDescription());
        return product;
    }
}
