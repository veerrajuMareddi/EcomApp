package com.udacity.ecom.serviceimpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.ecom.entity.CartData;
import com.udacity.ecom.entity.CartItem;
import com.udacity.ecom.repo.CartRepository;
import com.udacity.ecom.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartData getCartByUserId(Long userId) {
        // Implement logic to retrieve a user's cart by their ID
        return cartRepository.findByUserId(userId);
    }

    @Override
    public CartData addItemToCart(Long userId, CartItem cartItem) {
//        // Implement logic to add a cart item to the user's cart, including saving to the database
//        CartData cart = getCartByUserId(userId);
//        if (cart == null) {
//            cart = new CartData();
//            cart.setUserId(userId);
//        }
//        cart.setCartItemId(Arrays.asList(cartItem.getId()) );
//        return cartRepository.save(cart);
    	return null;
    }

	@Override
	public CartData saveCart(CartData userCart) {
		return cartRepository.save(userCart);
	}

}
