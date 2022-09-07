package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.ShoppingCartDTO;
import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.models.ShoppingCart;
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
public class ShoppingCartController {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/shoppingCarts")
    public List<ShoppingCartDTO> findAll() {
        return shoppingCartRepository.findAll().stream().map(shoppingCart -> new ShoppingCartDTO(shoppingCart)).collect(Collectors.toList());
    }
    @GetMapping("/shoppingCart/{id}")
    public ShoppingCartDTO getShoppingCartById (@PathVariable Long id){

        return shoppingCartRepository.findById(id).map(ShoppingCartDTO::new).orElse(null);
    }
    @GetMapping("/clients/current/shoppingCart")
    public ShoppingCartDTO getShoppingCart(Authentication authentication){
        Client client=this.clientRepository.findByEmail(authentication.getName());
        return  new ShoppingCartDTO(client.getShoppingCart());
    }

    @PostMapping("/clients/current/shoppingCart")
    public ResponseEntity<Object> postShoppingCartDTO(Authentication authentication) {
        Client clientInSession = this.clientRepository.findByEmail(authentication.getName());
        ShoppingCart newShoppingCart = clientInSession.getShoppingCart();
        return new ResponseEntity<>("Carrito Creado", HttpStatus.CREATED);
    }







}
