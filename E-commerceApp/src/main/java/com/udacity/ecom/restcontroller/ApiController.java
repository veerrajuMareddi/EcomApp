package com.udacity.ecom.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.ecom.entity.CartData;
import com.udacity.ecom.entity.CartDataDomain;
import com.udacity.ecom.entity.CartItem;
import com.udacity.ecom.entity.OrderData;
import com.udacity.ecom.entity.PurchaseHistory;
import com.udacity.ecom.entity.UserInfo;
import com.udacity.ecom.service.CartItemService;
import com.udacity.ecom.service.CartService;
import com.udacity.ecom.service.OrderService;
import com.udacity.ecom.service.PurchaseHistoryService;
import com.udacity.ecom.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private PurchaseHistoryService purchaseHistoryService;

	// Create User API
	@PostMapping("/create-user")
	public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo user) {
		try {
			UserInfo createdUser = userService.createUser(user);
			if (createdUser != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Place Order API
	@PostMapping("/place-order")
	public ResponseEntity<OrderData> placeOrder(@RequestBody OrderData order) {
		try {
			OrderData placedOrder = orderService.placeOrder(order);
			if (placedOrder != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(placedOrder);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/view-orderhistory/{userId}")
	public ResponseEntity<List<OrderData>> viewOrderHistory(@PathVariable Long userId) {
		try {
			List<OrderData> ordersData = orderService.getOrdersByUserId(userId);
			if (ordersData != null) {
				return ResponseEntity.ok(ordersData);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	// View Cart API
	@GetMapping("/view-cart/{userId}")
	public ResponseEntity<CartData> viewCart(@PathVariable Long userId) {
		try {
			CartData userCart = cartService.getCartByUserId(userId);
			if (userCart != null) {
				return ResponseEntity.ok(userCart);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Purchase History API
	@GetMapping("/purchase-history/{userId}")
	public ResponseEntity<List<PurchaseHistory>> purchaseHistory(@PathVariable Long userId) {
		try {
			List<PurchaseHistory> history = purchaseHistoryService.getPurchaseHistoryByUserId(userId);
			return ResponseEntity.ok(history);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Add Products to Cart API
	@PostMapping("/add-to-cart/{userId}")
	public ResponseEntity<CartData> addToCart(@PathVariable Long userId, @RequestBody CartDataDomain cartItem) {
		try {
			// Retrieve the user's cart by userId
			CartData userCart = cartService.getCartByUserId(userId);

			if (userCart == null) {
				// Create a new cart for the user if it doesn't exist
				userCart = new CartData();
				userCart.setUserId(userId);
			}
			CartData updatedCart = new CartData();
			for (Long id : cartItem.getCartItemId()) {
				userCart.setCartItemId(id);
				updatedCart = cartService.saveCart(userCart);

			}

			return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	

}
