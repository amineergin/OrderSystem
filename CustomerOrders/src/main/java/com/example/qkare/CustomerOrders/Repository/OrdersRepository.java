package com.example.qkare.CustomerOrders.Repository;

import com.example.qkare.CustomerOrders.Model.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByOrderNo(Integer orderNo);
    List<Orders> findByCustomerId(long customer_id);
}
