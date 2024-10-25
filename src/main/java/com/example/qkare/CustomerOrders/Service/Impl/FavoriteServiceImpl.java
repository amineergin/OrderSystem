package com.example.qkare.CustomerOrders.Service.Impl;


import com.example.qkare.CustomerOrders.Exception.Exceptions;
import com.example.qkare.CustomerOrders.Exception.MessageKey;
import com.example.qkare.CustomerOrders.Model.Entity.Favorite;
import com.example.qkare.CustomerOrders.Model.Entity.Product;
import com.example.qkare.CustomerOrders.Repository.FavoriteRepository;
import com.example.qkare.CustomerOrders.Service.FavoriteService;
import com.example.qkare.CustomerOrders.Service.ProductService;
import com.example.qkare.CustomerOrders.Service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoritesRepository;
    private final UserService userService;
    private final ProductService productService;

    public FavoriteServiceImpl(FavoriteRepository favoritesRepository, UserService userService, ProductService productService) {
        this.favoritesRepository = favoritesRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public boolean save(Favorite favorite) {
        if(validateProductAndUser(favorite)){
            favoritesRepository.save(favorite);
            return true;
        }
        throw new Exceptions(MessageKey.FAV_LIST_COULD_NOT_CREATE);
    }

    @Override
    public List<Favorite> getFavs() {
        return favoritesRepository.findAll();
    }

    @Override
    public List<Favorite> getFavsByUserId(Long userId) {
        return favoritesRepository.findByUser_Id(userId);
    }

    private boolean validateProductAndUser(Favorite favorite) {
        List<Product> products =favorite.getProducts();
        for (Product product : products) {
            if(productService.findById(product.getId()) == null){
                throw new Exceptions(MessageKey.PRODUCT_NOT_FOUND, product.getId());
            }
        }
        if (userService.findById(favorite.getUser().getId()) == null){
            throw new Exceptions(MessageKey.USER_NOT_FOUND, favorite.getUser().getId());
        }
        return true;
    }
}
