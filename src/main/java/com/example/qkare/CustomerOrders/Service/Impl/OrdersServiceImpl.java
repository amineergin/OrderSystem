package com.example.qkare.CustomerOrders.Service.Impl;

import com.example.qkare.CustomerOrders.Exception.Exceptions;
import com.example.qkare.CustomerOrders.Exception.MessageKey;
import com.example.qkare.CustomerOrders.Mapper.OrderMapper;
import com.example.qkare.CustomerOrders.Mapper.ProductMapper;
import com.example.qkare.CustomerOrders.Model.Entity.Attributes.OrderItem;
import com.example.qkare.CustomerOrders.Model.Entity.Orders;
import com.example.qkare.CustomerOrders.Model.Entity.Product;
import com.example.qkare.CustomerOrders.Model.Entity.User;
import com.example.qkare.CustomerOrders.Repository.OrdersRepository;
import com.example.qkare.CustomerOrders.Service.OrdersService;
import com.example.qkare.CustomerOrders.Service.ProductService;
import com.example.qkare.CustomerOrders.Service.UserService;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final ProductService productService;
    private final UserService userService;

    public OrdersServiceImpl(OrdersRepository ordersRepository, ProductService productService, UserService userService) {
        this.ordersRepository = ordersRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public boolean save(Orders order) {
        if(userService.findById(order.getUser().getId()) == null) {
            throw new Exceptions(MessageKey.USER_NOT_FOUND);
        }
        Orders createdOrder = new Orders();
        createdOrder.setOrderNo(OrderMapper.createRandomNumber());
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setCargoName(order.getCargoName());
        createdOrder.setUser(order.getUser());
        createdOrder.setOrderItems(saveOrderItem(createdOrder));
        ordersRepository.save(createdOrder);
        return true;
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders deliverOrder(Integer orderNo) {
        Orders existingOrder = ordersRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new Exceptions(MessageKey.ORDER_NOT_FOUND, orderNo));
        existingOrder.setDeliveryDate(LocalDateTime.now());
        return ordersRepository.save(existingOrder);
    }

    @Override
    public Orders cancelOrder(Integer orderNo) {
        Orders existingOrder = ordersRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new Exceptions(MessageKey.ORDER_NOT_FOUND, orderNo));

        if (!existingOrder.isCanceled() && existingOrder.getDeliveryDate() == null) {
            existingOrder.setCanceled(true);
            cancelOrderItem(existingOrder);
        }
        throw new Exceptions(MessageKey.ORDER_ALREADY_CANCELED);
    }

    @Override
    public List<Orders> getOrdersByUserId(Long userId) {
        User user = userService.findById(userId);
        return user.getOrders();
    }

    private List<OrderItem> saveOrderItem(Orders order){
        for (OrderItem orderItem : order.getOrderItems()) {
            Product productFromDb = productService.findById(orderItem.getProduct().getId());

            if (productFromDb.getStock() < orderItem.getQuantity()) {
                throw new Exceptions(MessageKey.NOT_ENOUGH_STOCK);
            }
            productFromDb.decreaseStock(orderItem.getQuantity());
            productService.save(ProductMapper.convertEntityToDto(productFromDb));
        }
        return order.getOrderItems();
    }

    private void cancelOrderItem(Orders order) {
         for (OrderItem orderItem : order.getOrderItems()) {
                Product product = orderItem.getProduct();
                int quantity = orderItem.getQuantity();
                product.increaseStock(quantity);
                productService.save(ProductMapper.convertEntityToDto(product));
            }
        ordersRepository.save(order);
    }

}