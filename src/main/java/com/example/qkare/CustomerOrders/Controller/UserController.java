package com.example.qkare.CustomerOrders.Controller;

import com.example.qkare.CustomerOrders.Model.Dto.UserDTO;
import com.example.qkare.CustomerOrders.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public boolean save(@RequestBody UserDTO userDto){
        return userService.save(userDto);
    }
}
