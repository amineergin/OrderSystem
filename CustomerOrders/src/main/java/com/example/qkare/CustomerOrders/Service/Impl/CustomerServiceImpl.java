package com.example.qkare.CustomerOrders.Service.Impl;

import com.example.qkare.CustomerOrders.Model.Dto.CustomerDTO;
import com.example.qkare.CustomerOrders.Model.Entity.Customer;
import com.example.qkare.CustomerOrders.Model.Entity.Orders;
import com.example.qkare.CustomerOrders.Repository.CustomerRepository;
import com.example.qkare.CustomerOrders.Service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean validateEmail(String email, String regexPattern) {
        try {
            return Pattern.compile(regexPattern).matcher(email).matches();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CustomerDTO convertEntityToDto(Customer customer) {
        CustomerDTO customerDto = new CustomerDTO();
        String regexPattern =  "(?=^.{4,40}$)[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+.[a-zA-Z]{2,4}$";
        if(validateEmail(customer.getEmail(), regexPattern)){
            customerDto.setId(customer.getId());
            customerDto.setName(customer.getName());
            customerDto.setSurname(customer.getSurname());
            customerDto.setPassword(customer.getPassword());
            customerDto.setEmail(customer.getEmail());
            return customerDto;
        }
        return null;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        customerRepository.save(customer);
        return convertEntityToDto(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String deleteCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isPresent()){
            customerRepository.deleteById(id);
            return "Customer deleted!";
        }else{
            return "Customer couldn't delete!";
        }
    }

    /*@Override
    public String deleteAllCustomers() {
        customerRepository.deleteAll();
        return "All customer deleted!";
    }*/

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Orders> findAllOrdersByCustomerId(Long customerId) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
        return existingCustomer.getOrders();
    }
}
