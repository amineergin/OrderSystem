package com.example.qkare.CustomerOrders.Service;

import com.example.qkare.CustomerOrders.Model.Dto.CustomerDTO;
import com.example.qkare.CustomerOrders.Model.Entity.Customer;
import com.example.qkare.CustomerOrders.Model.Entity.Orders;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    boolean validateEmail(String email, String regexPattern);
    CustomerDTO convertEntityToDto(Customer customer);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomers();
    String deleteCustomerById(Long id);
    //String deleteAllCustomers();
    Optional<Customer> findCustomerById(Long id);
    List<Orders> findAllOrdersByCustomerId(Long customerId);
}
