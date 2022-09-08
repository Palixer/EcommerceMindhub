package com.example.EcommerceMindhub.repositories;

import com.example.EcommerceMindhub.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
