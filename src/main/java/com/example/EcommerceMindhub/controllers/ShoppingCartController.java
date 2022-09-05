package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.ShoppingCartDTO;
import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.repositories.ClientRepository;
import com.example.EcommerceMindhub.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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



}
