package com.example.qkare.CustomerOrders.Model.Entity.Attributes;

import com.example.qkare.CustomerOrders.Model.Entity.Orders;
import com.example.qkare.CustomerOrders.Model.Entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@Tag(name = "OrderItemAPI", description = "Siparişe eklenen ürünlerin miktarını tutmak için oluşturulmuş entity")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity; // Üründen kaç adet sipariş edildiğini tutar
}