package com.example.EcommerceMindhub.services.implementService;

import com.example.EcommerceMindhub.dtos.ProductDTO;
import com.example.EcommerceMindhub.models.Product;
import com.example.EcommerceMindhub.repositories.ProductRepository;
import com.example.EcommerceMindhub.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id).map(ProductDTO::new).orElse(null);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public ResponseEntity<Object> createProduct(String name, double price, int stock) {
        if (name.isEmpty() || price <= 0.0 || stock <= 0) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        productService.createProduct(name, price, stock);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> deleteProduct(String name) {
        Product findProduct= productRepository.findByName(name);
        if (findProduct==null){
            return new ResponseEntity<>("El producto no existe", HttpStatus.FORBIDDEN);

        }
        productService.deleteProduct(name);

        return new ResponseEntity<>("Producto Borrado correctamente",HttpStatus.OK);
    }
}
