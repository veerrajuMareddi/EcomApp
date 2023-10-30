package com.udacity.ecom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.ecom.entity.CartItem;
import com.udacity.ecom.entity.OrderData;
import com.udacity.ecom.repo.CartItemRepo;
import com.udacity.ecom.repo.OrderRepository;
import com.udacity.ecom.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CartItemRepo cartItemRepo;

    @Override
    public OrderData placeOrder(OrderData order) {
    	
    	CartItem cartItem=order.getCartItems().get(0);
    	
    	OrderData data= orderRepository.save(order);
    	cartItem.setOrder(data);
    	cartItemRepo.save(cartItem);
    	
        return data;
    }

    @Override
    public List<OrderData> getOrdersByUserId(Long userId) {
        // Implement logic to retrieve orders for a user by their ID
        return orderRepository.findByUserId(userId);
    }

    // Implement other order-related service methods
}
