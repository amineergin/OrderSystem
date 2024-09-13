package com.example.qkare.orderSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer productId;

    @Column(name = "name")
    private String productName;

    @Column(name = "description", columnDefinition = "VARCHAR(250)") //bu kısım test edilecek
    private String productDescription;

    @Column(name = "price")
    private double productPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Comment> comments;

    @ManyToMany(mappedBy = "products", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Orders> orders;
}
