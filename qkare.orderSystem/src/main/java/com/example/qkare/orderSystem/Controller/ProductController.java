package com.example.qkare.orderSystem.Controller;

import com.example.qkare.orderSystem.Dto.ProductDto;
import com.example.qkare.orderSystem.Entity.Product;
import com.example.qkare.orderSystem.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/saveProduct")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.saveProduct(productDto);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/getAllProducts")
    public List<ProductDto> getAllProducts() {
        return productService.listAllProducts();
    }

    @GetMapping("/getProductsByName/{productName}")
    public List<Product> getAllProductsByName(@PathVariable String productName) {
        return productService.listProductsByName(productName);
    }

    @DeleteMapping("/deleteProductById/{id}")
    public String deleteProductById(@PathVariable int id) {
        return productService.deleteProductById(id);
    }
}
