package com.udacity.ecom.service;

import org.springframework.stereotype.Service;

import com.udacity.ecom.entity.CartItem;
@Service
public interface CartItemService {

	public CartItem addProduct (CartItem cartItem);
}
