package com.example.qkare.CustomerOrders.Repository;

import com.example.qkare.CustomerOrders.Model.Entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser_Id(long user_id);
}
