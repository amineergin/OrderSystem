package com.example.qkare.CustomerOrders.Controller;

import com.example.qkare.CustomerOrders.Model.Entity.Favorite;
import com.example.qkare.CustomerOrders.Service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoritesService;

    public FavoriteController(FavoriteService favoritesService){
        this.favoritesService = favoritesService;
    }

    @PostMapping("/createFavList")
    public ResponseEntity<Favorite> save(@RequestBody Favorite favorites) {
        favoritesService.save(favorites);
        return ResponseEntity.ok(favorites);
    }

    @GetMapping("/getAllFavLists")
    public List<Favorite> getAllFavLists(){
        return favoritesService.getFavs();
    }

    @GetMapping("/getFavListByCustomerId/{userId}")
    public List<Favorite> getFavListsByUserId(@PathVariable Long userId){
        return favoritesService.getFavsByUserId(userId);
    }
}
