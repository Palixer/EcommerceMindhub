package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.ClientDTO;
import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.models.PurchaseOrder;
import com.example.EcommerceMindhub.models.ShoppingCart;
import com.example.EcommerceMindhub.repositories.ClientRepository;
import com.example.EcommerceMindhub.repositories.PurchaseOrRepository;
import com.example.EcommerceMindhub.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
    @RequestMapping("/clients")
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }
    @RequestMapping("/clients/{id}")
    public ClientDTO getClientById (@PathVariable Long id){

        return clientRepository.findById(id).map(ClientDTO::new).orElse(null);
    }
    @GetMapping("/clients/current")
    public ClientDTO getClient(Authentication authentication){
        Client client = this.clientRepository.findByEmail(authentication.getName());

        return new ClientDTO(client);
    }
    @PostMapping(path = "/clients")

    public ResponseEntity<Object> createClient(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String address, @RequestParam String password) throws IOException {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientRepository.findByEmail(email) !=  null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        Client newClient=new Client(firstName, lastName, email, address, passwordEncoder.encode(password));
        clientRepository.save(newClient);


        ShoppingCart newShoppingCart = new ShoppingCart(newClient);
        shoppingCartRepository.save(newShoppingCart);
        return new ResponseEntity<>("Cliente creado correctamente",HttpStatus.CREATED);
    }

    @DeleteMapping(path ="/clients")
    public ResponseEntity<Object> deleteClient(@RequestParam Long id){
        Client clientFind = clientRepository.findById(id).orElse(null);
        if (clientFind==null){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);}
        Set<PurchaseOrder> ordenesEncontradas= clientFind.getShoppingCart().getPurchaseOrders();
        if (!ordenesEncontradas.isEmpty()){
            purchaseOrRepository.deleteAll(ordenesEncontradas);}

        shoppingCartRepository.deleteById(id);
        clientRepository.deleteById(id);



        return new ResponseEntity<>("Cliente Borrado correctamente",HttpStatus.OK);

    }

   }


