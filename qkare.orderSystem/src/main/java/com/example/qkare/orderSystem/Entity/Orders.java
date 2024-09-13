package com.example.qkare.orderSystem.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "orders")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer orderId;

    @Column(columnDefinition = "VARCHAR(50)")
    private String orderNo; //#9453128079 gibi sipariş numarası, otomatik üretilecek

    @Column(columnDefinition = "TIMESTAMP") //Sipariş oluştur fonksiyonu çalıştırıldığında sistem tarihi alınacak
    private LocalDateTime orderDate;

    @Column(columnDefinition = "TIMESTAMP") //Sipariş teslim et fonksiyonu çalıştırıldığında sistem tarihi alınacak
    private LocalDateTime deliveryDate;

    @Column(name = "cargoName", columnDefinition = "VARCHAR(50)")
    private String orderCargoName;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    @JsonManagedReference
    private Customer customer;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_product",
            joinColumns = {@JoinColumn(name = "orderId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "productId", referencedColumnName = "id")}
    )
    private Set<Product> products;
}
