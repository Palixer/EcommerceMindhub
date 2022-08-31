package com.example.EcommerceMindhub.repositories;

import com.example.EcommerceMindhub.models.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository {
    Client findById(Long id);
}
