package com.example.qkare.CustomerOrders.Repository;

import com.example.qkare.CustomerOrders.Model.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsById(Long id);
}
