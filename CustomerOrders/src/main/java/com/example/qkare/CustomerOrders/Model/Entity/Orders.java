package com.example.qkare.CustomerOrders.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "orders")
@Entity
@Data
@RequiredArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_no", unique = true)
    private Integer orderNo;

    @Column(name = "order_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDate;

    @Column(name = "delivery_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime deliveryDate;

    @Column(name = "cargo_name", columnDefinition = "VARCHAR(50)")
    private String cargoName;

    @Column(name = "is_canceled")
    private boolean isCanceled = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<OrderItem> orderItems;
}
