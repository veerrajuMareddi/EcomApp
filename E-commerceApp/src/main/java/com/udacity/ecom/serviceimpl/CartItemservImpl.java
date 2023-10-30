package com.udacity.ecom.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.ecom.entity.CartItem;
import com.udacity.ecom.repo.CartItemRepo;
import com.udacity.ecom.service.CartItemService;
@Service
public class CartItemservImpl implements CartItemService{
@Autowired
private CartItemRepo cartItemRepo;
	@Override
	public CartItem addProduct(CartItem cartItem) {
		return cartItemRepo.save(cartItem);
	}

}
