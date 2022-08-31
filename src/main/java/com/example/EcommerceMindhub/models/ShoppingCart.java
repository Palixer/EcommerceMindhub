package com.example.EcommerceMindhub.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private Long id;
    //@OneToOne
    public ShoppingCart() {
    }




}
