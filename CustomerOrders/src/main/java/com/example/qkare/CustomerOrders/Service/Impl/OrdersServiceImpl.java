package com.example.qkare.CustomerOrders.Service.Impl;

import com.example.qkare.CustomerOrders.Model.Entity.Customer;
import com.example.qkare.CustomerOrders.Model.Entity.Orders;
import com.example.qkare.CustomerOrders.Model.Entity.Product;
import com.example.qkare.CustomerOrders.Repository.OrdersRepository;
import com.example.qkare.CustomerOrders.Repository.ProductRepository;
import com.example.qkare.CustomerOrders.Service.CustomerService;
import com.example.qkare.CustomerOrders.Service.OrdersService;
import com.example.qkare.CustomerOrders.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Orders createOrder(Orders order) {
        Customer customer = customerService.findCustomerById(order.getCustomer().getId())
                .orElseThrow(()-> new RuntimeException("Customer not found!"));
        Orders createdOrder = new Orders();
        createdOrder.setOrderNo(createRandomNumber());
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setCargoName(order.getCargoName());
        createdOrder.setCustomer(customer);

        // Mevcut ürünleri veritabanından bul ve order'a ekle
        Set<Product> products =new HashSet<>();
        for(Product product : products){
            Product existingProduct = productService.findById(product.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found!"));
            products.add(existingProduct);
        }
        createdOrder.setProducts(products);

        return ordersRepository.save(createdOrder);
    }

    @Override
    public Integer createRandomNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return number;
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders deliverOrder(Integer orderNo) {
        Orders existingOrder = ordersRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        existingOrder.setDeliveryDate(LocalDateTime.now());
        return ordersRepository.save(existingOrder);
    }

    @Override
    public Orders cancelOrder(Integer orderNo) {
        Orders existingOrder = ordersRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        if(existingOrder.getDeliveryDate() == null) {
            existingOrder.setCanceled(true);
            return ordersRepository.save(existingOrder);
        }return null;
    }

    @Override
    public List<Orders> getOrdersByCustomerId(Long customerId) {
        Customer existingCustomer = customerService.findCustomerById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
        return ordersRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Orders> getOrdersByProductId(Long productId) {
        Product existingProduct = productService.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        return ordersRepository.findByproductsId(productId);
    }
}
