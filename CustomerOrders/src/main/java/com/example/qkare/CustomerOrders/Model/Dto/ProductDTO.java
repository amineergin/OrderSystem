package com.example.qkare.CustomerOrders.Model.Dto;

import com.example.qkare.CustomerOrders.Model.Entity.Attributes.Category;
import com.example.qkare.CustomerOrders.Model.Entity.ProductAttribute;
import lombok.Data;

import java.util.Set;

@Data
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String brand;
    private int stock;
    private Category category;
    private Set<ProductAttribute> attributes;
}
