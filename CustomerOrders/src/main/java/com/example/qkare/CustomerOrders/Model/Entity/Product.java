package com.example.qkare.CustomerOrders.Model.Entity;

import com.example.qkare.CustomerOrders.Model.Entity.Attributes.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Table(name = "products")
@Entity
@Data
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(50)")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "brand")
    private String brand;

    @Column(name = "stock")
    private int stock;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private Set<ProductAttribute> attributes;

    @ManyToMany(mappedBy = "products",cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Favorites> favorites;

    public void decreaseStock(int amount) {
        if (this.stock >= amount) {
            this.stock -= amount;
        } else {
            throw new IllegalArgumentException("Not enough stock available for product: " + this.title);
        }
    }

    public void increaseStock(int amount){
        this.stock += amount;
    }
}
