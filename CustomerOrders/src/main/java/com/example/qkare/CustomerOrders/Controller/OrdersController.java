package com.example.qkare.CustomerOrders.Controller;

import com.example.qkare.CustomerOrders.Model.Entity.Orders;
import com.example.qkare.CustomerOrders.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping("/createOrder")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order){
        Orders createdOrder = ordersService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/getAllOrders")
    public List<Orders> getAllOrders(){
        return ordersService.getAllOrders();
    }

    @PostMapping("/deliverOrder/{orderNo}")
    public Orders deliverOrder(@PathVariable Integer orderNo){
        return ordersService.deliverOrder(orderNo);
    }

    @PostMapping("/cancelOrder/{orderNo}")
    public Orders cancelOrder(@PathVariable Integer orderNo){
        return ordersService.cancelOrder(orderNo);
    }

    @GetMapping("/listOrdersByCustomerId/{customerId}")
    public List<Orders> listOrdersByCustomerId(@PathVariable Long customerId){
        return ordersService.getOrdersByCustomerId(customerId);
    }

    @GetMapping("/listOrdersByProductId/{productId}")
    public List<Orders> listOrdersByProductId(@PathVariable Long productId){
        return ordersService.getOrdersByProductId(productId);
    }
}
