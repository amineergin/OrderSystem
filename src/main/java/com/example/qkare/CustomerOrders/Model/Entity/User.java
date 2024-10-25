package com.example.qkare.CustomerOrders.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;

@Table(name = "user_tbl")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "surname", columnDefinition = "TEXT")
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Orders> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Favorite> favorites;

}
