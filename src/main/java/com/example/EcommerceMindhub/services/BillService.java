package com.example.EcommerceMindhub.services;

import com.example.EcommerceMindhub.dtos.BillDTO;
import com.example.EcommerceMindhub.models.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BillService {
    List<BillDTO> findAll();
    BillDTO getBillById(Long id);
    //BillDTO getBill(Authentication authentication);//

    ResponseEntity<Object> paymentCash(Authentication authentication);
    ResponseEntity<Object> paymentCard(Authentication authentication, String cardNumber, String cvv);




}
