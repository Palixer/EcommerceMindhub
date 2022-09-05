package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.ProductDTO;
import com.example.EcommerceMindhub.models.Product;
import com.example.EcommerceMindhub.repositories.ProductRepository;
import com.example.EcommerceMindhub.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @GetMapping("/products")
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productRepository.findById(id).map(ProductDTO::new).orElse(null);
    }

    @PostMapping("/products/newProducts")
    public ResponseEntity<Object> createProduct(
            @RequestParam String name, @RequestParam double price, @RequestParam int stock) {

        if (name.isEmpty() || price <= 0.0 || stock <= 0) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        Product newProduct = new Product(name, price, stock);
        productRepository.save(newProduct);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

