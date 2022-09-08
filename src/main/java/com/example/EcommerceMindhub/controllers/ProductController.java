package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.ProductDTO;
import com.example.EcommerceMindhub.models.Product;
import com.example.EcommerceMindhub.repositories.ProductRepository;
import com.example.EcommerceMindhub.repositories.ShoppingCartRepository;
import com.example.EcommerceMindhub.services.ProductService;
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
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/products/newProducts")
    public ResponseEntity<Object> createProduct(
            @RequestParam String name, @RequestParam double price, @RequestParam int stock) {

        if (name.isEmpty() || price <= 0.0 || stock <= 0) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        Product newProduct = new Product(name, price, stock);
        productService.createProduct(newProduct);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping(path ="/products")
    public ResponseEntity<Object> deleteProduct(@RequestParam String name){
        Product findProduct= productRepository.findByName(name);
        if (findProduct==null){
            return new ResponseEntity<>("El producto no existe", HttpStatus.FORBIDDEN);

        }
        productService.deleteProduct(findProduct);

        return new ResponseEntity<>("Producto Borrado correctamente",HttpStatus.OK);

    }

}

