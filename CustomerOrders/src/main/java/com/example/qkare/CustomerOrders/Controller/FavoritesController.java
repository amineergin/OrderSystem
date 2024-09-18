package com.example.qkare.CustomerOrders.Controller;

import com.example.qkare.CustomerOrders.Model.Entity.Favorites;
import com.example.qkare.CustomerOrders.Service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {
    @Autowired
    private FavoritesService favoritesService;

    @PostMapping("/createFavList")
    public ResponseEntity<Favorites> createFavList(@RequestBody Favorites favorites) {
        Favorites savedFavorites = favoritesService.createFavList(favorites);
        return ResponseEntity.ok(savedFavorites);
    }

    @GetMapping("/getAllFavLists")
    public List<Favorites> getAllFavLists(){
        return favoritesService.getFavs();
    }

    @GetMapping("/getFavListByCustomerId/{customerId}")
    public List<Favorites> getFavListsByCustomerId(@PathVariable Long customerId){
        return favoritesService.getFavsByCustomerId(customerId);
    }
}
