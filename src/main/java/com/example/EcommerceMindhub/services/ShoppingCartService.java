package com.example.EcommerceMindhub.services;

import com.example.EcommerceMindhub.dtos.ShoppingCartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCartDTO> findAll();
    ShoppingCartDTO getShoppingCartById (Long id);
    ShoppingCartDTO getShoppingCart(Authentication authentication);
    ResponseEntity<Object> postShoppingCartDTO(Authentication authentication);

}
