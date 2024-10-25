package com.example.qkare.CustomerOrders.Service;

import com.example.qkare.CustomerOrders.Model.Dto.UserDTO;
import com.example.qkare.CustomerOrders.Model.Entity.User;

public interface UserService {
    User findById(Long id);

    boolean save(UserDTO userDto);


}
