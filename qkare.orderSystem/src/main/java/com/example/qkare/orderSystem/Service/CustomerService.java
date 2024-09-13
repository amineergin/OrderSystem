package com.example.qkare.orderSystem.Service;

import com.example.qkare.orderSystem.Dto.CommentDto;
import com.example.qkare.orderSystem.Dto.CustomerDto;
import com.example.qkare.orderSystem.Entity.Customer;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService {
     CustomerDto saveCustomer(CustomerDto customerDto);

     List<CustomerDto> findAllCustomers();

     Optional<Customer> findCustomerByPhone(String customerPhoneNo);

     Optional<Customer> findCustomerById(Integer customerId);

     String deleteCustomerByPhone(String customerPhoneNo);

     Customer updateCustomerAllFields(String phoneNo, CustomerDto customerDto);

     CustomerDto convertEntityToDto(Customer customer);

     List<CommentDto> getAllCommentsByCustomerId(Integer customerId);

     Customer updateCustomerSelectedFields(String phone, Map<String, Object> updatedCustomers);

}
