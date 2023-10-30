package com.udacity.ecom.service;

import java.util.List;

import com.udacity.ecom.entity.OrderData;

public interface OrderService {
    OrderData placeOrder(OrderData order);
    List<OrderData> getOrdersByUserId(Long userId);
}
