package com.example.qkare.orderSystem.Repository;

import com.example.qkare.orderSystem.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByCustomerTelNo(String customerTelNo);
    Optional<Customer> findByCustomerEmail(String customerEmail);
}
