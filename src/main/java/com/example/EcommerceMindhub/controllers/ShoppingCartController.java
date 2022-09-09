package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.ShoppingCartDTO;
import com.example.EcommerceMindhub.repositories.ClientRepository;
import com.example.EcommerceMindhub.repositories.ShoppingCartRepository;
import com.example.EcommerceMindhub.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/shoppingCarts")
    public List<ShoppingCartDTO> findAll() {
        return shoppingCartService.findAll();
    }

    @GetMapping("/shoppingCart/{id}")
    public ShoppingCartDTO getShoppingCartById(@PathVariable Long id) {
        return shoppingCartService.getShoppingCartById(id);
    }

    @GetMapping("/clients/current/shoppingCart")
    public ShoppingCartDTO getShoppingCart(Authentication authentication) {
        return shoppingCartService.getShoppingCart(authentication);
    }

    @PostMapping("/clients/current/shoppingCart")
    public ResponseEntity<Object> postShoppingCartDTO(Authentication authentication) {
        return shoppingCartService.postShoppingCartDTO(authentication);
    }
}
