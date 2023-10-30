package com.udacity.ecom.entity;
import java.util.List;


public class CartDataDomain {
 

	
    private Long id;
        
    private Long userId; 
    
   private List<Long> cartItemId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	   public List<Long> getCartItemId() {
			return cartItemId;
		}

		public void setCartItemId(List<Long> cartItemId) {
			this.cartItemId = cartItemId;
		}
    
}
