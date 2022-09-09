package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.ProductDTO;
import com.example.EcommerceMindhub.dtos.PurchaseOrderDTO;
import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.models.Product;
import com.example.EcommerceMindhub.models.PurchaseOrder;
import com.example.EcommerceMindhub.models.ShoppingCart;
import com.example.EcommerceMindhub.repositories.ClientRepository;
import com.example.EcommerceMindhub.repositories.ProductRepository;
import com.example.EcommerceMindhub.repositories.PurchaseOrRepository;
import com.example.EcommerceMindhub.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrRepository purchaseOrRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/purchaseOrders")
    public List<PurchaseOrderDTO> findAll() {
        return purchaseOrderService.findAll();
    }

    @GetMapping("/purchaseOrders/{id}")
    public PurchaseOrderDTO getPurchaseOrderById(@PathVariable Long id) {
        return purchaseOrderService.getPurchaseOrderById(id);
    }

    @PostMapping(path = "/purchaseOrders")

    public ResponseEntity<Object> createPurchaseOrder(

            @RequestParam String name, @RequestParam Integer quantity, Authentication authentication) {
        return purchaseOrderService.createPurchaseOrder(name, quantity, authentication);
    }

    @DeleteMapping(path = "/purchaseOrders")
    public ResponseEntity<Object> deletePurchaseOrder(@RequestParam Long id) {
        return purchaseOrderService.deletePurchaseOrder(id);

    }
}