package com.example.qkare.orderSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "customerTelNo")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer customerId;

    @Column(name = "name")
    private String customerName;

    @Column(name = "surname")
    private String customerSurname;

    @Column(name = "telNo", columnDefinition = "text", unique = true, nullable = false)
    private String customerTelNo;

    @Column(name = "email", unique = true, nullable = false)
    private String customerEmail;

    @Column(name = "password")
    private String customerPassword;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Orders> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Comment> comments;

}
