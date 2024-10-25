package com.example.qkare.CustomerOrders.Model.Entity;

import com.example.qkare.CustomerOrders.Exception.Exceptions;
import com.example.qkare.CustomerOrders.Exception.MessageKey;
import com.example.qkare.CustomerOrders.Model.Entity.Attributes.Category;
import com.example.qkare.CustomerOrders.Model.Entity.Attributes.ProductAttribute;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Table(name = "products")
@Entity
@Getter
@Setter
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
    @EqualsAndHashCode.Exclude //Bunu eklemeyince bir hata veriyor, bununla düzeldi.
    private Set<ProductAttribute> attributes;

    @ManyToOne
    @JoinColumn(name = "favorite_list_id")
    private Favorite favorites;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void decreaseStock(int amount) {
        if (this.stock >= amount) {
            this.stock -= amount;
        } else {
            throw new Exceptions(MessageKey.NOT_ENOUGH_STOCK, this.title);
        }
    }

    public void increaseStock(int amount){
        this.stock += amount;
    }
}
