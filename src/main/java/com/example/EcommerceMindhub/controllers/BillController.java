package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.BillDTO;
import com.example.EcommerceMindhub.models.*;
import com.example.EcommerceMindhub.repositories.*;
import com.example.EcommerceMindhub.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BillController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PurchaseOrRepository purchaseOrRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BillService billService;


    @GetMapping("/bills")
    public List<BillDTO> findAll() {
        return billService.findAll();
    }

    @GetMapping("/bills/{id}")
    public BillDTO getBillById(@PathVariable Long id) {

        return billService.getBillById(id);
    }

    @PostMapping("/shoppingCart/current/bills/payment/cash")
    public ResponseEntity<Object> paymentCash(Authentication authentication) {
    return billService.paymentCash(authentication);

    }

    @PostMapping("/shoppingCart/current/bills/payment/card")
    public  ResponseEntity<Object> paymentCard (Authentication authentication,
                                                @RequestParam String cardNumber,
                                                @RequestParam String cvv) {

        if (cardNumber.isEmpty() || cvv.isEmpty()) {
            return new ResponseEntity<>("Los datos estan vacios.", HttpStatus.FORBIDDEN);
        }

        if (cardNumber.length() != 16) {
            return new ResponseEntity<>("Faltan números o alguna gilada.", HttpStatus.FORBIDDEN);
        }

        if (cvv.length() != 3) {
            return new ResponseEntity<>("Revisá los números, rey.", HttpStatus.FORBIDDEN);
        }
        return billService.paymentCard(authentication, cardNumber, cvv);
    }



}
