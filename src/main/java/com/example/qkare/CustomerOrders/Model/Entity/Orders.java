package com.example.qkare.CustomerOrders.Model.Entity;

import com.example.qkare.CustomerOrders.Model.Entity.Attributes.OrderItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
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
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
}
