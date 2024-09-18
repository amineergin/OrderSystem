package com.example.qkare.CustomerOrders.Service;

import com.example.qkare.CustomerOrders.Model.Entity.Favorites;

import java.util.List;

public interface FavoritesService {
    Favorites createFavList(Favorites favorites);
    List<Favorites> getFavs();
}
