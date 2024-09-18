package com.example.qkare.CustomerOrders.Service.Impl;

import com.example.qkare.CustomerOrders.Model.Entity.Customer;
import com.example.qkare.CustomerOrders.Model.Entity.Favorites;
import com.example.qkare.CustomerOrders.Model.Entity.Product;
import com.example.qkare.CustomerOrders.Repository.FavoritesRepository;
import com.example.qkare.CustomerOrders.Service.CustomerService;
import com.example.qkare.CustomerOrders.Service.FavoritesService;
import com.example.qkare.CustomerOrders.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FavoritesServiceImpl implements FavoritesService {
    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private CustomerService customerService; //Optional<Customer> findCustomerById(Long id);

    @Autowired
    private ProductService productService; //Optional<Product> findById(long id);

    @Override
    public Favorites createFavList(Favorites favorites) {
        Favorites createdFavorite = new Favorites();
        createdFavorite.setListName(favorites.getListName());

        List<Product> productList =productService.getAllProducts();
        Set<Product> products = new HashSet<>();
        for(Product product: productList) {
            products.add(product);
        }
        createdFavorite.setProducts(products);

        Customer customer = customerService.findCustomerById(favorites.getCustomer().getId())
                .orElseThrow(()-> new RuntimeException("Customer not found!"));

        createdFavorite.setCustomer(customer);

        return favoritesRepository.save(createdFavorite);
    }

    @Override
    public List<Favorites> getFavs() {
        return favoritesRepository.findAll();
    }

    @Override
    public List<Favorites> getFavsByCustomerId(Long customerId) {
        return favoritesRepository.findByCustomer_id(customerId);
    }
}
