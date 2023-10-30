package com.udacity.ecom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.ecom.entity.PurchaseHistory;
import com.udacity.ecom.repo.PurchaseHistoryRepository;
import com.udacity.ecom.service.PurchaseHistoryService;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @Override
    public List<PurchaseHistory> getPurchaseHistoryByUserId(Long userId) {
        // Retrieve the purchase history for a user by userId
        return purchaseHistoryRepository.findByUserId(userId);
    }

    @Override
    public PurchaseHistory addPurchaseToHistory(Long userId, PurchaseHistory purchase) {
        // Set the user ID in the purchase history
        purchase.setUserId(userId);

        // Save the purchase history
        return purchaseHistoryRepository.save(purchase);
    }

    // Implement other purchase history-related service methods
}
