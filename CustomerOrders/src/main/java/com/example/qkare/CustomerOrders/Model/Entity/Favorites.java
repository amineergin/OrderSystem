package com.example.qkare.CustomerOrders.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
@RequiredArgsConstructor
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "list_name", columnDefinition = "VARCHAR(50)")
    private String listName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Customer customer;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "fav_products",
            joinColumns = {@JoinColumn(name = "fav_list_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
    )
    Set<Product> products = new HashSet<>();
}
