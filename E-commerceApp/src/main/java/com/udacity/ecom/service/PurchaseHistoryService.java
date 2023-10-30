package com.udacity.ecom.service;

import java.util.List;

import com.udacity.ecom.entity.PurchaseHistory;

public interface PurchaseHistoryService {
    List<PurchaseHistory> getPurchaseHistoryByUserId(Long userId);
    PurchaseHistory addPurchaseToHistory(Long userId, PurchaseHistory purchase);
}
