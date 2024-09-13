package com.example.qkare.orderSystem.Controller;

import com.example.qkare.orderSystem.Dto.CommentDto;
import com.example.qkare.orderSystem.Dto.CustomerDto;
import com.example.qkare.orderSystem.Entity.Customer;
import com.example.qkare.orderSystem.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/saveCustomer")
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto){
        return customerService.saveCustomer(customerDto);
    }

    @GetMapping("/getAllCustomer")
    public List<CustomerDto> getAllCustomer(){
        return customerService.findAllCustomers();
    }

    @GetMapping("/{customerPhoneNo}")
    public Optional<Customer> getCustomerByPhone(@PathVariable String customerPhoneNo){
        return customerService.findCustomerByPhone(customerPhoneNo);
    }

    @GetMapping("/getCommentsById/{customerId}")
    public List<CommentDto> getCommentsByCustomerId(@PathVariable Integer customerId){
        return customerService.getAllCommentsByCustomerId(customerId);
    }

    @DeleteMapping("/{customerPhoneNo}")
    public String deleterCustomerByPhone(@PathVariable String customerPhoneNo){
        return customerService.deleteCustomerByPhone(customerPhoneNo);
    }

    @PutMapping("/{customerPhoneNo}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerPhoneNo, @RequestBody CustomerDto customerDto){
        Customer updatedCustomer = customerService.updateCustomerAllFields(customerPhoneNo, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PatchMapping("/patchs/{customerPhone}")
    public ResponseEntity<CustomerDto> updateCustomerSelectedField(@PathVariable String customerPhone, @RequestBody Map<String, Object> updates){
        Customer updatedCustomer = customerService.updateCustomerSelectedFields(customerPhone, updates);
        CustomerDto customerDto = customerService.convertEntityToDto(updatedCustomer);
        return ResponseEntity.ok(customerDto);
    }
}
