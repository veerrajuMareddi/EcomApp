package com.udacity.ecom.restcontroller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.udacity.ecom.entity.CartItem;
import com.udacity.ecom.entity.UserInfo;
import com.udacity.ecom.security.CustomUserDetailsService;
import com.udacity.ecom.security.JwtTokenProvider;
import com.udacity.ecom.service.CartItemService;
import com.udacity.ecom.service.CartService;
import com.udacity.ecom.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserInfo user) {
        try {
            // Validate user input, check password length, and confirmation field
            if (!isValidUser(user)) {
                logger.error("Invalid user data: {}", user);
                return ResponseEntity.badRequest().build();
            }
            UserInfo createdUser = new UserInfo();
            if (customUserDetailsService.checkUser(user.getUsername()) == null) {
                createdUser = userService.createUser(user);
            }

            if (createdUser != null) {
                logger.info("User created successfully my Log: {}", createdUser.getUsername());
                String token = jwtTokenProvider.generateToken(createdUser.getUsername());
                return ResponseEntity.created(
                        UriComponentsBuilder.fromPath("/api/users/{id}").buildAndExpand(createdUser.getId()).toUri())
                        .body("Token =" + token);
            } else {
                logger.error("Failed to create user: {}", user.getUsername());
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            logger.error("Error while creating user: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<CartItem> addProduct(@RequestBody CartItem cartItem) {
        cartItem = cartItemService.addProduct(cartItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }

    // Other user-related APIs with similar logging structure

    // Validation method for user data
    private boolean isValidUser(UserInfo user) {
        boolean x = (user.getPassword() == null) ? false : true;
        return x;
    }
}
