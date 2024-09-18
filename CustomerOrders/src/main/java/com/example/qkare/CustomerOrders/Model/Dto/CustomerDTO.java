package com.example.qkare.CustomerOrders.Model.Dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
}
