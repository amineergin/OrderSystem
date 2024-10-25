package com.example.qkare.CustomerOrders.Service;

import com.example.qkare.CustomerOrders.Model.Entity.Favorite;
import java.util.List;

public interface FavoriteService {
    boolean save(Favorite favorites);
    List<Favorite> getFavs();
    List<Favorite> getFavsByUserId(Long customerId);
}
