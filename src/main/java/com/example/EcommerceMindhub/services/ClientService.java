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

    ResponseEntity<Object> createNewClient(String firstName, String lastName, String email, String address, String password);

    ResponseEntity<Object> deleteClient(Long id);

    Client findByEmail(String email);
}
