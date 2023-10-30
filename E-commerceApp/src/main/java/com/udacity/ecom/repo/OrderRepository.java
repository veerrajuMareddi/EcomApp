package com.udacity.ecom.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.ecom.entity.OrderData;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderData, Long> {
    List<OrderData> findByUserId(Long userId);
}
