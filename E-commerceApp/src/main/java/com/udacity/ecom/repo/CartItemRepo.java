package com.udacity.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udacity.ecom.entity.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
	
	

}
