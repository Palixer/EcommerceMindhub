package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.BillDTO;
import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.models.ShoppingCart;
import com.example.EcommerceMindhub.repositories.BillRepository;
import com.example.EcommerceMindhub.repositories.ClientRepository;
import com.example.EcommerceMindhub.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/bills")
    public List<BillDTO> findAll() {
        return billRepository.findAll().stream().map(bill -> new BillDTO(bill)).collect(Collectors.toList());
    }
    @GetMapping("/bills/{id}")
    public BillDTO getBillById (@PathVariable Long id){

        return billRepository.findById(id).map(BillDTO::new).orElse(null);
    }
    @PostMapping("/shoppingCart/current/bills")
    public ResponseEntity<Object> postBillDTO(Authentication authentication){
        Client clientInSession= this.clientRepository.findByEmail(authentication.getName());
        ShoppingCart newShoppingCart= clientInSession.getShoppingCart();
        return new ResponseEntity<>("C", HttpStatus.CREATED);

}
}
