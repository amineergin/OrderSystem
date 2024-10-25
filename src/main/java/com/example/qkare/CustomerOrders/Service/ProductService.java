package com.example.qkare.CustomerOrders.Service;

import com.example.qkare.CustomerOrders.Model.Dto.ProductDTO;
import com.example.qkare.CustomerOrders.Model.Entity.Attributes.Category;
import com.example.qkare.CustomerOrders.Model.Entity.Product;
import java.util.List;

public interface ProductService {
    boolean save(ProductDTO productDto);

    List<Product> getAllProducts();

    Product findById(long id);

    List<Product> getProductsByCategory(Category category);
}
