package com.example.qkare.CustomerOrders.Mapper;

import com.example.qkare.CustomerOrders.Exception.Exceptions;
import com.example.qkare.CustomerOrders.Exception.MessageKey;
import com.example.qkare.CustomerOrders.Model.Dto.UserDTO;
import com.example.qkare.CustomerOrders.Model.Entity.User;
import java.util.regex.Pattern;

public class UserMapper {
    public static User convertDtoToEntity(UserDTO userDto){
        String regexEmail = "(?=^.{4,40}$)[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+.[a-zA-Z]{2,4}$";
        if(validateEmail(userDto.getEmail(), regexEmail)){
            User user = new User();
            user.setId(userDto.getId());
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());
            return user;
        }else{
            throw new Exceptions(MessageKey.EMAIL_REGEX_EXCEPTION);
        }
    }

    public static boolean validateEmail(String email, String regexPattern){
        try {
            return Pattern.compile(regexPattern).matcher(email).matches();
        } catch (Exception e) {
            return false;
        }
    }

}
