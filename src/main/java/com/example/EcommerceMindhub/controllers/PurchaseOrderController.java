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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
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
    @GetMapping("/purchaseOrders")
    public List<PurchaseOrderDTO> findAll() {
        return purchaseOrRepository.findAll().stream().map(purchaseOrder -> new PurchaseOrderDTO(purchaseOrder)).collect(Collectors.toList());
    }
    @GetMapping("/purchaseOrders/{id}")
    public PurchaseOrderDTO getPurchaseOrderById(@PathVariable Long id) {
        return purchaseOrRepository.findById(id).map(PurchaseOrderDTO::new).orElse(null);
    }
    @PostMapping(path = "/purchaseOrders")

    public ResponseEntity<Object> createPurchaseOrder(

            @RequestParam String name, @RequestParam Integer quantity, Authentication authentication)

           {
               Client clientInSession = this.clientRepository.findByEmail(authentication.getName());
        Product productFind = productRepository.findByName(name);


        if (name.isEmpty() || quantity <=0) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (productRepository.findByName(name) ==  null) {

            return new ResponseEntity<>("El nombre no existe", HttpStatus.FORBIDDEN);
        }

        if (quantity>productFind.getStock()){
            return new ResponseEntity<>("No podés comprar más del stock", HttpStatus.FORBIDDEN);
        }

        PurchaseOrder newPurchaseOrder = new PurchaseOrder(quantity, productFind.getPrice()*quantity, clientInSession.getShoppingCart(), productFind );
        purchaseOrRepository.save(newPurchaseOrder);
        return new ResponseEntity<>("Orden de compra creada",HttpStatus.CREATED);
    }
}