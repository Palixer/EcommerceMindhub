package com.example.EcommerceMindhub.repositories;

import com.example.EcommerceMindhub.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    //Client findByName(Long id);
}
