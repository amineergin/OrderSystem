package com.example.qkare.CustomerOrders.Repository;

import com.example.qkare.CustomerOrders.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
