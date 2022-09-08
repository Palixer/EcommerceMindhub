package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.BillDTO;
import com.example.EcommerceMindhub.dtos.PaymentDTO;
import com.example.EcommerceMindhub.models.*;
import com.example.EcommerceMindhub.repositories.BillRepository;
import com.example.EcommerceMindhub.repositories.ClientRepository;
import com.example.EcommerceMindhub.repositories.PaymentRepository;
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
public class PaymentController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ClientRepository clientRepository;


    @GetMapping("/payments")
    public List<PaymentDTO> findAll() {
        return paymentRepository.findAll().stream().map(payment -> new PaymentDTO(payment)).collect(Collectors.toList());
    }

    @GetMapping("/payments/{id}")
    public PaymentDTO getPaymentById (@PathVariable Long id){
        return paymentRepository.findById(id).map(PaymentDTO::new).orElse(null);
    }

    @PostMapping("/payments")
    public ResponseEntity<Object> payBill(Authentication authentication, WayToPayType wayToPayType, @RequestParam double priceTotal){
        Client clientInSession= this.clientRepository.findByEmail(authentication.getName());
        ShoppingCart shoppingCart= clientInSession.getShoppingCart();

        private double priceTotal(ShoppingCart){
            List<Double> prices=shoppingCart.getPurchaseOrders().stream().map(purchaseOrder -> purchaseOrder.getPrice()).collect(Collectors.toList());
            double total=0.0;
            for (double price : prices) {
                total=total+price;
            }
            return total;
        }
        Payment newPayment = new Payment(wayToPayType, priceTotal );

        return new ResponseEntity<>("Factura Abonada",HttpStatus.OK);

    }
}
