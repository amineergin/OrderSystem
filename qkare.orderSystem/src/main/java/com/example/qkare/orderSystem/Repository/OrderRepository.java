package com.example.qkare.orderSystem.Repository;

import com.example.qkare.orderSystem.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Comment, Integer> {
}
