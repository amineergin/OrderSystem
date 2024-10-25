package com.example.qkare.CustomerOrders.Service.Impl;

import com.example.qkare.CustomerOrders.Mapper.UserMapper;
import com.example.qkare.CustomerOrders.Model.Dto.UserDTO;
import com.example.qkare.CustomerOrders.Model.Entity.User;
import com.example.qkare.CustomerOrders.Repository.UserRepository;
import com.example.qkare.CustomerOrders.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    @Override
    public boolean save(UserDTO userDto) {
        userRepository.save(UserMapper.convertDtoToEntity(userDto));
        return true;
    }
}
