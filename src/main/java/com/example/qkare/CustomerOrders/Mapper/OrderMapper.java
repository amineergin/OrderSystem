package com.example.qkare.CustomerOrders.Mapper;

import java.util.Random;

public class OrderMapper {
    public static Integer createRandomNumber() {
        Random rnd = new Random();
        return rnd.nextInt(999999);
    }
}
