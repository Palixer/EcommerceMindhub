package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.ClientDTO;
import com.example.EcommerceMindhub.repositories.ClientRepository;
import com.example.EcommerceMindhub.repositories.PurchaseOrRepository;
import com.example.EcommerceMindhub.repositories.ShoppingCartRepository;
import com.example.EcommerceMindhub.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PurchaseOrRepository purchaseOrRepository;


    @Autowired
    private ClientService clientService;

    @RequestMapping("/clients")
    public List<ClientDTO> findAll() {

        return clientService.findAll();
    }
    @RequestMapping("/clients/{id}")
    public ClientDTO getClientById (@PathVariable Long id){

        return clientService.getClientById(id);
    }
    @GetMapping("/clients/current")
    public ClientDTO getClient(Authentication authentication){

        return clientService.getClient(authentication);
    }

    @PostMapping(path = "/clients")

    public ResponseEntity<Object> createClient(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String address, @RequestParam String password) throws IOException {


        return clientService.createNewClient(firstName, lastName, email, address, passwordEncoder.encode(password));
    }

    @DeleteMapping(path ="/clients")
    public ResponseEntity<Object> deleteClient(@RequestParam Long id){
       return clientService.deleteClient(id);
    }

   }


