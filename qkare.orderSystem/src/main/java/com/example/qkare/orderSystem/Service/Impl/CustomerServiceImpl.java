package com.example.qkare.orderSystem.Service.Impl;

import com.example.qkare.orderSystem.Dto.CommentDto;
import com.example.qkare.orderSystem.Dto.CustomerDto;
import com.example.qkare.orderSystem.Entity.Comment;
import com.example.qkare.orderSystem.Entity.Customer;
import com.example.qkare.orderSystem.Repository.CustomerRepository;
import com.example.qkare.orderSystem.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Optional<Customer> existingCustomerPhone = customerRepository.findByCustomerTelNo(customerDto.getPhone());
        Optional<Customer> existingCustomerEmail = customerRepository.findByCustomerEmail(customerDto.getEmail());

        if(existingCustomerPhone.isPresent()){
            System.out.println("This customer is already exist!" + customerDto.getPhone());
            return null;
        }else if(existingCustomerEmail.isPresent()){
            System.out.println("This customer is already exist!" + customerDto.getEmail());
            return null;
        }else{
            Customer newCustomer = new Customer();
            newCustomer.setCustomerName(customerDto.getName());
            newCustomer.setCustomerSurname(customerDto.getSurname());
            newCustomer.setCustomerEmail(customerDto.getEmail());
            newCustomer.setCustomerTelNo(customerDto.getPhone());
            newCustomer.setCustomerPassword(customerDto.getPassword());
            Customer savedCustomer = customerRepository.save(newCustomer);
            return convertEntityToDto(savedCustomer);
        }
    }

    @Override
    public List<CustomerDto> findAllCustomers(){
        return customerRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findCustomerByPhone(String customerPhoneNo) {
        return customerRepository.findByCustomerTelNo(customerPhoneNo);
    }

    @Override
    public Optional<Customer> findCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public String deleteCustomerByPhone(String customerPhoneNo) {
        Optional<Customer> deletedCustomer = customerRepository.findByCustomerTelNo(customerPhoneNo);
        if(deletedCustomer.isPresent()){
            customerRepository.delete(deletedCustomer.get());
            return "Customer deleted!";
        }else{
            return "Customer not found!";
        }
    }

    @Override
    public Customer updateCustomerAllFields(String phoneNo, CustomerDto customerDto) {
        Optional<Customer> existingCustomer = customerRepository.findByCustomerTelNo(phoneNo);
        if(existingCustomer.isPresent()){
            Customer customerToUpdate = existingCustomer.get();

            customerToUpdate.setCustomerName(customerDto.getName());
            customerToUpdate.setCustomerSurname(customerDto.getSurname());
            customerToUpdate.setCustomerEmail(customerDto.getEmail());
            customerToUpdate.setCustomerTelNo(customerDto.getPhone());
            customerToUpdate.setCustomerPassword(customerDto.getPassword());

            return customerRepository.save(customerToUpdate);
        }
        else{
            throw new RuntimeException("Customer not found!" + phoneNo);
        }
    }

    @Override
    public List<CommentDto> getAllCommentsByCustomerId(Integer customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer customer_ = customer.get();
            return customer_.getComments().stream()
                    .map(this::convertEntityToCommentDto)
                    .collect(Collectors.toList());
        }else{
            System.out.println("Customer not found!" + customerId);
            return null;
        }
    }

    private CommentDto convertEntityToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(comment.getCommentDescription());
        commentDto.setCustomerId(comment.getCustomer().getCustomerId());
        commentDto.setProductId(comment.getProduct().getProductId());
        return commentDto;
    }

    @Override
    public CustomerDto convertEntityToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getCustomerName());
        customerDto.setSurname(customer.getCustomerSurname());
        customerDto.setEmail(customer.getCustomerEmail());
        customerDto.setPhone(customer.getCustomerTelNo());
        customerDto.setPassword(customer.getCustomerPassword());
        return customerDto;
    }

    @Override
    public Customer updateCustomerSelectedFields(String phone, Map<String, Object> updatedCustomer) {
        Customer existingCustomer = customerRepository.findByCustomerTelNo(phone)
                .orElseThrow(()-> new RuntimeException("Customer not found!"));
        if(updatedCustomer.containsKey("name")){
            existingCustomer.setCustomerName((String) updatedCustomer.get("name").toString());
        }
        if(updatedCustomer.containsKey("surname")){
            existingCustomer.setCustomerSurname((String) updatedCustomer.get("surname").toString());
        }
        if(updatedCustomer.containsKey("email")){
            existingCustomer.setCustomerEmail((String) updatedCustomer.get("email").toString());
        }
        if(updatedCustomer.containsKey("phone")){
            existingCustomer.setCustomerTelNo((String) updatedCustomer.get("phone").toString());
        }
        if(updatedCustomer.containsKey("password")){
            existingCustomer.setCustomerPassword((String) updatedCustomer.get("password").toString());
        }
        return customerRepository.save(existingCustomer);
    }
}
