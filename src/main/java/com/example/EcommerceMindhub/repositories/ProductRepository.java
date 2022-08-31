package com.example.EcommerceMindhub.repositories;

import com.example.EcommerceMindhub.models.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

    Product  findById(Long id);

}
