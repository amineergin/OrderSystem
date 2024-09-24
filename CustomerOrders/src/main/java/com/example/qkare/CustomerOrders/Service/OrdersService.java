package com.example.qkare.CustomerOrders.Service;

import com.example.qkare.CustomerOrders.Model.Entity.Orders;
import java.util.List;

public interface OrdersService {
    Orders createOrder(Orders order);
    Integer createRandomNumber();
    List<Orders> getAllOrders();
    Orders deliverOrder(Integer orderNo);
    Orders cancelOrder(Integer orderNo);
    List<Orders> getOrdersByCustomerId(Long customerId);
}
