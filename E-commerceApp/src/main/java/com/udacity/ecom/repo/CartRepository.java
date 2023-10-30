package com.udacity.ecom.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.ecom.entity.CartData;
import com.udacity.ecom.entity.CartItem;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartData, Long> {
    CartData findByUserId(Long userId);
}
