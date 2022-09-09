package com.example.EcommerceMindhub.services;

import com.example.EcommerceMindhub.dtos.ClientDTO;
import com.example.EcommerceMindhub.dtos.ProductDTO;
import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.models.Product;
import com.example.EcommerceMindhub.models.ShoppingCart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO getProductById(Long id);

    Product findByName(String name);

    ResponseEntity<Object> createProduct(String name, double price, int stock);

    ResponseEntity<Object> deleteProduct(String name);


}
