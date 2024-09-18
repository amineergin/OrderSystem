package com.example.qkare.CustomerOrders.Controller;

import com.example.qkare.CustomerOrders.Model.Dto.CustomerDTO;
import com.example.qkare.CustomerOrders.Model.Entity.Customer;
import com.example.qkare.CustomerOrders.Model.Entity.Orders;
import com.example.qkare.CustomerOrders.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/listAllCustomers")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDto){
        CustomerDTO createdCustomerDto = customerService.createCustomer(customerDto);
        return ResponseEntity.ok(createdCustomerDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomerById(@PathVariable("id") Long id){
        return customerService.deleteCustomerById(id);
    }
    
    /*@DeleteMapping("/deleteAll")
    public String deleteAllCustomers(){
        return customerService.deleteAllCustomers();
    }*/

    @GetMapping("/getOrders/{customerId}")
    public List<Orders> findOrdersByCustomerId(@PathVariable Long customerId){
        return customerService.findAllOrdersByCustomerId(customerId);
    }
}
