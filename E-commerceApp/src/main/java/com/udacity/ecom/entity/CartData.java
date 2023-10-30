package com.udacity.ecom.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CartData {
	



	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    private Long userId; 
    
   private Long cartItemId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}
  
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
    
}
