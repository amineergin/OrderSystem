package com.example.qkare.orderSystem.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer commentId;

    @Column(name = "description", columnDefinition = "VARCHAR(250)")
    private String commentDescription;

    @Column(name = "date", columnDefinition = "TIMESTAMP")
    private LocalDateTime commentDate;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    @JsonManagedReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    @JsonManagedReference
    private Product product;
}
