package com.example.qkare.CustomerOrders.Controller;

import com.example.qkare.CustomerOrders.Model.Entity.Orders;
import com.example.qkare.CustomerOrders.Service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/createOrder")
    public boolean createOrder(@RequestBody Orders order){
        return ordersService.save(order);
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

    @GetMapping("/listOrdersByUserId/{userId}")
    public List<Orders> listOrdersByCustomerId(@PathVariable Long userId){
        return ordersService.getOrdersByUserId(userId);
    }
}
