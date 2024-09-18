package com.example.qkare.CustomerOrders.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;


@Table(name = "customer")
@Entity
@Data
@RequiredArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "surname", columnDefinition = "TEXT")
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Orders> orders;
}
