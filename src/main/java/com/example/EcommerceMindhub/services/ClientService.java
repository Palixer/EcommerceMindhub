package com.example.EcommerceMindhub.services;

import com.example.EcommerceMindhub.dtos.ClientDTO;
import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.models.ShoppingCart;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;



import java.util.List;

public interface ClientService {


    List<ClientDTO> findAll();
    ClientDTO getClientById(Long id);
    ClientDTO getClient(Authentication authentication);

    ResponseEntity<Object> createNewClient(Client newClient, ShoppingCart shoppingCart);

    ResponseEntity<Object> deleteClient(Client clientFind, ShoppingCart shoppingCartFind);

    ClientDTO findByEmail(String email);
}
