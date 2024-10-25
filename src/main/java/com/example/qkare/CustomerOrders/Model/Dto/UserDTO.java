package com.example.qkare.CustomerOrders.Model.Dto;

import com.example.qkare.CustomerOrders.Model.Entity.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
}
