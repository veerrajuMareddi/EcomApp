package com.udacity.ecom.service;

import com.udacity.ecom.entity.CartData;
import com.udacity.ecom.entity.CartItem;

public interface CartService {
    CartData getCartByUserId(Long userId);
    CartData addItemToCart(Long userId, CartItem cartItem);
	CartData saveCart(CartData userCart);
}
