package com.example.EcommerceMindhub.repositories;

import com.example.EcommerceMindhub.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BillRepository extends JpaRepository <Bill, Long>{

}
