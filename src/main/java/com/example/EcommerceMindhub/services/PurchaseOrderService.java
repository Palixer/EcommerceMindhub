package com.example.EcommerceMindhub.services;

import com.example.EcommerceMindhub.dtos.PurchaseOrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseOrderDTO> findAll();
    PurchaseOrderDTO getPurchaseOrderById(Long id);

    ResponseEntity<Object> createPurchaseOrder(String name, Integer quantity, Authentication authentication);

    ResponseEntity<Object> deletePurchaseOrder(Long id);

    ResponseEntity<Object> purchaseReminder(Authentication authentication);


}


