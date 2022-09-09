package com.example.EcommerceMindhub.services.implementService;

import com.example.EcommerceMindhub.dtos.ShoppingCartDTO;
import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.models.ShoppingCart;
import com.example.EcommerceMindhub.repositories.ClientRepository;
import com.example.EcommerceMindhub.repositories.ShoppingCartRepository;
import com.example.EcommerceMindhub.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShoppingCartImplement implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ShoppingCartDTO> findAll() {
        return shoppingCartRepository.findAll().stream().map(shoppingCart -> new ShoppingCartDTO(shoppingCart)).collect(Collectors.toList());

    }

    @Override
    public ShoppingCartDTO getShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id).map(ShoppingCartDTO::new).orElse(null);

    }

    @Override
    public ShoppingCartDTO getShoppingCart(Authentication authentication) {
        Client client=this.clientRepository.findByEmail(authentication.getName());
        return  new ShoppingCartDTO(client.getShoppingCart());
    }

    @Override
    public ResponseEntity<Object> postShoppingCartDTO(Authentication authentication) {
        Client clientInSession = this.clientRepository.findByEmail(authentication.getName());
        ShoppingCart newShoppingCart = clientInSession.getShoppingCart();
        return new ResponseEntity<>("Carrito Creado", HttpStatus.CREATED);
    }
}
